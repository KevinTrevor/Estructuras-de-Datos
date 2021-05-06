package GrafoLista;

public class Arista {
    public int destino;
    public double peso;
    
    public Arista(int d){
        this.destino = d;
    }
    
    public Arista(int d, double p){
        this.destino = d;
        this.peso = p;
    }
    
    // Métodos propios
    
    public boolean equals(Arista a){
        return destino == a.destino;
    }
    
    // Getter 
    
    public int getDestino(){
        return this.destino;
    }
}
