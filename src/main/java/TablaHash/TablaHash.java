package TablaHash;

import java.util.*;


public class TablaHash <C, V>{
    public LinkedList<V>[] tabla;
    public int size;
    
    public TablaHash(int max){
        size = max;
        tabla = new LinkedList[size];
    }
    
    public int hash(String clave){
        int valorNumerico = 0;
        for(int i = 0; i < clave.length(); i++){
            valorNumerico = valorNumerico + Character.getNumericValue(clave.charAt(i));
        }
        return valorNumerico % size;
    }
}
