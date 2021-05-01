package ArbolAVL;

public class ArbolAVL <E>{
    public NodoAVL raiz;
    
    public ArbolAVL(){
        this.raiz = null;
    }
    
    public int altura(NodoAVL subRaiz){
        /** Método que calcula la altura de un ArbolAVL y devuelve un entero
         0 si es vacia
         n como la altura del ArbolAVL*/
	if (subRaiz == null){
            return 0;
	}
	else{
            return (int) Math.floor((Math.log10(this.contarNodos(subRaiz)) / Math.log10(2)) + 1);
	}
    }

    public void equilibrar(NodoAVL subRaiz){
        /** Método que calcula el factor de equilibrio de un nodo*/
	subRaiz.factor_equilibrio = this.altura(subRaiz.subArbolDcho()) - this.altura(subRaiz.subArbolIzdo());
    }

    public NodoAVL rebalancear(NodoAVL subRaiz){
        /** Método que calcula el equilibrio de una subRaiz y hace las rotaciones necesarias; 
          devuelve un NodoAVL*/
        equilibrar(subRaiz);
        
	int balance = subRaiz.factor_equilibrio;
        switch(balance){
            case 2:
		if(subRaiz.subArbolDcho().factor_equilibrio >= 0){
                    subRaiz = RI(subRaiz);
		}
		else{
                    subRaiz = RDI(subRaiz);
		}
		break;
            case -2:
		if(subRaiz.subArbolIzdo().factor_equilibrio <= 0){
                    subRaiz = RD(subRaiz);
		}
		else{
                    subRaiz = RID(subRaiz);
		}
                break;	
	}
	return subRaiz;
    }
    
    public void insertar(E dato, E key) throws Exception{
        NodoAVL dato_insertado = new NodoAVL(dato, (int) key);
        raiz = insertar(raiz, dato_insertado);
    }
    
    private NodoAVL insertar(NodoAVL subRaiz, NodoAVL dato) throws Exception{
        if (subRaiz == null){
            return dato;
        }
        else if((int) dato.ID < (int) subRaiz.ID){
            subRaiz.ramaIzda(insertar(subRaiz.subArbolIzdo(), dato));
        }
        else if((int) dato.ID > (int) subRaiz.ID){
            subRaiz.ramaDcha(insertar(subRaiz.subArbolDcho(), dato));
        }
        else{
            throw new Exception("Nodo duplicado");
        }
        return rebalancear(subRaiz);
    }
    
    public int contarHojas(NodoAVL subRaiz){
        int n = 0;
        if (subRaiz != null){
            if (subRaiz.izquierdo == null && subRaiz.derecho == null){
                return 1;
            }
            else{
                n = contarHojas(subRaiz.subArbolIzdo()) + contarHojas(subRaiz.subArbolDcho());
            }
        }
        return n;
    }
    
    public int contarNodos(NodoAVL subRaiz){
        if (subRaiz == null){
            return 0;
        }
        else{
            return 1 + contarNodos(subRaiz.subArbolIzdo()) + contarNodos(subRaiz.subArbolDcho());
        }
    }
     
    public int calcularAltura(NodoAVL subRaiz){
        if (contarNodos(subRaiz) == 0){
            return 0;
        }
        else{
            return (int) Math.floor((Math.log10(this.contarNodos(subRaiz)) / Math.log10(2)) + 1);
        }
    } 
    
    // Rotaciones
    
    private NodoAVL RI(NodoAVL subRaiz){
        /** Método que rota un NodoAVL a la izquierda de su hijo derecho;
         devuelve un NodoAVL, que es la nueva raiz*/
	NodoAVL nuevaRaiz = subRaiz.subArbolDcho();
        NodoAVL extra = nuevaRaiz.subArbolIzdo();
        
	nuevaRaiz.ramaIzda(subRaiz);
	subRaiz.ramaDcha(extra); 
        
        equilibrar(subRaiz);
        equilibrar(nuevaRaiz);
	return nuevaRaiz;
    }

    private NodoAVL RD(NodoAVL subRaiz){
        /** Método que rota un NodoAVL a la derecha de su hijo izquierdo;
         devuelve un NodoAVL, que es la nueva raiz*/
	NodoAVL nuevaRaiz = subRaiz.subArbolIzdo();
        NodoAVL extra = nuevaRaiz.subArbolDcho();

	nuevaRaiz.ramaDcha(subRaiz);
	subRaiz.ramaIzda(extra);
        
        equilibrar(subRaiz);
        equilibrar(nuevaRaiz);
	return nuevaRaiz;
    }

    private NodoAVL RID(NodoAVL subRaiz){
        /** Método de rotacion doble izquierda/derecha; devuelve un NodoAVL*/
	subRaiz.ramaIzda(RI(subRaiz.subArbolIzdo()));
	return RD(subRaiz);
    }

    private NodoAVL RDI(NodoAVL subRaiz){
        /** Método de rotacion doble derecha/izquierda; devuelve un NodoAVL*/
        subRaiz.ramaDcha(RD(subRaiz.subArbolDcho()));
	return RI(subRaiz);
    }
    
    // Recorridos
    
    public void preorden(NodoAVL subRaiz){
        if (subRaiz != null){
            subRaiz.visitar();
            preorden(subRaiz.subArbolIzdo());
            preorden(subRaiz.subArbolDcho());
        }
    }
    
    public void inorden(NodoAVL subRaiz){
        if (subRaiz != null){
            inorden(subRaiz.subArbolIzdo());
            subRaiz.visitar();
            inorden(subRaiz.subArbolDcho());
        }
    }
    
    public void postorden(NodoAVL subRaiz){
        if (subRaiz != null){
            postorden(subRaiz.subArbolIzdo());
            postorden(subRaiz.subArbolDcho());
            subRaiz.visitar();
        }
    }
}
