package GrafoMatriz;

import java.util.*;

/**
 *
 * @author Kevin Rojas
 */
public class CaminoMaximo {
    private final int INFINITO = -999999999;
    
    public GrafoMatriz<Punto> grafo;
    public int[][] pesos;
    public int numVerts;
    
    public CaminoMaximo(GrafoMatriz g){
        grafo = g;
        pesos = g.matAdy;
        numVerts = g.numeroVertices();
    }
    
    public Integer caminoMaximo(String inicio, String fin){
        Integer vI = grafo.numVertice(inicio);
        Integer vF = grafo.numVertice(fin);
        
        return caminoMaximo(vI, vF);
    }
    
    @SuppressWarnings("null")
    private Integer caminoMaximo(Integer inicio, Integer fin){
        TreeSet<Integer> caminosPosibles = new TreeSet();
        if(distanciaEntrePuntos(inicio, fin) <= 1.0){
            return pesos[inicio][fin];
        }
        else{
            for(int posible = 0; posible < numVerts; posible++){
                if((distanciaEntrePuntos(posible, fin) < distanciaEntrePuntos(inicio, fin)) 
                        && (pesos[inicio][posible] > 0)){
                    caminosPosibles.add(pesos[inicio][posible] + caminoMaximo(posible, fin));
                }
            }
        }
        return caminosPosibles.isEmpty() ? INFINITO : caminosPosibles.last();
    }
    
    public double distanciaEntrePuntos(int inicial, int fin){
        return grafo.vertices[inicial].valor.distancia(grafo.vertices[fin].valor);
    }
}
