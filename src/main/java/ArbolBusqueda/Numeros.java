package ArbolBusqueda;

public class Numeros extends Comparador{
    public int valor;
    
    public Numeros(){
        this.valor = 0;
    }
    
    public Numeros(int dato){
        this.valor = dato;
    }
    public void mostrar(){
        System.out.println(this.valor);
    }
    @Override
    public boolean igualQue(Object q) {
        Numeros n2 = (Numeros) q;
        return this.valor == n2.valor;
    }

    @Override
    public boolean mayorQue(Object q) {
        Numeros n2 = (Numeros) q;
        return this.valor > n2.valor;
    }

    @Override
    public boolean mayorIgualQue(Object q) {
        Numeros n2 = (Numeros) q;
        return this.valor >= n2.valor;
    }

    @Override
    public boolean menorQue(Object q) {
        Numeros n2 =  (Numeros) q;
        return this.valor < n2.valor;
    }

    @Override
    public boolean menorIgualQue(Object q) {
        Numeros n2 = (Numeros) q;
        return this.valor <= n2.valor;
    }    
}
