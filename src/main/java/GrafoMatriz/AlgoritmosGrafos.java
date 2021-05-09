/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafoMatriz;

/**
 *
 * @author yara
 */
public class AlgoritmosGrafos {
    
    static int[][] matrizWarshall(GrafoMatriz g) throws Exception{
        int[][] w = g.copiarMatAdy();
        
        for(int k = 0; k < g.numeroVertices(); k++){
            for(int i = 0; i < g.numeroVertices(); i++){
                for (int j = 0; j < g.numeroVertices(); j++){
                    w[i][j] = Math.min(w[i][j] + w [i][k] * w[k][j], 1);
                }
            }
        }
        
        return w;
    }
    
    static void puntosArticulacion(GrafoMatriz g, int vert, int[] num, int paso, 
        boolean[] visitado, int[] arista, int[] bajo) throws Exception{
        
        visitado[vert] = true;
        num[vert] = ++paso;
        bajo[vert] = num[vert];
        
        for(int k = 0; k < g.numeroVertices(); k++){
            if(g.adyacente(vert, k)){
                if(!visitado[k]){
                    arista[k] = vert;
                    puntosArticulacion(g, k, num, paso, visitado, arista, bajo);
                    
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
}
