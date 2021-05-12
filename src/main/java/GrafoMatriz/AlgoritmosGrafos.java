package GrafoMatriz;

public class AlgoritmosGrafos {
    
    static GrafoMatriz grafoInverso(GrafoMatriz g) throws Exception{
        GrafoMatriz gInv = new GrafoMatriz(g.tamMax);
        
        for(int i = 0; i < g.tamMax; i++){
            for(int j = 0; j < g.tamMax; j++){
                if(g.adyacente(i, j)){
                    gInv.nuevaArista(j, i);
                }
                else{
                    gInv.matAdy[j][i] = 0;
                }
            }
        }
        return gInv;
    }
    
    static int[][] copiarMatAdy(GrafoMatriz g) throws Exception{
        int[][] nuevaMat = new int[g.tamMax][g.tamMax];
        
        for (int i = 0; i < g.tamMax; i++){
            for(int j = 0; j < g.tamMax; j++){
                nuevaMat[i][j] = g.adyacente(i,j) ? 1 : 0; 
            }
        }
        return nuevaMat;
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
    
    
    // Algoritmos avanzados
    
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

}
