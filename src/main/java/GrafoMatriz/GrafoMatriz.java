package GrafoMatriz;

public class GrafoMatriz {
    /** Atributos de la clase GrafoMatriz*/
    public int numVerts;
    public Vertice[] vertices;
    public int[][] matAdy;
    public int tamMax;
    
    public GrafoMatriz(int max){
        /** Método constructor de la clase GrafoMatriz*/
        vertices = new Vertice[max];
        matAdy = new int[max][max];
        
        for(int i = 0; i < max; i++){
            for(int j = 0; j < max; j++){
                matAdy[i][j] = 0;
            }
        }
        
        tamMax = max;
        numVerts = 0;
    }
    
    // Métodos propios
    
    public int numVertice(String nom){
        /** Método que verifica que no se encuentra un vertice en el grafo
         devuelve un numero entero*/
        Vertice v = new Vertice(nom);
        boolean encontrado = false;
        
        int i = 0;
        
        for(;(i < numVerts) && !encontrado;){
            encontrado = vertices[i].equals(v);
            if(!encontrado){
                i++;
            }
        }
        return (i < numVerts) ? i : -1;
    }
    
    public void nuevoVert(String nom){
        /** Método para añadir un nuevo vertice al grafo*/
        boolean existe = numVertice(nom) >= 0;
        
        if(!existe){
            Vertice v = new Vertice(nom);
            v.setVert(numVerts);
            vertices[numVerts] = v;
            numVerts++;
        }
    }
    
    public void nuevaAristaP(int vA, int vB, int peso) throws Exception{
        /** Método para añadir una nueva arista con peso entre dos vertices*/
        if(vA < 0 || vB < 0){
            throw new Exception("Vértice no existe");
        }
        matAdy[vA][vB] = peso;
    }
    
    public void nuevaAristaP(String idA, String idB, int peso) throws Exception{
        /** Método para añadir una nueva arista con peso entre dos vertices*/
        int vA = numVertice(idA); 
        int vB = numVertice(idB);
        
        if(vA < 0 || vB < 0){
            throw new Exception("Vértice no existe");
        }
        matAdy[vA][vB] = peso;
    }
    
    public void nuevaArista(int vA, int vB) throws Exception{
        /** Método para añadir una nueva arista entre dos vertices*/
        if(vA < 0 || vB < 0){
            throw new Exception("Vértice no existe");
        }
        matAdy[vA][vB] = 1;
    }
    
    public void nuevaArista(String idA, String idB) throws Exception{
        /** Método para añadir una nueva arista entre dos vertices*/
        int vA = numVertice(idA); 
        int vB = numVertice(idB);
        
        if(vA < 0 || vB < 0){
            throw new Exception("Vértice no existe");
        }
        matAdy[vA][vB] = 1;
    }
    
    public boolean adyacente(int vA, int vB) throws Exception{
        /** Método que determina si un vertice es adyacente a otro*/
        if(vA < 0 || vB < 0){
            throw new Exception("Vértice no existe");
        }
        return matAdy[vA][vB] >= 1;
    }
    
    public boolean adyacente(String idA, String idB) throws Exception{
        /** Método que determina si un vertice es adyacente a otro*/
        int vA = numVertice(idA); 
        int vB = numVertice(idB);
        
        if(vA < 0 || vB < 0){
            throw new Exception("Vértice no existe");
        }
        return matAdy[vA][vB] >= 1;
    }
    
    public int[][] copiarMatAdy() throws Exception{
        int[][] nuevaMat = new int[tamMax][tamMax];
        
        for (int i = 0; i < tamMax; i++){
            for(int j = 0; j < tamMax; j++){
                nuevaMat[i][j] = adyacente(i,j) ? 1 : 0; 
            }
        }
        return nuevaMat;
    }

    // Getter y Setter
    
}
