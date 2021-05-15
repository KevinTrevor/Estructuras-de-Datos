package GrafoMatriz;

import Cola.Cola;
import Pila.Pila;

public class RecorrerGrafo {
        static boolean[] recorridoAnchura(GrafoMatriz g, int origen) throws Exception{
        int vM;
        boolean[] visitados = new boolean[g.numeroVertices()];
        
        Cola c = new Cola();
        
        int vI = origen;
        if (vI < 0){
            throw new Exception("Vértice inexistente");
        }
        
        for(int i = 0; i < g.numeroVertices(); i++){
            visitados[i] = false;
        }
        
       visitados[vI] = true;
       c.encolar(vI);
       while(!c.esVacio()){
           vM = (int) c.desencolar();
           System.out.println("Vertice: "+ g.vertices[vM].ID);
           for(int k = 0; k < g.numeroVertices(); k++){
               if((g.matAdy[vM][k] == 1) && (visitados[k] == false)){
                   visitados[k] = true;
                   c.encolar(k);
               }
           }
       }
        return visitados;
    }
    
    static boolean[] recorridoProfundidad(GrafoMatriz g, int origen) throws Exception{
        int vM;
        boolean[] visitados = new boolean[g.numeroVertices()];
        
        Pila p = new Pila();
        
        int vI = origen;
        if (vI < 0){
            throw new Exception("Vértice inexistente");
        }
        
        for(int i = 0; i < g.numeroVertices(); i++){
            visitados[i] = false;
        }
        
       visitados[vI] = true;
       p.push(vI);
       while(!p.esVacio()){
           vM = (int) p.pop();
           System.out.println("Vertice: "+ g.vertices[vM].ID);
           for(int k = 0; k < g.numeroVertices(); k++){
               if((g.matAdy[vM][k] == 1) && (visitados[k] == false)){
                   visitados[k] = true;
                   p.push(k);
               }
           }
       }
        return visitados;
    }
}
