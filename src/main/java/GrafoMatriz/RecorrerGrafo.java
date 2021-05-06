package GrafoMatriz;

import Pilas_y_Colas.Cola;
import Pilas_y_Colas.Pila;

public class RecorrerGrafo {
        public boolean[] recorridoAnchura(GrafoMatriz g, int origen) throws Exception{
        int vM;
        boolean[] visitados = new boolean[g.tamMax];
        
        Cola c = new Cola();
        
        int vI = origen;
        if (vI < 0){
            throw new Exception("Vértice inexistente");
        }
        
        for(int i = 0; i < g.tamMax; i++){
            visitados[i] = false;
        }
        
       visitados[vI] = true;
       c.encolar(vI);
       while(!c.esVacio()){
           vM = (int) c.desencolar();
           // Hacer operaciones necesarias
           for(int k = 0; k < g.tamMax; k++){
               if((g.matAdy[vM][k] == 1) && (visitados[k] == false)){
                   visitados[k] = true;
                   c.encolar(k);
               }
           }
       }
        return visitados;
    }
    
    public boolean[] recorridoProfundidad(GrafoMatriz g, int origen) throws Exception{
        int vM;
        boolean[] visitados = new boolean[g.tamMax];
        
        Pila p = new Pila();
        
        int vI = origen;
        if (vI < 0){
            throw new Exception("Vértice inexistente");
        }
        
        for(int i = 0; i < g.tamMax; i++){
            visitados[i] = false;
        }
        
       visitados[vI] = true;
       p.push(vI);
       while(!p.esVacio()){
           vM = (int) p.pop();
           // Hacer operaciones necesarias
           for(int k = 0; k < g.tamMax; k++){
               if((g.matAdy[vM][k] == 1) && (visitados[k] == false)){
                   visitados[k] = true;
                   p.push(k);
               }
           }
       }
        return visitados;
    }
}
