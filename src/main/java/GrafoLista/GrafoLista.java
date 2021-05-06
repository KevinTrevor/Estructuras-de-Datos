package GrafoLista;

public class GrafoLista {
    public int numVerts;
    public int tamMax;
    public VerticeAdy[] listAdy;
    
    public GrafoLista(int max){
        this.numVerts = 0;
        this.listAdy = new VerticeAdy[max];
        this.tamMax = max;
    }
}
