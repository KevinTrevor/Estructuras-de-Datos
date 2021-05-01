package Pilas_y_Colas;

public class Cola <E>{
    public Nodo frente;
    public Nodo fondo;
    public int size;
    public int limite;
    
    public Cola(){
        
        this.frente = null;
        this.fondo = null;
        this.size = 0;
        this.limite = 100;
    }
    
    public boolean esVacio(){
        return this.frente == null;
    }
    
    public boolean estaLleno(){
        return this.size == this.limite;
    }
    
    public void encolar(E usuario) throws Exception{
        if(!estaLleno()){
            Nodo nuevo_nodo = new Nodo();
            nuevo_nodo.ingresar(usuario);
            if (!esVacio()){
                this.fondo.siguiente = nuevo_nodo;
            }
            else{
                this.frente = nuevo_nodo;
            }
            this.size++;
            this.fondo = nuevo_nodo;
        }
        else{
            throw new Exception("Cola Llena");  
        }   
    }
    
    public Nodo desencolar() throws Exception{
        Nodo nodo_retorno;
        if(esVacio()){
            throw new Exception("Cola Vacía");
        }
        else{
            nodo_retorno = this.frente;
            this.frente = frente.siguiente;
        }
        this.size--;
        return nodo_retorno;
    }
}