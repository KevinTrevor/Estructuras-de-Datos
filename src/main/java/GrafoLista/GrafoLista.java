package GrafoLista;

public class GrafoLista {
    public int numVerts;
    public int tamMax;
    public VerticeAdy[] listAdy;
    
    public GrafoLista(int max){
        this.numVerts = 0;
        this.listAdy = new VerticeAdy[max];
        this.tamMax = max;
    }
    
    // Métodos propios
    
    public int numVertice(String nom){
        VerticeAdy v = new VerticeAdy(nom);
        boolean encontrado = false;
        
        int i = 0;
        
        for(;(i < numVerts) && (!encontrado);){
            encontrado = listAdy[i].equals(v);
            if(!encontrado){
                i++;
            }
        }
        return (i < numVerts) ? i : -1;
    }
    
    public void nuevoVert(String nom){
        boolean existe = numVertice(nom) >= 0;
        
        if(!existe){
            VerticeAdy v = new VerticeAdy(nom);
            v.setNumVert(numVerts);
            listAdy[numVerts] = v;
            numVerts++;
        }
    }
    
    public void nuevaArista(int vI, int vF) throws Exception{
        if(!listAdy[vI].existeArista(vF)){
            Arista nueva_arista = new Arista(vF);
            listAdy[vI].setArista(nueva_arista);
        }
        else{
            throw new Exception("Ya existe la arista");
        }
    }
    
    // Getter y Setter
}
