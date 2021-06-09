package GrafoMatriz;

public class Punto {
    double x;
    double y;
    
    public Punto(double compX, double compY){
        x = compX;
        y = compY;
    }
    
    public double distancia(Punto p2){
        return Math.sqrt(Math.pow(p2.x - x, 2) + Math.pow(p2.y - y, 2));
    }
}
