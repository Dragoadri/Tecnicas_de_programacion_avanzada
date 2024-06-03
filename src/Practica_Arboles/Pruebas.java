package Practica_Arboles;

/**
 * @author1 Cañadas Gallardo, Adrian
 * @expediente1 22004996
 *
 * @author2 Apellido3 Apellido4, Nombre
 * @expediente2 87654321
 *
 * @date 2024-04-30
 * @version 0.1
 * @param <T> 
 */

//imports aquí
import java.util.LinkedList;

public class Pruebas<T> {

	/**
	 * FUNCION GRADO ARBOL
	 * 
	 * @param <T> Tipo de los elementos del árbol
	 * @param a   Árbol del que se va a calcular el grado
	 * @return Devuelve el grado del árbol
	 * 
	 *         Esta función calcula el grado de un árbol, para ello llama a la
	 *         función gradoArbolMax y devuelve el valor máximo de los grados de los
	 *         hijos del árbol
	 */

	private static <T> int gradoArbol(Arbol_N<T> a) {
		// Si el árbol está vacío, devuelve 0
		// Si no, llama a la función gradoArbolMax
		return a.esVacio() ? 0 : gradoArbolMax(a);
	}

	private static <T> int gradoArbolMax(Arbol_N<T> a) {
		int grado = a.getNumHijos();

		// Recorre los hijos del árbol y llama a la función de forma recursiva
		// para obtener el grado máximo de los hijos
		for (Arbol_N<T> hijo : a.getHijos()) {
			grado = Math.max(grado, gradoArbol(hijo));
		}
		// Devuelve el grado máximo
		return grado;
	}


	/**
	 * 
	 * FUNCION ESABB
	 * 
	 * @param a Es el árbol que se va a comprobar si es ABB
	 * @return Devuelve true si el árbol es ABB y false si no lo es
	 * 
	 * 
	 *         Segun he hablado con el profesor, y a mi entendimiento un nodo hoja
	 *         tendra un UNICO hijo, que sera un arbol vacio.
	 * 
	 *         Por lo tanto, si el arbol tiene 2 hijos, se comprueba si uno de los
	 *         hijos es vacio Es decir si el nodo tiene almenos un hijo con
	 *         contenido, tendra otro que tambien tendra contenido o sera vacio
	 * 
	 * 
	 */
	
	private static boolean esABB(Arbol_N<Integer> a) {

		// Si el árbol está vacío, devuelve true
		if (a.esVacio()) {
			return true;
		
		//Si el arbol tiene 2 hijos
		} else if (a.getNumHijos() == 2) {

			Arbol_N<Integer> izq = a.getHijoN(1);
			Arbol_N<Integer> der = a.getHijoN(2);

			//se comprueba si uno de los hijos es vacio
			if (izq.esVacio() || der.esVacio()) {

				//si el hijo izquierdo es vacio, se comprueba si el hijo derecho es mayor que la raiz
				//y si el hijo derecho es ABB
				
				//si el hijo derecho es vacio, se comprueba si el hijo izquierdo es menor que la raiz
				//y si el hijo izquierdo es ABB
				return izq.esVacio() ? der.getRaiz() > a.getRaiz() && esABB(der)
						: izq.getRaiz() < a.getRaiz() && esABB(izq);

			} else {
				//si ambos hijos tienen contenido, se comprueba si el hijo izquierdo es menor que la raiz
				//y si el hijo derecho es mayor que la raiz
				//si alguno de los hijos no cumple la condicion, se devuelve false
				
				if (izq.getRaiz() >= a.getRaiz() || der.getRaiz() <= a.getRaiz()) {
					return false;
				} else {

					//se obtienen los valores minimos y maximos de los arboles
					
					//Orden de complejidad 4*O(n) siendo n el numero de nodos del arbol introducido
					
					int minHijo1 = getMinInArbol_N(izq); 
					int maxHijo1 = getMaxInArbol_N(izq);
					int minHijo2 = getMinInArbol_N(der);
					int maxHijo2 = getMaxInArbol_N(der);
					
					//se comprueba si el hijo izquierdo es menor que la raiz
					//y si el hijo derecho es mayor que la raiz
					//y si el hijo izquierdo es menor que el hijo derecho
					//y si el hijo izquierdo es ABB
					//y si el hijo derecho es ABB

					return maxHijo1 < a.getRaiz() && minHijo2 > a.getRaiz() && minHijo1 < minHijo2
							&& maxHijo1 < maxHijo2 && esABB(izq) && esABB(der);
				}
			}

		} else {
			// Si el árbol tiene más de 2 hijos, devuelve false ya que no es un ABB al tener
			// más de 2 hijos
			// Si el árbol tiene 1 hijo, devuelve true ya que es un ABB (nodo hoja)
			return !(a.getNumHijos() > 2);
		}

	}

	/**
	 * FUNCION SONIGUALES
	 * 
	 * @param <T> Tipo de los elementos del árbol
	 * @param a   Primer árbol
	 * @param b   Segundo árbol
	 * @return Devuelve true si los árboles son iguales y false si no lo son
	 * 
	 *         Este metodo comprueba si dos arboles son iguales, para ello comprueba
	 *         si la raiz de ambos arboles es igual
	 */

	private static <T> boolean sonIguales(Arbol_N<T> a, Arbol_N<T> b) {

		// Si ambos árboles son vacíos, devuelve true, si no, llama a la función
		return (a.esVacio() && b.esVacio()) ? true : compruebaIguales(a, b);
	}

	private static <T> boolean compruebaIguales(Arbol_N<T> a, Arbol_N<T> b) {

		// Booleano que comprueba si las raíces de los árboles son iguales
		boolean sonIguales = a.getRaiz().equals(b.getRaiz());

		// Si el número de hijos de los árboles es igual y las raíces son iguales
		if (a.getNumHijos() == b.getNumHijos() && sonIguales) {

			// Indice para el while
			int i = 1;

			// Mientras los hijos sean iguales y no se haya recorrido todos los hijos
			while (sonIguales && i <= a.getNumHijos()) {

				// Se llama a la función de forma recursiva
				sonIguales = sonIguales && sonIguales(a.getHijoN(i), b.getHijoN(i));

				// Se incrementa el índice
				i++;
			}
		} else {

			// Si no se cumple la condición, se pone el booleano en false
			sonIguales = false;
		}
		return sonIguales;
	}

	/**
	 * FUNCION GETMININARBOL_N
	 * 
	 * @param a Arbol del que sacar el valor mínimo
	 * @return Devuelve el valor mínimo del árbol
	 * 
	 *         Esta función recorre el árbol de forma recursiva y devuelve el valor
	 *         mínimo de las raíces de los nodos del árbol
	 */
	private static int getMinInArbol_N(Arbol_N<Integer> a) {
		if (a.esVacio()) {
			// Si el árbol está vacío, devuelve el valor máximo de integer
			return Integer.MAX_VALUE;
		}
		
		// Se obtiene la raíz del árbol
		int min = a.getRaiz();
		// Se recorren los hijos del árbol
		//Para cada hijo se llama a la función de forma recursiva
		//Nos quedamos con el valor mínimo de los hijos
		for (Arbol_N<Integer> hijo : a.getHijos()) {
			min = hijo.esVacio() ? min : Math.min(min, getMinInArbol_N(hijo));
		}
		return min;

	}

	/**
	 * FUNCION GETMAXINARBOL_N
	 * 
	 * @param a Arból del que sacar el valor máximo
	 * @return Devuelve el valor máximo del árbol
	 * 
	 *         Esta función recorre el árbol de forma recursiva y devuelve el valor
	 *         máximo de las raíces de los nodos del árbol
	 */

	private static int getMaxInArbol_N(Arbol_N<Integer> a) {
		// Si el árbol está vacío, devuelve el valor mínimo de integer
		if (a.esVacio()) {
			return Integer.MIN_VALUE;
		}
		// Se obtiene la raíz del árbol
		// Se recorren los hijos del árbol
		// Para cada hijo se llama a la función de forma recursiva
		// Nos quedamos con el valor máximo de los hijos
		int max = a.getRaiz();
		for (Arbol_N<Integer> hijo : a.getHijos()) {
			max = hijo.esVacio() ? max : Math.max(max, getMaxInArbol_N(hijo));
		}
		return max;
	}
	
	//funcion que diga en que nivel se encuentra un dato buiscado, si no lo encuentra devuelve 0
	//si lo encuentra devuelve el nivel
	
	public static int nivelDato(Arbol_N<Integer> a, int dato) {
		return enNivel(a, dato, 1);
	}

	private static int enNivel(Arbol_N<Integer> a, int dato, int l) {
		if (a.esVacio()) {
			return -1;
		}
		if (a.getRaiz() == dato) {
			return l;
		}
		
		int nivel = 0;
		int i=0;
		boolean encontrado = false;
		
		while (i < a.getNumHijos() && !encontrado) {
			nivel = enNivel(a.getHijoN(i), dato, l + 1);
			encontrado = nivel != -1 && nivel != 0;
			i++;
		}
		
		return encontrado ? nivel : 0;
	}
	

	public static void main(String[] args) {
		
		
		
		

		// _______________________________________________ pruebas gradoArbol

		/* EJEMPLO:
		 * 			  x-------- 1 -------x
		 *			  |	       / \       |
		 * 			  2		  3	  4      5
		 * 			  |	     / \       / | \
		 * 			  6	    7   8	  9  10  11
		 */


		LinkedList<Arbol_N<Integer>> lista = new LinkedList<Arbol_N<Integer>>();
		LinkedList<Arbol_N<Integer>> listaVacia = new LinkedList<Arbol_N<Integer>>();
		listaVacia.add(new Arbol_N<Integer>());

		Arbol_N<Integer> once = new Arbol_N<Integer>(11, listaVacia);
		Arbol_N<Integer> diez = new Arbol_N<Integer>(10, listaVacia);
		Arbol_N<Integer> nueve = new Arbol_N<Integer>(9, listaVacia);

		lista.add(nueve);
		lista.add(diez);
		lista.add(once);

		Arbol_N<Integer> cinco = new Arbol_N<Integer>(5, lista);

		LinkedList<Arbol_N<Integer>> lista2 = new LinkedList<Arbol_N<Integer>>();

		Arbol_N<Integer> ocho = new Arbol_N<Integer>(8, listaVacia);
		Arbol_N<Integer> siete = new Arbol_N<Integer>(7, listaVacia);

		lista2.add(ocho);
		lista2.add(siete);

		Arbol_N<Integer> tres = new Arbol_N<Integer>(3, lista2);

		LinkedList<Arbol_N<Integer>> lista3 = new LinkedList<Arbol_N<Integer>>();

		Arbol_N<Integer> seis = new Arbol_N<Integer>(6, listaVacia);

		lista3.add(seis);

		Arbol_N<Integer> dos = new Arbol_N<Integer>(2, lista3);

		LinkedList<Arbol_N<Integer>> lista4 = new LinkedList<Arbol_N<Integer>>();

		Arbol_N<Integer> cuatro = new Arbol_N<Integer>(4, listaVacia);

		lista4.add(dos);
		lista4.add(tres);
		lista4.add(cuatro);
		lista4.add(cinco);

		Arbol_N<Integer> uno = new Arbol_N<Integer>(1, lista4);

		System.out.println("\n____________GRADO ARBOL____________\n");
		System.out.println("GRADO ARBOL: " + gradoArbol(uno));
		System.out.println("\n__________FIN GRADO ARBOL__________\n\n");

		
		// _______________________________________________ pruebas nivelDato

		System.out.println("\n____________NIVEL DATO____________\n");
		System.out.println("NIVEL DATO (1): " + nivelDato(uno, 1));
		
		System.out.println("NIVEL DATO (2): " + nivelDato(uno,11));
//_______________________________________________ pruebas esABB

		LinkedList<Arbol_N<Integer>> lista6 = new LinkedList<Arbol_N<Integer>>();
		LinkedList<Arbol_N<Integer>> lista7 = new LinkedList<Arbol_N<Integer>>();
		LinkedList<Arbol_N<Integer>> lista8 = new LinkedList<Arbol_N<Integer>>();
		LinkedList<Arbol_N<Integer>> lista9 = new LinkedList<Arbol_N<Integer>>();
		LinkedList<Arbol_N<Integer>> lista10 = new LinkedList<Arbol_N<Integer>>();

		lista10.add(new Arbol_N<Integer>());
		lista10.add(new Arbol_N<Integer>(9, listaVacia));

		lista9.add(new Arbol_N<Integer>());
		lista9.add(new Arbol_N<Integer>(6, lista10));

		lista8.add(new Arbol_N<Integer>(1, listaVacia));
		lista8.add(new Arbol_N<Integer>());

		lista7.add(new Arbol_N<Integer>(2, lista8));
		lista7.add(new Arbol_N<Integer>(5, lista9));

//		 lista7.add(new Arbol_N<Integer>(2, listaVacia));
//		 lista7.add(new Arbol_N<Integer>(5, listaVacia));

		Arbol_N<Integer> uno2 = new Arbol_N<Integer>(3, lista7);

		Arbol_N<Integer> siete2 = new Arbol_N<Integer>(10, listaVacia);

		lista6.add(uno2);
		lista6.add(siete2);

		Arbol_N<Integer> ABB2 = new Arbol_N<Integer>(8, lista6);

		
		// -------8-------
		// 		 / \
		// 		3 	10
		// 	   / \
		// 	  2   5
		// 	 / \ / \
		//  1   V   6
		// 		   / \
		// 		  V   9
			

		System.out.println("\n____________ABB____________\n");
		// System.out.println("ES ABB (uno): " + esABB(uno));
		System.out.println("ES ABB (ABB2): " + esABB(ABB2));
		System.out.println("\n__________FIN ABB__________\n\n");

		// _______________________________________________ pruebas sonIguales

		System.out.println("\n______PRUEBAS SON IGUALES_______\n");
		System.out.println("SON IGUALES: " + sonIguales(uno, uno));
		System.out.println("NO SON IGUALES: " + sonIguales(uno, ABB2));
		System.out.println("\n_____FIN PRUEBAS SON IGUALES___\n\n");

	}// main

}// Clase Pruebas