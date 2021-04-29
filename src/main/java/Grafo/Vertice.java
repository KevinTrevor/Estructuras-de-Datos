package Grafo;

public class Vertice <E>{
    /** Atributos de la clase Vertice*/
    public String ID;
    public E valor;
    public int numVert;
    
    public Vertice(String nom){
        ID = nom;
        numVert = -1;
    }
    
    // Métodos propios
    
    public boolean equals(Vertice v){
        return ID.equals(v.ID);
    }
    
    // Getter y Setter
    
    public void setID(String d){
        this.ID = d;
    }
    
    public String getID(){
        return ID;
    }
    
    public void setVert(int n){
        numVert = n;
    }
    
}
