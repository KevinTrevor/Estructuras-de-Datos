package UnionFind;

public class UnionFind {
    public int[] padres;
    
    public UnionFind(int numVerts){
        padres = new int[numVerts];
        
        for(int i = 0; i < numVerts; i++){
            padres[i] = i;
        }
    }
    
    public int find(int nodo){
        if(padres[nodo] == nodo){
            return nodo;
        }
        
        return find(padres[nodo]);
    }
    
    public void union(int origen, int destino){
        int fOrigen = find(origen);
        int fDestino = find(destino);
        padres[fOrigen] = fDestino;
    }
}
