package GrafoMatriz;

import UnionFind.UnionFind;
import java.util.*;

public class AlgoritmosGrafos {
    
    static GrafoMatriz grafoInverso(GrafoMatriz g) throws Exception{
        GrafoMatriz gInv = new GrafoMatriz(g.tamMax);
        Vertice[] vs = g.vertices;
        
        for (int k = 0; k < g.tamMax; k++){
            gInv.nuevoVert(vs[k].getID());
        }
        for(int i = 0; i < g.tamMax; i++){
            for(int j = 0; j < g.tamMax; j++){
                if(g.adyacente(i, j)){
                    gInv.nuevaArista(j, i);
                }
            }
        }
        return gInv;
    }
    
    static int todosArboles(int[] bosque, int n){
        int i, w;
        i = w = -1;
        
        do{
            if(bosque[++i] == 0){
                w = i;
            }
        }while((i < n - 1) && (w == -1));
        return w;
    }
    
    static void componentesConexos(GrafoMatriz g) throws Exception{
        int i, v;
        boolean[] m;
        int[] descendientes = new int[g.tamMax];
        int[] ascendientes = new int[g.tamMax];
        int[] bosque = new int[g.tamMax];
        
        GrafoMatriz gInv = grafoInverso(g);
        Vertice[] verts = g.vertices;
        
        v = 0;
        do{
            m = RecorrerGrafo.recorridoProfundidad(g, v);
            for(i = 0; i < g.tamMax; i++){
                ascendientes[i] = (m[i] != false) ? 1 : 0;
            }
            m = RecorrerGrafo.recorridoProfundidad(gInv, v);
            for(i = 0; i < gInv.tamMax; i++){
                descendientes[i] = (m[i] != false) ? 1 : 0;
            }
            System.out.println("{ ");
            for(i = 0; i < g.tamMax; i++){
                if(descendientes[i] * ascendientes[i] == 1){
                    System.out.println(" "+ verts[i].getID());
                    bosque[i] = 1;
                }
            }
            System.out.println(" }");
            v = todosArboles(bosque, g.tamMax);
        }while(v != -1);
    }
    
    static int[][] generarAdyacencia(GrafoMatriz g) throws Exception{
        int[][] newMat = new int[g.tamMax][g.tamMax];
        
        for (int i = 0; i < g.tamMax; i++){
            for(int j = 0; j < g.tamMax; j++){
                newMat[i][j] = g.adyacente(i,j) ? 1 : 0; 
            }
        }
        return newMat;
    }
    
    static int[][] copiarMatriz(GrafoMatriz g){
        int[][] newMat = new int[g.tamMax][g.tamMax];
        for (int i = 0; i < g.tamMax; i++){
            System.arraycopy(g.matAdy[i], 0, newMat[i], 0, g.tamMax);
        }
        return newMat;
    }
    
    static int[][] matrizEstrella(GrafoMatriz g, int[][] newMat){ 
        for(int i = 0; i < g.tamMax; i++){
            for(int j = 0; j < g.tamMax; j++){
                for(int k = 0; k < g.tamMax; k++){
                    newMat[i][j] += g.matAdy[i][k] * g.matAdy[k][j];
                }
            }
        }
        return newMat;
    }
    
    static void puntosArticulacion(GrafoMatriz g) throws Exception{
        int[] num = new int[g.tamMax];
        int[] bajo = new int[g.tamMax];
        int[] arista = new int[g.tamMax];
        boolean[] visitado = new boolean[g.tamMax];
        
        for(int i = 0; i < g.tamMax; i++){
            visitado[i] = false;
        }
        puntosArticulacion(g, 0, -1, num, visitado, arista, bajo);
    }
    private static void puntosArticulacion(GrafoMatriz g, int vert, int paso, int[] num, 
        boolean[] visitado, int[] arista, int[] bajo) throws Exception{
        
        visitado[vert] = true;
        num[vert] = ++paso;
        bajo[vert] = num[vert];
        
        for(int k = 0; k < g.numeroVertices(); k++){
            if(g.adyacente(vert, k)){
                if(!visitado[k]){
                    arista[k] = vert;
                    puntosArticulacion(g, k, paso, num, visitado, arista, bajo);
                    
                    if(bajo[k] >= num[vert]){
                        System.out.println("Vértice" + vert + "es un punto de articulación");
                        bajo[k] = Math.min(bajo[vert], bajo[k]);
                    }
                    else if(arista[vert] != k){
                        bajo[k] = Math.min(bajo[vert], num[k]);
                    }
                }    
            }  
        }
    }    
    
    
    // Algoritmo de Warshall
    
    static int[][] matrizWarshall(GrafoMatriz g) throws Exception{
        int[][] w = AlgoritmosGrafos.copiarMatriz(g);
        
        for(int k = 0; k < g.numeroVertices(); k++){
            for(int i = 0; i < g.numeroVertices(); i++){
                for (int j = 0; j < g.numeroVertices(); j++){
                    w[i][j] = Math.min(w[i][j] + w [i][k] * w[k][j], 1);
                }
            }
        }
        
        return w;
    }
    
    // Algoritmo de Kruskall
    
    class AristaK implements Comparable<AristaK>, Comparator<AristaK>{
        int origen;
        int destino;
        int peso;
        
        public AristaK(int o, int d, int p){
            origen = o;
            destino = d;
            peso = p;
        }
        
        @Override
        public int compareTo(AristaK a){
            Integer o1 = peso;
            Integer o2 = a.peso;
            return o1.compareTo(o2);
        }
        
        @Override
        public String toString(){
            return "("+origen+","+destino+","+peso+")";
        }
        
        @Override
        public int compare(AristaK a1, AristaK a2){
            return a1.peso - a2.peso;
        }
    }
    
    public void ArbolExpMinimo(GrafoMatriz g) throws Exception{
        UnionFind aux = new UnionFind(g.tamMax);
        PriorityQueue<AristaK> mont = new PriorityQueue();
        LinkedList<AristaK> ArbolExpansion = new LinkedList();
        
        for(int i = -1; i < g.tamMax-1; i++){
            for(int j = i+1; j < g.tamMax; j++){
                if(g.adyacente(i+1, j)){
                    mont.add(new AristaK(i+1, j, g.matAdy[i+1][j]));
                }    
            }
        }
        
        while((ArbolExpansion.size() != g.tamMax-1) ||!mont.isEmpty()){
            AristaK temporal = mont.remove();
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
            AristaK temporal = ArbolExpansion.removeFirst();
            System.out.print(temporal.toString());
            if (!ArbolExpansion.isEmpty()){
                System.out.print(", ");
            }
            count = count + temporal.peso;
        }
        System.out.println("}");
        System.out.println("Peso total del arbol de expansión mínimo: " + count);
    }
}
