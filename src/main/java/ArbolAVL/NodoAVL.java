package ArbolAVL;

public class NodoAVL <E>{
    public E ID;
    public int factor_equilibrio;
    public E valor;
    public NodoAVL izquierdo;
    public NodoAVL derecho;
    
    public NodoAVL(){
        this.ID = null;
	this.factor_equilibrio = 0;
	this.valor = null;
	this.izquierdo = null;
	this.derecho = null;
    }
    
    public NodoAVL(E dato, E key){
        this.ID = key;
	this.factor_equilibrio = 0;
	this.valor = dato;
	this.izquierdo = null;
        this.derecho = null;
    }
    
    public E valorNodo(){
	return this.valor;
    }

    public void nuevoValor(E dato, E key){
        this.ID = key;
	this.valor = dato;
    }

    public NodoAVL subArbolIzdo(){
	return this.izquierdo;
    }

    public NodoAVL subArbolDcho(){
	return this.derecho;
    }

    public void ramaIzda(NodoAVL nuevo_nodo){
	this.izquierdo = nuevo_nodo;
    }

    public void ramaDcha(NodoAVL nuevo_nodo){
	this.derecho = nuevo_nodo;
    }

    public void visitar(){
	System.out.println("Dato: " + this.valor);	
    }
}
