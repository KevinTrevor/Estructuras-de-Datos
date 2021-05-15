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
    
    // Getter y Setter
    
    public int numeroVertices(){
        return tamMax;
    }
    
    public int pesoArco(int i, int j){
        return matAdy[i][j];
    }
    
    public void mostrar(){
        for(int i = 0; i < tamMax; i++){
            System.out.print("|");
            for(int j = 0; j < tamMax; j++){
                System.out.print(" "+ matAdy[i][j] +" ");   
            }
            System.out.println("|");
        }
    }
    public static void main(String[] args) throws Exception{
        GrafoMatriz g = new GrafoMatriz(4);
        
        g.nuevoVert("Porlamar");
        g.nuevoVert("La Asunción");
        g.nuevoVert("Villa Rosa");
        g.nuevoVert("San Antonio");
        
        g.nuevaArista("Villa Rosa", "Porlamar", 10);
        g.nuevaArista("Porlamar", "Villa Rosa", 10);
        
        g.nuevaArista("Porlamar", "San Antonio", 8);
        g.nuevaArista("San Antonio", "Porlamar", 8);
        
        g.nuevaArista("La Asunción", "Porlamar", 18);
        g.nuevaArista("Porlamar", "La Asunción", 18);
        
        g.nuevaArista("San Antonio", "Villa Rosa", 2);
        g.nuevaArista("Villa Rosa", "San Antonio", 2);
        
        //RecorrerGrafo.recorridoAnchura(g, 0);
        //g.mostrar();
        
        AlgoritmosGrafos x = new AlgoritmosGrafos();
        
        x.ArbolExpMinimo(g);
    }
}
