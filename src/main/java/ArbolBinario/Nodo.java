package ArbolBinario;

public class Nodo <E>{
    public Nodo izquierdo;
    public Nodo derecho;
    public E valor;
    
    public Nodo(){
        this.izquierdo = null;
        this.derecho = null;
        this.valor = null;
    }
    public Nodo(E dato){
        this.izquierdo = null;
        this.derecho = null;
        this.valor = dato;
    }
        
    public Nodo(Nodo ramaIzqda, E dato, Nodo ramaDrcha){
        this.valor = dato;
        this.derecho = ramaDrcha;
        this.izquierdo = ramaIzqda;      
    }
        
    public E valorNodo(){
        return this.valor;
    }
        
    public boolean esVacio(){
        return this.valor == null;
    }
        
    public Nodo subArbolIzq(){
        return this.izquierdo;
    }
        
    public Nodo subArbolDcho(){
        return this.derecho;
    }
        
    public void nuevoValor(E dato){
        this.valor = dato;
    }
        
    public void ramaDcho(Nodo dcho){
        this.derecho = dcho;
    }
        
    public void ramaIzq(Nodo izq){
        this.izquierdo = izq;
    }
    
    public void visitar(){
        System.out.println("Dato: " + valor);
    }
}  