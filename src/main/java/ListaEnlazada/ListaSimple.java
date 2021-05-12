package ListaEnlazada;

import GrafoLista.Arista;

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
    
    public void eliminarInicio() throws Exception{
        Nodo nodo_auxiliar;
        if (esVacio()){
            throw new Exception("Dipolo vacío");
        }
        else{
            nodo_auxiliar = inicio;
            inicio = inicio.siguiente;
            nodo_auxiliar.siguiente = null;
            size--;
        }
    }
    
    public void insertar(Arista a){
        Nodo nuevo = new Nodo(a);
        Arista arisInicio = (Arista) inicio.info;
        Arista arisFin = (Arista) fin.info;
        
        if (a.destino < arisInicio.destino){
            nuevo.siguiente = inicio;
            inicio = nuevo;
        }
        else if(a.destino > arisFin.destino){
            fin.siguiente = nuevo;
            fin = nuevo;
        }
        else{
            Nodo aux = inicio;
            Arista arisAux = (Arista) inicio.siguiente.info;
            while(aux.siguiente != null && a.destino > arisAux.destino){
                aux = aux.siguiente;
                arisAux = (Arista) aux.siguiente.info;
            }
            nuevo.siguiente = aux.siguiente;
            aux.siguiente = nuevo;
        }
    }
    
    public void eliminarFinal() throws Exception{
        if (!esVacio()){
            if (inicio == fin){
                inicio = null;
                fin = null;
            }
            else{
                Nodo nodo_auxiliar = inicio;
                
                while (nodo_auxiliar.siguiente != fin){
                    nodo_auxiliar = nodo_auxiliar.siguiente;
                }
                nodo_auxiliar.siguiente = null;
                fin = nodo_auxiliar;
            }
            size--;
        }
        else{
            throw new Exception("Dipolo vacío");
        }
    }
}

