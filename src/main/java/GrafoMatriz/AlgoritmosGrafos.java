package GrafoMatriz;

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
    
    
    // Algoritmos avanzados
    
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

}
