package ArbolBusqueda;
import ArbolBinario.*;

public class ArbolBusqueda <E> extends ArbolBinario{
    
    public ArbolBusqueda(){
        super();
    }

    public Nodo buscar(E valor_buscado){
        Nodo resultado = null;
        Numeros dato = (Numeros) valor_buscado;
        if (raiz == null){
            return resultado;
        }
        else{
            resultado = localizar(raiz,dato);
        }
        return resultado;
    }
    
    protected Nodo localizar(Nodo subRaiz, Numeros valor_buscado){
        if (subRaiz == null){
            return null;
        }
        if (valor_buscado.igualQue(subRaiz.valorNodo())){
            System.out.println(subRaiz.valor);
            return subRaiz; 
        }
        else if (valor_buscado.menorQue(subRaiz.valorNodo())){
            localizar(subRaiz.subArbolIzq(),valor_buscado);
        }
        else if (valor_buscado.mayorQue(subRaiz.valorNodo())){
            localizar(subRaiz.subArbolDcho(),valor_buscado);
        }
        return subRaiz;
    }
    
    public void insertar(E valor) throws Exception{    
        Numeros dato = (Numeros) valor;
        raiz = insertar(raiz,dato);
    }
    
    protected Nodo insertar(Nodo subRaiz, Numeros dato) throws Exception{
        if (subRaiz == null){
            subRaiz = new Nodo(dato);
        }
        else if (dato.menorQue(subRaiz.valorNodo())){
            Nodo iz;
            iz = insertar(subRaiz.subArbolIzq(),dato);
            subRaiz.ramaIzq(iz);
        }
        else if (dato.mayorQue(subRaiz.valorNodo())){
            Nodo dr;
            dr = insertar(subRaiz.subArbolDcho(),dato);
            subRaiz.ramaDcho(dr);
        }
        else{
            throw new Exception("Nodo duplicado");
        }
        return subRaiz;             
    }
    
    public void eliminar(E valor) throws Exception{
        Numeros dato = (Numeros) valor;
        raiz = eliminar(raiz,dato);
    }
    
    protected Nodo eliminar(Nodo subRaiz, Numeros dato) throws Exception{
        if(subRaiz == null){
            throw new Exception("No se encontró el valor a eliminar");
        }
        else if(dato.menorQue(subRaiz.valorNodo())){
            Nodo iz;
            iz = eliminar(subRaiz.subArbolIzq(),dato);
            subRaiz.ramaIzq(iz);
        }
        else if(dato.mayorQue(subRaiz.valorNodo())){
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
