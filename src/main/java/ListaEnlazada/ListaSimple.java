/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListaEnlazada;

/**
 *
 * @author yara
 */
public class ListaSimple <E>{
    public Nodo inicio;
    public Nodo fin;
    public int size;
    
    public ListaSimple(){
        this.inicio = null;
        this.fin = null;
        this.size = 0;
    }
    
    public boolean esVacio(){
        return this.inicio == null;
    }
    
    public void insertarFinal(E info) throws Exception{
        Nodo nuevo_nodo = new Nodo();
        nuevo_nodo.ingresar(info);
        if (!esVacio()){
            fin.siguiente = nuevo_nodo;
            fin = fin.siguiente; 
        }
        else{
            inicio = nuevo_nodo;
            fin = inicio;
        }
        size++;
  
    }
    
    public void insertarInicio(E info) throws Exception{
        Nodo nuevo_nodo = new Nodo();
        nuevo_nodo.ingresar(info);
        if (!esVacio()){
            nuevo_nodo.siguiente = inicio;
            inicio = nuevo_nodo;
        }
        else{
            inicio = nuevo_nodo;
            fin = inicio;
        }
        size++;
    }
    
    public E sacarInicio() throws Exception{
        Nodo nodo_retorno;
        if (esVacio()){
            throw new Exception("Dipolo vacío");
        }
        else{
            nodo_retorno = inicio;
            inicio = inicio.siguiente;
            nodo_retorno.siguiente = null;
            size--;
        }
        return (E) nodo_retorno.info;
    }
    
    public E sacarFinal() throws Exception{
        Nodo nodo_retorno;
        if (!esVacio()){
            if (inicio == fin){
                nodo_retorno = fin;
                inicio = null;
                fin = null;
            }
            else{
                Nodo nodo_auxiliar = inicio;
                
                while (nodo_auxiliar.siguiente != fin){
                    nodo_auxiliar = nodo_auxiliar.siguiente;
                }
                nodo_auxiliar.siguiente = null;
                nodo_retorno = fin;
                fin = nodo_auxiliar;
            }
            size--;
        }
        else{
            throw new Exception("Dipolo vacío");
        }
        return (E) nodo_retorno.info;
    }
}

