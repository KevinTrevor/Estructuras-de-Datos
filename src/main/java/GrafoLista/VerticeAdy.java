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
}
