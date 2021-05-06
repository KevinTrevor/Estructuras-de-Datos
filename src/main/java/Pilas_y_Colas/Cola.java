package Pilas_y_Colas;

public class Cola <E>{
    public Nodo frente;
    public Nodo fondo;
    public int size;
    
    public Cola(){
        this.frente = null;
        this.fondo = null;
        this.size = 0;
    }
    
    public boolean esVacio(){
        return this.frente == null;
    }
    
    public void encolar(E usuario) throws Exception{
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
    
    public E desencolar() throws Exception{
        Nodo nodo_retorno;
        if(esVacio()){
            throw new Exception("Cola Vacía");
        }
        else{
            nodo_retorno = this.frente;
            this.frente = frente.siguiente;
            this.size--;
        }
        return (E) nodo_retorno.info;
    }
}