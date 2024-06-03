package Ejercicios_de_arboles;


public class ARBOLBIN <T> {
	
	class NodoBin {
		T info;
		NodoBin hijoIzq;
		NodoBin hijoDcho;

		NodoBin(NodoBin hIzq, T info, NodoBin hDcho) {
			this.hijoIzq = hIzq;
			this.hijoDcho = hDcho;
			this.info = info;
		}
	}

	private NodoBin raiz;

	public ARBOLBIN() {
		raiz = null;
	}

	public ARBOLBIN(ARBOLBIN<T> subIzq, T infoRaiz, ARBOLBIN<T> subDcho) {
		NodoBin izq = subIzq == null? null : subIzq.raiz; 
		NodoBin dcho = subDcho == null? null : subDcho.raiz; 
		this.raiz = new NodoBin(izq, infoRaiz, dcho);
	}

	public boolean esVacio() {
		return raiz == null;
	}
	
	public T raiz() { //get
		return this.raiz.info;
	}

	public ARBOLBIN<T> hijoIzquierdo() {
		ARBOLBIN<T> subIzq = new ARBOLBIN<T>();
		subIzq.raiz = this.raiz.hijoIzq;

		return subIzq;
	}

	public ARBOLBIN<T> hijoDerecho() {
		ARBOLBIN<T> subDcho = new ARBOLBIN<T>();
		subDcho.raiz = this.raiz.hijoDcho;

		return subDcho;
	}
	
	public void dibujar(int nivel){
		
		if ( !this.esVacio() ){
			for (int i = 1; i<= nivel; i++)
				System.out.print("  ");
			System.out.println(this.raiz());
			this.hijoIzquierdo().dibujar(nivel+1);
			this.hijoDerecho().dibujar(nivel+1);			
		}
	}//dibujar

	public void insertar(int i) {
		
		if (this.esVacio()) {
            this.raiz = new NodoBin(null, (T) new Integer(i), null);
        } else {
            ARBOLBIN<T> hijo;
            
            if (i < (Integer) this.raiz.info) {
                hijo = this.hijoIzquierdo();
                hijo.insertar(i);
            } else {
                hijo = this.hijoDerecho();
                hijo.insertar(i);
            }
		
		
	}
		}//insertar}
				

	/*
	 * 			|-------1-------|
	 *  	|---2--| 		|---3---| 
	 *    |-4-|    x 	  |-5-|     x 
	 *    x   x			  6   x
	 */
	
	
	
	
}//class





