package GrafoMatriz;

import static GrafoMatriz.CaminoMinimo.INFINITO;

public class GrafoMatriz <E>{
    /** Atributos de la clase GrafoMatriz*/
    public int numVerts;
    public Vertice<E>[] vertices;
    public int[][] matAdy;
    public int tamMax;
    
    /** Método constructor de la clase GrafoMatriz
     * @param max*/
    public GrafoMatriz(int max){
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
    
    /** Método que verifica que no se encuentra un vertice en el grafo devuelve un numero entero
     * @param nom
     * @return */
    public int numVertice(String nom){
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
      
    public void nuevoVert(String nombre){
        /** Método para añadir un nuevo vertice al grafo*/
        boolean existe = numVertice(nombre) >= 0;
        
        if(!existe){
            Vertice v = new Vertice(nombre);
            v.setNumVert(numVerts);
            vertices[numVerts] = v;
            numVerts++;
        }
    }
    
    public void nuevoVert(String nombre, E valor){
        /** Método para añadir un nuevo vertice al grafo*/
        boolean existe = numVertice(nombre) >= 0;
        
        if(!existe){
            Vertice v = new Vertice(nombre, valor);
            v.setNumVert(numVerts);
            vertices[numVerts] = v;
            numVerts++;
        }
    }
    
    public void nuevaArista(int vA, int vB, int peso) throws Exception{
        /** Método para añadir una nueva arista con peso entre dos vertices*/
        if(vA < 0 || vB < 0){
            throw new Exception("Vértice no existe");
        }
        matAdy[vA][vB] = peso;
    }
    
    public void nuevaArista(String idA, String idB, int peso) throws Exception{
        /** Método para añadir una nueva arista con peso entre dos vertices*/
        int vA = numVertice(idA); 
        int vB = numVertice(idB);
        
        if(vA < 0 || vB < 0){
            throw new Exception("Vértice no existe");
        }
        matAdy[vA][vB] = peso;
    }
    
    public void nuevaAristaNoDirigida(int vA, int vB, int peso) throws Exception{
        /** Método para añadir una nueva arista con peso entre dos vertices*/
        if(vA < 0 || vB < 0){
            throw new Exception("Vértice no existe");
        }
        matAdy[vA][vB] = peso;
        matAdy[vB][vA] = peso;
    }
    
    public void nuevaAristaNoDirigida(String idA, String idB, int peso) throws Exception{
        /** Método para añadir una nueva arista con peso entre dos vertices*/
        int vA = numVertice(idA); 
        int vB = numVertice(idB);
        
        if(vA < 0 || vB < 0){
            throw new Exception("Vértice no existe");
        }
        matAdy[vA][vB] = peso;
        matAdy[vB][vA] = peso;
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
    
    public int[][] copiarMatrizPesos() throws Exception{
        int[][] n = new int[tamMax][tamMax];
        for(int i = 0; i < tamMax; i++){
            for(int j = 0; j < tamMax; j++){
                if(i == j){
                    n[i][j] = 0;
                }
                else{
                    n[i][j] = adyacente(i,j) ? matAdy[i][j] : INFINITO;
                }
            }
        }
        return n;
    }
    
    // Getter y Setter
    
    public int numeroVertices(){
        return tamMax;
    }
    
    public int pesoArco(int i, int j){
        return matAdy[i][j];
    }
    
    public void mostrarMatriz(){
        for(int i = 0; i < tamMax; i++){
            System.out.print("|");
            for(int j = 0; j < tamMax; j++){
                System.out.print(" "+ matAdy[i][j] +" ");   
            }
            System.out.println("|");
        }
    }
}
