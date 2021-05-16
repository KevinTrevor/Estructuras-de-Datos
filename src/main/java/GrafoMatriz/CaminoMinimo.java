package GrafoMatriz;

// Algoritmo de Dijkstra

public class CaminoMinimo {
    static final int INFINITO = 100000000;
    
    GrafoMatriz grafo;
    int[][] pesos;
    int[] ultimo;
    int[] valorPeso;
    boolean[] incluidos;
    int origen, numVerts;
        
    public CaminoMinimo(GrafoMatriz g, int org){
        grafo = g;
        numVerts = g.tamMax;
        pesos = g.matAdy;
        ultimo = new int[numVerts];
        valorPeso = new int[numVerts];
        incluidos = new boolean[numVerts];
        origen = org;
    }
        
    public void caminoMinimo(){
        for(int i = 0; i < numVerts; i++){
            incluidos[i] = false;
            valorPeso[i] = pesos[origen][i];
            ultimo[i] = origen;
        }
        incluidos[origen] = true;
        valorPeso[origen] = 0;
            
        int vertMin = 0;
            
        for(int i = 1; i < numVerts; i++){
            vertMin = minimo();
                
            incluidos[i] = true;
            for(int w = 1; w < numVerts; w++){
                if(!incluidos[w]){
                    if(valorPeso[vertMin] + pesos[vertMin][w] < valorPeso[w]){
                        valorPeso[w] = valorPeso[vertMin] + pesos[vertMin][w];
                        ultimo[w] = vertMin;
                    }
                }
            }
        }
            
        mostrarCamino(vertMin);
    }
        
    public int minimo(){
        int mx = INFINITO;
        int v = 1;
        for(int j = 1; j < numVerts; j++){
            if(!incluidos[j] && (valorPeso[j] <= mx)){
                mx = valorPeso[j];
                v = j;
            }
        }
        return v;
    }
        
    public void mostrarCamino(int v){
        int anterior = ultimo[v];
        if(v != origen){
            mostrarCamino(anterior);
            System.out.print(" -> ("+ grafo.vertices[v].getID() + ", " + valorPeso[v] + ")");
        }
        else{
            System.out.print("(" + grafo.vertices[origen].getID() + ", " + valorPeso[origen]+ ")");
        }
    }
}
