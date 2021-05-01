package ArbolBinario;
import Pilas_y_Colas.*;

public class ArbolBinario <E>{
    protected Nodo raiz;
    
    public ArbolBinario(){
        this.raiz = null;
    }
    
    public ArbolBinario(Nodo valor){
        this.raiz = valor;
    }
    
    public Nodo raizArbol(){
        return this.raiz;
    }
    
    public boolean esVacio(){
        return this.raiz == null;
    }
    
    public Nodo nuevoArbol(Nodo ramaIzqda, E dato, Nodo ramaDrcha){
        return new Nodo(ramaIzqda, dato, ramaDrcha);
    }
    
    public int contarHojas(Nodo subRaiz){
        int n = 0;
        if (subRaiz != null){
            if (subRaiz.izquierdo == null && subRaiz.derecho == null){
                return 1;
            }
            else{
                n = contarHojas(subRaiz.subArbolIzq()) + contarHojas(subRaiz.subArbolDcho());
            }
        }
        return n;
    }
    
    public int contarNodos(Nodo subRaiz){
        if (subRaiz == null){
            return 0;
        }
        else{
            return 1 + contarNodos(subRaiz.subArbolIzq()) + contarNodos(subRaiz.subArbolDcho());
        }
    }
    
    public int calcularAltura(Nodo subRaiz){
        if (contarNodos(subRaiz) == 0){
            return 0;
        }
        else{
            return (int) Math.ceil((Math.log10(this.contarNodos(subRaiz)) / Math.log10(2)) + 1);
        }
    }
    
    public Cola porNivel(Nodo subRaiz) throws Exception{
        Cola procesamiento = new Cola();
        Cola resultado = new Cola();
        if (subRaiz != null){
            procesamiento.encolar(subRaiz);
            while (!procesamiento.esVacio()){
                Nodo analizado = (Nodo) procesamiento.desencolar().info;
                if (analizado.subArbolIzq() != null){
                    procesamiento.encolar(analizado.subArbolIzq());
                    analizado.ramaIzq(null);
                }
                if (analizado.subArbolDcho() != null){
                    procesamiento.encolar(analizado.subArbolDcho());
                    analizado.ramaDcho(null);
                }
                resultado.encolar(analizado);
            }
        }
        return resultado;
    }
    public void preorden(Nodo subRaiz){
        if (subRaiz != null){
            subRaiz.visitar();
            preorden(subRaiz.subArbolIzq());
            preorden(subRaiz.subArbolDcho());
        }
    }
    
    public void inorden(Nodo subRaiz){
        if (subRaiz != null){
            inorden(subRaiz.subArbolIzq());
            subRaiz.visitar();
            inorden(subRaiz.subArbolDcho());
        }
    }
    
    public void postorden(Nodo subRaiz){
        if (subRaiz != null){
            postorden(subRaiz.subArbolIzq());
            postorden(subRaiz.subArbolDcho());
            subRaiz.visitar();
        }
    }
    
    public static void main(String[] args) throws Exception{
        Pila pila_nodos = new Pila();
        Nodo a, a1, a2;
        ArbolBinario arbol_binario = new ArbolBinario();
        
        a1 = arbol_binario.nuevoArbol(null, "María",null);
        a2 = arbol_binario.nuevoArbol(null, "Rodrigo",null);
        a = arbol_binario.nuevoArbol(a1, "Esperanza",a2);
        
        pila_nodos.push(a);
        
        a1 = arbol_binario.nuevoArbol(null, "Anyora",null);
        a2 = arbol_binario.nuevoArbol(null, "Abel",null);
        a = arbol_binario.nuevoArbol(a1, "M Jesus",a2);
        
        pila_nodos.push(a);
        
        a1 = (Nodo) pila_nodos.pop().info;
        a2 = (Nodo) pila_nodos.pop().info;
        a = arbol_binario.nuevoArbol(a1, "Esperanza", a2);
        
        ArbolBinario nuevo_arbol = new ArbolBinario(a);
        
        System.out.println(nuevo_arbol.calcularAltura(nuevo_arbol.raiz));
    }
        
}