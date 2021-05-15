package Pila;

import Cola.Nodo;

public class Pila <E>{
    public Nodo tope;
    public int size;
    
    public Pila(){
        this.tope = null;
        this.size = 0;
    }
    
    public boolean esVacio(){
        return this.tope == null;
    }
    
    public void push(E info) {
        Nodo nuevo_nodo = new Nodo(); 
        nuevo_nodo.ingresar(info);
        if (esVacio()){
            this.tope = nuevo_nodo;
        }
        else{
            nuevo_nodo.siguiente = this.tope;
            this.tope = nuevo_nodo;
        }
        this.size++;
    }
    
    public E pop() throws Exception{
        /* Método que elimina y devuelve el valor del nodo tope */
        Nodo nodo_retorno = new Nodo();
        
        if (esVacio()){
            throw new Exception("Cola Vacía");
        }
        else{
            nodo_retorno.ingresar(this.tope.info); 
            this.tope = this.tope.siguiente;
            this.size--;
        }
        return (E) nodo_retorno.info;
    }
}