package 	    ejarbol;

import java.util.LinkedList;

public class FuncionesEjec {

	public static void main(String[] args) {
		ARBOLBIN<Integer> d = new ARBOLBIN<Integer>(new ARBOLBIN<Integer>(), 4, new ARBOLBIN<Integer>());
		ARBOLBIN<Integer> f = new ARBOLBIN<Integer>(new ARBOLBIN<Integer>(), 6, new ARBOLBIN<Integer>());
		// ArbolBin<Character> e = new ArbolBin<Character>(new
		// ArbolBin<Character>(),'E',f);
		ARBOLBIN<Integer> e = new ARBOLBIN<Integer>(new ARBOLBIN<Integer>(), 5, f);
		ARBOLBIN<Integer> b = new ARBOLBIN<Integer>(d, 2, new ARBOLBIN<Integer>());
		ARBOLBIN<Integer> c = new ARBOLBIN<Integer>(e, 3, new ARBOLBIN<Integer>());

		
		
		
		//a y a2 son iguales
		ARBOLBIN<Integer> a = new ARBOLBIN<Integer>(b, 1, c);
		ARBOLBIN<Integer> a2 = new ARBOLBIN<Integer>(b, 1, c);
		
		
		ARBOLBIN<Integer> a3 = new ARBOLBIN<Integer>(d, 1, f);
		ARBOLBIN<Integer> a4 = new ARBOLBIN<Integer>(d, 1, new ARBOLBIN<Integer>());
	
		
		//Arbol binario de busqueda que tiene a la izquierda numeros menores que la raiz y a la derecha numeros mayores que la raiz
		ARBOLBIN<Integer> ArbolBin = new ARBOLBIN<Integer>(new ARBOLBIN<Integer>(), 1, new ARBOLBIN<Integer>());
		ARBOLBIN<Integer> ArbolBindeBusqueda = new ARBOLBIN<Integer>(new ARBOLBIN<Integer>(), 5, new ARBOLBIN<Integer>());
		ARBOLBIN<Integer> ArbolBindeBusqueda2 = new ARBOLBIN<Integer>(ArbolBindeBusqueda, 3, new ARBOLBIN<Integer>());
		
		
		
		
		
		
		

		/*	Arbol a y a2
		 * 			|-------1-------|
		 *  	|---2--| 		|---3---| 
		 *    |-4-|    x 	  |-5-|     x 
		 *    x   x			  6   x
		 */
		
		/*	Arbol a3
		 * 	        |-------1-------|
		 *  	|---4--| 		|---6---|
		 *  	x      x 	  	x       x
		 */
		
		/*	Arbol a4
		 *             |-------1-------|
		 *         |---4--| 		   x	
		 *         x      x
		 */
		
		// **************************** RECORRIDOS ********************************
		
		System.out.println("\n-------------------------RECORRIDOS-------------------------------------\n");
		
		System.out.println("Recorrido en PREORDEN: (Raiz, Izquierda, Derecha)");
		preorden(a);
		System.out.println("\n------------------------------------------------------------------------\n");
		System.out.println("\nRecorrido en INORDEN: (Izquierda, Raiz, Derecha)");
		inorden(a);
		System.out.println("\n------------------------------------------------------------------------\n");

		System.out.println("\nRecorrido en POSTORDEN: (Izquierda, Derecha, Raiz)");
		postorden(a);
		System.out.println("\n------------------------------------------------------------------------\n");

		
        // **************************** FUNCIONES ********************************
		
		
		System.out.println("\n-------------------------FUNCIONES-------------------------------------\n");
		
		System.out.println("\n\nNumero de nodos del arbol: " + contarNodos(a));
		
		System.out.println("\n------------------------------------------------------------------------\n");

		System.out.println("\nSuma de todos los nodos del arbol: " + sumarNodos(a));
		
		System.out.println("\n------------------------------------------------------------------------\n");

		System.out.println("\nNumero de nodos pares del arbol: " + cuanosSonpares(a));
		
		System.out.println("\n------------------------------------------------------------------------\n");

		System.out.println("\nNumero de nodos hoja del arbol: " + cuantosNodosHoja(a));
		
		System.out.println("\n------------------------------------------------------------------------\n");

		System.out.println("\nArboles iguales: " + arbolesIguales(a, a2));
		
		System.out.println("\n------------------------------------------------------------------------\n");

		System.out.println("\nProfundidad del arbol: " + profundidad(a));
		
		System.out.println("\n------------------------------------------------------------------------\n");

		System.out.println("\nArbol lleno: " + ArbolLleno(a3));
		
		System.out.println("\n------------------------------------------------------------------------\n");
		
		System.out.println("\n Imprimir en anchos: ");	
		imprimirEnAnchos(a);
		
		System.out.println("\n------------------------------------------------------------------------\n");
		System.out.println("\n Recorrido en anchura: ");
		recorridoEnAnchura(a);
		
		System.out.println("\nImprimir nivel 2: ");
		ImprimirNivel(a,1);
		
		System.out.println("\n------------------------------------------------------------------------\n");
		
		System.out.println("\n\nComparar busqueda de dato en arbol: ");
		System.out.println("Sin orden: "+buscardatoEnArbol(ArbolBindeBusqueda, 8));
		

		System.out.println("\n*** FIN ***");

	}
	
	
	public static boolean buscardatoEnArbol(ARBOLBIN<Integer> a, int x) {
		
	
		return a.esVacio() ? false
				: (a.raiz() == x ? true
						: buscardatoEnArbol(a.hijoIzquierdo(), x) || buscardatoEnArbol(a.hijoDerecho(), x));
		
	
	
	}
	
	// funcion q reciba arbol binario y devuelva la suma de todos los nodos del
	// arbol

	public static int sumarNodos(ARBOLBIN<Integer> a) {
		return (a.esVacio()) ? 0 :
			sumarNodos(a.hijoIzquierdo()) 
			+ a.raiz() + 
			sumarNodos(a.hijoDerecho());

	}

	public static int contarNodos(ARBOLBIN<Integer> a) {
		return (a.esVacio()) ? 0 : 
			(1 + 
			contarNodos(a.hijoIzquierdo()) + 
			contarNodos(a.hijoDerecho()));

	}// contarNodos

	// func que reciba arbolbin de numeros enteros y devuelva cuantos son pares
	public static int cuanosSonpares(ARBOLBIN<Integer> a) {
		return (a.esVacio()) ? 0
				: (a.raiz() % 2 == 0 ? 
						1 :
						0) + 
				cuanosSonpares(a.hijoIzquierdo()) + 
				cuanosSonpares(a.hijoDerecho());
	}

	public static int cuantosNodosHoja(ARBOLBIN<Integer> a) {
		return (a.esVacio()) ? 0
				: (a.hijoIzquierdo().esVacio() && a.hijoDerecho().esVacio() ? 1 : 0)
						+ cuantosNodosHoja(a.hijoIzquierdo()) + cuantosNodosHoja(a.hijoDerecho());
	}

	public static boolean arbolesIguales(ARBOLBIN<Integer> a, ARBOLBIN<Integer> b) {
		//Si ambos arboles estan vacios, entonces son iguales
		return ((a.esVacio() && b.esVacio()) ? true
				//Si no estan vacios, entonces son iguales si la raiz de ambos arboles es igual y los hijos izquierdo y derecho son iguales
				: a.raiz() == b.raiz() 
				&& arbolesIguales(a.hijoDerecho(), b.hijoDerecho())
				&& arbolesIguales(a.hijoIzquierdo(), b.hijoIzquierdo()));

	}

	public static int profundidad(ARBOLBIN<Integer> a) {
		//Si el arbol esta vacio, entonces la profundidad es 0
		return (a.esVacio()) ? 0 : 
			//Si no esta vacio, entonces la profundidad es 1 + el maximo entre la profundidad del hijo izquierdo y la profundidad del hijo derecho
			1 + 
			Math.max(
					profundidad(a.hijoIzquierdo()), 
					profundidad(a.hijoDerecho()));
	}
	
	//arbol binario
	
	public static boolean ArbolLleno(ARBOLBIN<Integer> a) {
		//Si el arbol esta vacio, entonces es lleno
		return (a.esVacio()) ? true
				//Si la profundidad del hijo izquierdo es igual a la profundidad del hijo derecho
				: profundidad(a.hijoIzquierdo()) == profundidad(a.hijoDerecho())
				//Y ambos hijos son arboles llenos
				&& ArbolLleno(a.hijoIzquierdo())
				&& ArbolLleno(a.hijoDerecho());
	}
	
	//funcion que recibe arbol de num enteros y un numero k, escribir por pantalla escribir por pantalla los numeros k que hay en el arbol
	public static void ImprimirNivel(ARBOLBIN<Integer> a,int k) {
	// si a esta vacio no imprimo nada
		if (!a.esVacio()) {
			//si k es el primer nivel imprimo solo dicha raiz
			if (k == 1) {
				System.out.print(a.raiz() + " ");
			} else {
				//como ya estoy bajando el nivel resto en 1 
				ImprimirNivel(a.hijoIzquierdo(), k-1);
				ImprimirNivel(a.hijoDerecho(), k-1);
			}
		}
		
	}
	
	public static void imprimirEnAnchos(ARBOLBIN<Integer> a) {
		int h = profundidad(a);
		for (int i = 1; i <= h; i++) {
			ImprimirNivel(a, i);
		}
	}
	
	
	public static void recorridoEnAnchura(ARBOLBIN<Integer> a) {
		LinkedList<ARBOLBIN<Integer>> cola = new LinkedList<ARBOLBIN<Integer>>();
		//Añado el arbol a la cola
		cola.add(a);
		//Mientras la cola no este vacia
		while (!cola.isEmpty()) {
			//Saco el primer elemento de la cola
			ARBOLBIN<Integer> nodo = cola.poll();
			//Imprimo la raiz del arbol
			System.out.print(nodo.raiz() + " ");
			
			//Añado los hijos del arbol a la cola
			if (!nodo.hijoIzquierdo().esVacio()) {
				//Añado el hijo izquierdo
				cola.add(nodo.hijoIzquierdo());
			}
			//Añado los hijos del arbol a la cola
			if (!nodo.hijoDerecho().esVacio()) {
				//Añado el hijo derecho
				cola.add(nodo.hijoDerecho());
			}
		}
	}
	
	//sabiendio que tenemos esABB ver si el erbole es AVL
	public static boolean esAVL(ARBOLBIN<Integer> a) {
	if(!esABB(a)) {
		return false;
		
		} else {
			return Math.abs(profundidad(a.hijoIzquierdo()) - profundidad(a.hijoDerecho())) <= 1
					&& esAVL(a.hijoIzquierdo()) && esAVL(a.hijoDerecho());
		}
	}

	
    // **************************** Arbol Bin de Busqueda ********************************
	
	
	/**
	 * Codificar el método buscar() para que, dado un elemento tipo T,
		indique si está o no dentro del ABB
	 * @param a = arbol
	 * @param x = numero a buscar
	 */
	
	
    public static boolean esABB(ARBOLBIN<Integer> a) {
    	return true;
    }
	
	/**
	 * Codificar el método buscar2() para que, dado un elemento de tipo T,
		devuelva un árbol vacío si no lo encuentra, y en caso contrario un ABB
		cuya raíz sea el elemento buscado.
		
		        * @param a = arbol
		        * @param x = numero a buscar
		        * @return arbol
		        * 
	 */
	
	public ARBOLBIN<Integer> buscar2(ARBOLBIN<Integer> a, int x) {
		if (a.esVacio()) {
			return new ARBOLBIN<Integer>();
		} else {
			if (a.raiz() == x) {
				return a;
			} else {
				return buscar2((x < a.raiz()) ? a.hijoIzquierdo() : a.hijoDerecho(), x);
			
			}
		}
	}
	
	
	
	/**
	 * Recorrido en PREORDEN: en este recorrido se visita: primero la raiz, luego el
	 * hijo izquierdo y finalmente el hijo derecho.
	 */
	public static void preorden(ARBOLBIN<Integer> a) {

		if (!a.esVacio()) {
			System.out.print(a.raiz() + " ");
			preorden(a.hijoIzquierdo());
			preorden(a.hijoDerecho());
		}

	}// preorden

	/**
	 * Recorrido en INORDEN: en este recorrido se visita: primero el hijo izquierdo,
	 * luego la raiz y finalmente el hijo derecho.
	 */

	public static void inorden(ARBOLBIN<Integer> a) {

		if (!a.esVacio()) {
			inorden(a.hijoIzquierdo());
			System.out.print(a.raiz() + " ");
			inorden(a.hijoDerecho());
		}

	}// inorden

	/**
	 * Recorrido en POSTORDEN: en este recorrido se visita: primero el hijo
	 * izquierdo, luego el hijo derecho y finalmente la raiz.
	 */

	public static void postorden(ARBOLBIN<Integer> a) {

		if (!a.esVacio()) {
			postorden(a.hijoIzquierdo());
			postorden(a.hijoDerecho());
			System.out.print(a.raiz() + " ");
		}

	}// postorden

}
