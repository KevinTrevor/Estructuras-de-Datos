package ArbolBusqueda;
import ArbolBinario.*;

public class ArbolBusqueda <E> extends ArbolBinario{
    
    public ArbolBusqueda(){
        super();
    }

    public Nodo buscar(E valor_buscado, E key){
        Nodo resultado = null;
        Nodo dato = new Nodo(valor_buscado, key);
        if (raiz == null){
            return resultado;
        }
        else{
            resultado = localizar(raiz,dato);
        }
        return resultado;
    }
    
    protected Nodo localizar(Nodo subRaiz, Nodo valor_buscado){
        if (subRaiz == null){
            return null;
        }
        if ((int) valor_buscado.ID == (int) subRaiz.ID){
            System.out.println(subRaiz.valor);
            return subRaiz; 
        }
        else if ((int) valor_buscado.ID < (int) subRaiz.ID){
            localizar(subRaiz.subArbolIzq(),valor_buscado);
        }
        else if ((int) valor_buscado.ID > (int) subRaiz.ID){
            localizar(subRaiz.subArbolDcho(),valor_buscado);
        }
        return subRaiz;
    }
    
    public void insertar(E valor, E key) throws Exception{    
        Nodo dato = new Nodo(valor, key);
        raiz = insertar(raiz,dato);
    }
    
    protected Nodo insertar(Nodo subRaiz, Nodo dato) throws Exception{
        if (subRaiz == null){
            subRaiz = dato;
        }
        else if ((int) dato.ID < (int) subRaiz.ID){
            Nodo iz;
            iz = insertar(subRaiz.subArbolIzq(),dato);
            subRaiz.ramaIzq(iz);
        }
        else if ((int) dato.ID > (int) subRaiz.ID){
            Nodo dr;
            dr = insertar(subRaiz.subArbolDcho(),dato);
            subRaiz.ramaDcho(dr);
        }
        else{
            throw new Exception("Nodo duplicado");
        }
        return subRaiz;             
    }
    
    public void eliminar(E valor, E key) throws Exception{
        Nodo dato = new Nodo(valor, key);
        raiz = eliminar(raiz,dato);
    }
    
    protected Nodo eliminar(Nodo subRaiz, Nodo dato) throws Exception{
        if(subRaiz == null){
            throw new Exception("No se encontró el valor a eliminar");
        }
        else if((int) dato.ID < (int) subRaiz.ID){
            Nodo iz;
            iz = eliminar(subRaiz.subArbolIzq(),dato);
            subRaiz.ramaIzq(iz);
        }
        else if((int) dato.ID > (int) subRaiz.ID){
            Nodo dr;
            dr = eliminar(subRaiz.subArbolDcho(),dato);
            subRaiz.ramaDcho(dr);
        }
        else{
            Nodo encontrado;
            encontrado = subRaiz;
            if (encontrado.subArbolIzq() == null){
                subRaiz = encontrado.subArbolDcho();
            }
            else if (encontrado.subArbolDcho() == null){
                subRaiz = encontrado.subArbolIzq();
            }
            else{
                encontrado = reemplazar(encontrado);
            }
            encontrado = null;
        }
        return subRaiz;
    }
    
    private Nodo reemplazar(Nodo subRaiz) {
        Nodo actual, posterior;
        posterior = subRaiz;
        actual = subRaiz.subArbolIzq();
        while (actual.subArbolDcho() != null){
            posterior = actual;
            actual = actual.subArbolDcho();
        }
        
        subRaiz.nuevoValor(actual.valorNodo());
        
        if(subRaiz == posterior){
            posterior.ramaIzq(actual.subArbolIzq()); 
        }
        else{
            posterior.ramaDcho(actual.subArbolIzq());
        }
        return actual;
    }
}
