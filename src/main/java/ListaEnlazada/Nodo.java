package ListaEnlazada;

public class Nodo <E>{
    /** Atributos de la Clase Nodo */
    public Nodo siguiente;
    public E info;

    /** Métodos de la Clase Nodo */
    public Nodo(){
        /** Método constructor de la Clase Nodo */
        this.info = null;
        this.siguiente = null;
    }
    
    public Nodo(E dato){
        this.info = dato;
        this.siguiente = null;
    }
    
    public void ingresar(E datos){
        this.info = datos;
    }
}    
