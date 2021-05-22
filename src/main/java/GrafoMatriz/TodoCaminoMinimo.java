package GrafoMatriz;

// Algoritmo de Floyd
public class TodoCaminoMinimo {
    static final int INFINITO = 100000000;
    
    public GrafoMatriz grafo;
    public int[][] pesos;
    public int[][] distancia;
    public int[][] camino;
    public int numVerts;
    
    public TodoCaminoMinimo(GrafoMatriz g) throws Exception{
        numVerts = g.tamMax;
        grafo = g;
        pesos = g.copiarMatrizPesos();
        distancia = new int[numVerts][numVerts];
        camino = new int[numVerts][numVerts];
    }
    
    public void todosCaminosMinimo(){
        for(int i = 0; i < numVerts; i++){
            for(int j = 0; j < numVerts; j++){
                distancia[i][j] = pesos[i][j];
                camino[i][j] = -1;
            }
        }
        
        for(int k = 0; k < numVerts; k++){
            for(int i = 0; i < numVerts; i++){
                for(int j = 0; j < numVerts; j++){
                    // Condición nuevo vértice mínimo
                    if(distancia[i][k] + distancia[k][j] < distancia[i][j]){
                        distancia[i][j] = distancia[i][k] + distancia[k][j];
                        camino[i][j] = k;
                    }
                }
            }
        }
        
        rutas();
    }
    
    public void mostrarCamino(int vI, int vF){
        int siguiente = camino[vI][vF];
        if(siguiente != -1){
            mostrarCamino(vI, siguiente);
            System.out.print(" -> (" + grafo.vertices[vF].getID()+ ", "+ distancia[vI][vF]+")");
        }
        else{
            System.out.print("("+ grafo.vertices[vI].getID() + ", " + distancia[vI][vI] + ") -> (" 
                    + grafo.vertices[vF].getID() + ", " + distancia[vI][vF] + ")");
        }   
    }
    
    public void rutas(){
       for(int i = 0; i < numVerts; i++){
            for(int j = 0; j < numVerts; j++){
                if(i != j){
                    System.out.println("\nEl camino mínimo entre el vertice "+i+" y "+j+" es: "+distancia[i][j]);
                    mostrarCamino(i, j);
                    System.out.println("");
                }    
            }
        }
    }
}
