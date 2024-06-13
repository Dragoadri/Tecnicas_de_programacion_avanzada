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

	public static <T> int gradoArbol(Arbol_N<T> a) {
		return a.esVacio() ? 0 : gradoArbolMax(a);
	}

	private static <T> int gradoArbolMax(Arbol_N<T> a) {
		int grado = a.getNumHijos();

		for (Arbol_N<T> hijo : a.getHijos()) {
			grado = Math.max(grado, gradoArbol(hijo));
		}

		return grado;
	}
	
	
	
//	private static <T> int gradoArbol1(Arbol_N<T> a) {
//
//		// Al principio el grado es 0
//		int grado = 0;
//
//		// Si el árbol no está vacío
//		if (!a.esVacio()) {
//
//			// Booleano que comprueba si todos los hijos son hoja
//			boolean todosLosHijosSonHoja = true;
//
//			// Indice para el while
//			int i = 1;
//
//			// Mientras todos los hijos sean hoja y no se haya recorrido todos los hijos
//			while (todosLosHijosSonHoja && i <= a.getNumHijos()) {
//
//				// Si el hijo es vacío,el boolenao se mantiene en true
//				// Si el hijo no es vacío, se comprueba si el hijo tiene un único hijo
//				// lo que quiere decir que es hoja y se mantiene en true sino, se pone en false
//				todosLosHijosSonHoja = a.getHijoN(i).esVacio() ? todosLosHijosSonHoja
//						: a.getHijoN(i).getNumHijos() == 1;
//
//				// Se incrementa el índice
//				i++;
//			}
//
//			// Si todos los hijos son hoja, el grado es el número de hijos
//			if (todosLosHijosSonHoja) {
//				grado = a.getNumHijos();
//				// Si no, se recorre los hijos y se llama a la función de forma recursiva
//			} else {
//				for (Arbol_N<T> hijo : a.getHijos()) {
//					grado = Math.max(grado, gradoArbol(hijo));
//				}
//			}
//		}
//		return grado;
//
//	}

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

		return
		// Comprueba si el árbol es vacío y si lo es devuelve true
		a.esVacio() ? true :

		// Comprueba si el árbol tiene 2 hijos
				(a.getNumHijos() == 2) ?

				// Comprueba si uno de los hijos es vacío
						((a.getHijoN(1).esVacio() || a.getHijoN(2).esVacio()) ?

						// Si uno de los hijos es vacío, comprueba si el izquierdo es vacío
								(a.getHijoN(1).esVacio()) ?

								// Si el hijo izquierdo es vacío, comprueba si el hijo derecho es mayor que la
								// raíz
								// y si el hijo derecho es ABB
										a.getHijoN(2).getRaiz() > a.getRaiz() &&
										
										esABB(a.getHijoN(2)) :

										// Como el jizquierdo no es vacío, comprueba si el hijo izquierdo es menor que
										// la raíz
										// y si el hijo izquierdo es ABB
										a.getHijoN(1).getRaiz() < a.getRaiz() &&
										getMinInArbol_N(a.getHijoN(1)) < a.getRaiz() &&
										esABB(a.getHijoN(1))
								:

									// Si ambos hijos no son vacíos, comprueba si el hijo izquierdo es menor que la
								// raíz
								// y si el hijo derecho es mayor que la raíz
								// devuelve false si no se cumple la condición ^^^^^^
								(a.getHijoN(1).getRaiz() >= a.getRaiz() || a.getHijoN(2).getRaiz() <= a.getRaiz())
										? false
										:

										// Comprueba que los hijos sean ABB
										// y que todo lo que haya a la derecha sea
										// menor que lo que haya a la izquierda
										// y que todo lo que haya a la izquierda sea
										// menor que lo que haya a la derecha

											getMaxInArbol_N(a.getHijoN(1)) < a.getRaiz() &&
											getMinInArbol_N(a.getHijoN(2)) > a.getRaiz() &&
											    
												getMinInArbol_N(a.getHijoN(1)) < getMinInArbol_N(a.getHijoN(2))
												&& getMaxInArbol_N(a.getHijoN(1)) < getMaxInArbol_N(a.getHijoN(2))
												&& esABB(a.getHijoN(1)))
												&& esABB(a.getHijoN(2))
												
												
						:

						// si el arbol tiene mas de 2 hijos, ya no sera ABB
						!(a.getNumHijos() > 2);

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
		
		//Booleano que comprueba si las raíces de los árboles son iguales
		boolean sonIguales = a.getRaiz().equals(b.getRaiz());
		
		//Si el número de hijos de los árboles es igual y las raíces son iguales
		if (a.getNumHijos() == b.getNumHijos() && sonIguales) {

			//Indice para el while
			int i = 1;
			
			//Mientras los hijos sean iguales y no se haya recorrido todos los hijos
			while (sonIguales && i <= a.getNumHijos()) {
				
				//Se llama a la función de forma recursiva
				sonIguales = sonIguales && sonIguales(a.getHijoN(i), b.getHijoN(i));
				
				//Se incrementa el índice
				i++;
			}
		} else {
			
			//Si no se cumple la condición, se pone el booleano en false
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
            return Integer.MAX_VALUE;
        }

		int min = a.getRaiz();
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
		if (a.esVacio()) {
			return Integer.MIN_VALUE;
		}
		int max = a.getRaiz();
		for (Arbol_N<Integer> hijo : a.getHijos()) {
			max = hijo.esVacio() ? max : Math.max(max, getMaxInArbol_N(hijo));
		}
		return max;
	}
	
	
	
	
	public static void main(String[] args) {

		// _______________________________________________ pruebas gradoArbol

		/* ARBOL UNO:
		 * 			   -------- 1 -------
		 *			  |	       / \       |
		 * 			  2		  3	  4      5
		 * 			  |	     / \       / | \
		 * 			  6	    7   8	  9	 10 11
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
		
		System.out.println("\n_GRADO ARBOL_\n");
		System.out.println("GRADO ARBOL: " + gradoArbol(uno));
		System.out.println("GRADO ARBOL: " + gradoArbol(cuatro));
		System.out.println("GRADO ARBOL: " + gradoArbol(new Arbol_N<Integer>()));
		System.out.println("\n_FIN GRADO ARBOL_\n\n");

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
		//  |    \ / \
		//  V     V   9

		
	
		
		System.out.println("\n_NIVEL DATO_\n");
		//System.out.println("NIVEL DATO 1: " + nivelDato(uno, 1));
		//System.out.println("NIVEL DATO 1: " + nivelDato(uno, 11));

		
	}// main

}// Clase Pruebas