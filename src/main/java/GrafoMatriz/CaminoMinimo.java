package GrafoMatriz;

// Algoritmo de Dijkstra

public class CaminoMinimo {
    static final int INFINITO = 100000000;
    
    GrafoMatriz grafo;
    int[][] pesos;
    int[] ultimo;
    int[] direccion;
    boolean[] incluidos;
    int origen, numVerts;
        
    public CaminoMinimo(GrafoMatriz g, int org) throws Exception{
        grafo = g;
        numVerts = g.tamMax;
        pesos = g.copiarMatrizPesos();
        ultimo = new int[numVerts];
        direccion = new int[numVerts];
        incluidos = new boolean[numVerts];
        origen = org;
    }
        
    public void caminoMinimo(){
        for(int i = 0; i < numVerts; i++){
            incluidos[i] = false;
            direccion[i] = pesos[origen][i];
            ultimo[i] = origen;
        }
        incluidos[origen] = true;
        direccion[origen] = 0;
            
        for(int i = 0; i < numVerts; i++){
            int vertMin = minimo();
                
            incluidos[vertMin] = true;
            for(int w = 0; w < numVerts; w++){
                if(!incluidos[w]){
                    if(direccion[vertMin] + pesos[vertMin][w] < direccion[w]){
                        direccion[w] = direccion[vertMin] + pesos[vertMin][w];
                        ultimo[w] = vertMin;
                    }
                }
            }
        }
        rutas();    
    }
        
    public int minimo(){
        int mx = INFINITO;
        int v = 0;
        for(int j = 0; j < numVerts; j++){
            if(!incluidos[j] && (direccion[j] <= mx)){
                mx = direccion[j];
                v = j;
            }
        }
        return v;
    }
        
    public void mostrarCamino(int v){
        int anterior = ultimo[v];
        if(v != origen){
            mostrarCamino(anterior);
            System.out.print(" -> ("+ grafo.vertices[v].getID() + ", " + direccion[v] + ")");
        }
        else{
            System.out.print("(" + grafo.vertices[origen].getID() + ", " + direccion[origen]+ ")");
        }
    }
    
    public void rutas(){
        for(int i = 0; i < numVerts; i++){
            if(i != origen){
                System.out.println("El camino mínimo entre el vertice "+origen+" y "+i+" es: "+direccion[i]);
                mostrarCamino(i);
                System.out.println("\n");
            }
        }
    }
}
