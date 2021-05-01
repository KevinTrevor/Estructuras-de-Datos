package Pilas_y_Colas;

public class Pila <E>{
    public Nodo tope;
    public int size;
    public int limite;
    
    public Pila(){
        this.tope = null;
        this.size = 0;
        this.limite = 100;
    }
    
    public boolean esVacio(){
        return this.tope == null;
    }
    
    public boolean estaLleno(){
        return this.size == limite;
    }
    
    public void push(E info) throws Exception{
        if (estaLleno()){
            throw new Exception("Cola Llena");
        }
        else{
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
    }
    
    public Nodo pop() throws Exception{
        /* Método que elimina y devuelve el valor del nodo tope */
        if (esVacio()){
            throw new Exception("Cola Vacía");
        }
        else{
            Nodo nodo_retorno = new Nodo();
            nodo_retorno.ingresar(this.tope.info); 
            this.tope = this.tope.siguiente;
            this.size--;
            return nodo_retorno;
        }
    }
}