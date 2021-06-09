package GrafoMatriz;

import UnionFind.UnionFind;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 *
 * @author Kevin Rojas
 */
public class ArbolExpansion {
    class AristaKruskall implements Comparable<AristaKruskall>, Comparator<AristaKruskall>{
        int origen;
        int destino;
        int peso;
        
        public AristaKruskall(int o, int d, int p){
            origen = o;
            destino = d;
            peso = p;
        }
        
        @Override
        public int compareTo(AristaKruskall a){
            Integer o1 = peso;
            Integer o2 = a.peso;
            return o1.compareTo(o2);
        }
        
        @Override
        public String toString(){
            return "("+origen+","+destino+","+peso+")";
        }
        
        @Override
        public int compare(AristaKruskall a1, AristaKruskall a2){
            return a1.peso - a2.peso;
        }
    }
    
     public PriorityQueue<AristaKruskall> hallarAristas(GrafoMatriz g, PriorityQueue<AristaKruskall> aristas) throws Exception{
        for(int i = -1; i < g.tamMax-1; i++){
            for(int j = i+1; j < g.tamMax; j++){
                if(g.adyacente(i+1, j)){
                    aristas.add(new AristaKruskall(i+1, j, g.matAdy[i+1][j]));
                }    
            }
        }
        return aristas;
    }
    
    public void ArbolExpMinimo(GrafoMatriz g) throws Exception{
        UnionFind aux = new UnionFind(g.tamMax);
        PriorityQueue<AristaKruskall> mont = hallarAristas(g, new PriorityQueue());
        LinkedList<AristaKruskall> ArbolExpansion = new LinkedList();
        
        while((ArbolExpansion.size() != g.tamMax-1) ||!mont.isEmpty()){
            AristaKruskall temporal = mont.remove();
            if (aux.find(temporal.origen) != aux.find(temporal.destino)){
                aux.union(temporal.origen, temporal.destino);
                ArbolExpansion.add(temporal);
            }
        }
        
        System.out.print("{");
        int count = 0;
        if (mont.isEmpty() && ArbolExpansion.size() != g.tamMax-1) {
            System.out.println("ARBOL INVÁLIDO");
        }
        while (!ArbolExpansion.isEmpty()){
            AristaKruskall temporal = ArbolExpansion.removeFirst();
            System.out.print(temporal.toString());
            if (!ArbolExpansion.isEmpty()){
                System.out.print(", ");
            }
            count = count + temporal.peso;
        }
        System.out.println("}");
        System.out.println("Peso total del arbol de expansión mínimo: " + count);
    }
    
    public GrafoMatriz ArbolExpMaximo(GrafoMatriz g) throws Exception{
        UnionFind aux = new UnionFind(g.tamMax);
        PriorityQueue<AristaKruskall> mont = hallarAristas(g, new PriorityQueue(Collections.reverseOrder()));
        LinkedList<AristaKruskall> ArbolExpansion = new LinkedList();
        
        while((ArbolExpansion.size() != g.tamMax-1) ||!mont.isEmpty()){
            AristaKruskall temporal = mont.remove();
            if (aux.find(temporal.origen) != aux.find(temporal.destino)){
                aux.union(temporal.origen, temporal.destino);
                ArbolExpansion.add(temporal);
            }
        }
        
        GrafoMatriz sinCaminosMinimos = new GrafoMatriz(g.tamMax);
        sinCaminosMinimos.vertices = g.vertices;
        sinCaminosMinimos.numVerts = g.numVerts;
        
        if (mont.isEmpty() && ArbolExpansion.size() != g.tamMax-1){
            return null;
        }
        while(!ArbolExpansion.isEmpty()){
            AristaKruskall nuevaArista = ArbolExpansion.removeFirst();
            sinCaminosMinimos.nuevaAristaNoDirigida(nuevaArista.origen, nuevaArista.destino, 
                    nuevaArista.peso);
        }
        
        return sinCaminosMinimos;
    }
}
