package GrafoLista;

import ListaEnlazada.*;

public class VerticeAdy <E>{
    public String ID;
    public E valor;
    public int numVertice;
    public ListaSimple aristas;
    
    public VerticeAdy(String id){
        this.ID = id;
        this.valor = null;
        this.numVertice = -1;
        this.aristas = new ListaSimple();
    }
    
    // Método propio
    
    public boolean equals(VerticeAdy v){
        return ID.equals(v.ID);
    }
    
    // Getter y Setter
    
    public void setArista(Arista a) throws Exception{
        aristas.insertar(a);
    }
    
    public void setNumVert(int n){
        numVertice = n;
    }
}
