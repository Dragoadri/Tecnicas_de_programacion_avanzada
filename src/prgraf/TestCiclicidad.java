package prgraf;

/**
* @author1 CAÑADAS GALLARDO, ADRIAN
* @expediente1 22004996
*  
* @author2 Farhat, Mohamed Oussama
* @expediente2 22052494
*
* @date 2024-05-14
* @version 0.1
* 
*/

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class TestCiclicidad {

	public static <Clave, InfoVertice, Coste> boolean testCiclos(Grafo<Clave, InfoVertice, Coste> grafo) {

		/**
		 * Utilizamos una LinkedKist para almacenar los vértices visitados en la BPD
		 * (Búsqueda en Profundidad) HashSet es una colección que no permite elementos
		 * duplicados y no garantiza el orden de los elementos basicamente Esto nos
		 * vienee muuy bien para ver si efectivamente hemos pasado por un vértice o no y
		 * habra ciclo o no
		 * 
		 */

		//Set<Clave> visitados = new HashSet<>();
		
		LinkedList<Clave> visitados = new LinkedList<>();

		/**
		 * Comprobamos si hay un ciclo a si mismo en el grafo o un ciclo en profundidad
		 * 
		 * Le pasamos el primer vértice del grafo para que empiece a recorrer desde ahi
		 * 
		 */
		boolean cicloASiMismo = cicloASiMismo(grafo);
		boolean cicloEnProfundidad = cicloEnProfundidad(grafo, visitados, grafo.listaVertices().consultar(1));

		// Si se encontró un ciclo en el grafo, se retorna true
		return cicloASiMismo || cicloEnProfundidad;

	}

	/**
	 * Método auxiliar para detectar ciclos en un grafo que no sean a si mismo.
	 * Basado en un recorrido en profundidad. Primero miramos si el vertice actual
	 * está en visitados y si no lo está podemos recorrer en profundidad
	 * 
	 * @param grafo     Grafo en el que se buscarán ciclos.
	 * @param visitados Conjunto de vértices visitados en el recorrido.
	 * @param clave    Vértice actual en el recorrido.
	 * @return true si se encontró un ciclo, false en caso contrario.
	 */

	private static <Clave, InfoVertice, Coste> boolean cicloEnProfundidad(Grafo<Clave, InfoVertice, Coste> grafo,
			LinkedList<Clave> visitados, Clave clave) {
		boolean ciclo = false;

		int i = 1;
		// Recorremos los vértices del grafo y mientras no haya ciclo y no hayamos
		// recorrido todos los vértices
		while (i <= grafo.listaVertices().longitud() && !ciclo) {

			// Si el vértice actual no está en visitados y hay un ciclo en profundidad
			ciclo = !visitados.contains(clave) && recorrerEnProfundidad(grafo, clave, visitados);
			// Pasamos al siguiente vértice
			clave = grafo.listaVertices().consultar(i);

			i++;
		}

		return ciclo;

	}

	/**
	 * Método auxiliar para detectar ciclos en un grafo a si mismo.
	 * 
	 * @param grafo Grafo en el que se buscarán ciclos.
	 * @return true si se encontró un ciclo, false en caso contrario.
	 */

	private static <Clave, InfoVertice, Coste> boolean cicloASiMismo(Grafo<Clave, InfoVertice, Coste> grafo) {
		// TODO Auto-generated method stub
		boolean ciclo = false;

		int i = 1;
		// Recorremos las aristas del grafo y mientras no haya ciclo y no hayamos
		// recorrido todas las aristas
		while (i <= grafo.listaAristas().longitud() && !ciclo) {

			// Si el atributo de la arista es igual al valor de la arista es que hay un
			// ciclo a si mismo
			ciclo = grafo.listaAristas().consultar(i).getAtributo()
					.equals(grafo.listaAristas().consultar(i).getValor());
			i++;
		}
		return ciclo;

	}

	/**
	 * Método auxiliar para detectar ciclos en un grafo mediante un recorrido en
	 * profundidad.
	 * 
	 * @param grafo     Grafo en el que se buscarán ciclos.
	 * @param clave    Vértice actual en el recorrido.
	 * @param visitados Conjunto de vértices visitados en el recorrido.
	 * @return true si se encontró un ciclo, false en caso contrario.
	 */

	private static <Clave, InfoVertice, Coste> boolean recorrerEnProfundidad(Grafo<Clave, InfoVertice, Coste> grafo,
			Clave clave, LinkedList<Clave> visitados) {
		// Marcado del vértice actual como visitado
		visitados.add(clave);

		// Recorrido de sucesores
		Lista<Clave> sucesores = grafo.listaSucesores(clave);

		// Comprobación de ciclo en profundidad
		boolean cicloEnProfundidad = tieneCicloEnProfundidad(grafo, clave, sucesores, visitados);

		/**
		 * Solo se elimina el vértice actual de visitados si no se encontró un ciclo en
		 * profundidad esto se debe a que si se encontró un ciclo en profundidad se debe
		 * mantener el vértice actual en visitados para poder detectar ciclos
		 * posteriores posibles.
		 * 
		 */
		visitados.remove(!cicloEnProfundidad ? clave : null);

		return cicloEnProfundidad;
	}

	/**
	 * Método auxiliar para determinar si un vértice tiene un ciclo en profundidad.
	 * Revisando si el sucesor ya ha sido visitado o si es predecesor actual o si
	 * hay un ciclo en profundidad
	 * 
	 * @param grafo     Grafo en el que se buscarán ciclos.
	 * @param clave    Vértice actual en el recorrido.
	 * @param sucesores Lista de sucesores del vértice actual.
	 * @param visitados Conjunto de vértices visitados en el recorrido.
	 * @return true si se encontró un ciclo, false en caso contrario.
	 */

	private static <Clave, InfoVertice, Coste> boolean tieneCicloEnProfundidad(Grafo<Clave, InfoVertice, Coste> grafo,
			Clave clave, Lista<Clave> sucesores, LinkedList<Clave> visitados) {

		boolean cicloEnProfundidad = false;

		int i = 1;
		// Recorremos los sucesores del vértice actual y mientras no haya ciclo y no
		// hayamos recorrido todos los sucesores
		while (i <= sucesores.longitud() && !cicloEnProfundidad) {
			Clave sucesor = sucesores.consultar(i++);
			// Si el sucesor ya ha sido visitado o es predecesor actual o hay un ciclo en
			// profundidad, hay ciclo
			cicloEnProfundidad = visitados.contains(sucesor) || recorrerEnProfundidad(grafo, sucesor, visitados);

		}
		return cicloEnProfundidad;

	}

	public static void main(String[] args) {
		// Crear grafo de ejemplo
		Grafo<String, String, Integer> grafoConCiclos = new Grafo<>();

		grafoConCiclos.insertarVertice("A", "A");
		grafoConCiclos.insertarVertice("B", "B");
		grafoConCiclos.insertarVertice("C", "C");

		grafoConCiclos.insertarArista("A", "B", 1);
		grafoConCiclos.insertarArista("A", "C", 1);
		grafoConCiclos.insertarArista("B", "A", 1);
		grafoConCiclos.insertarArista("C", "B", 1);


		Grafo<String, String, Integer> grafoSinCiclos = new Grafo<>();

		grafoSinCiclos.insertarVertice("A", "A");
		grafoSinCiclos.insertarVertice("B", "B");
		grafoSinCiclos.insertarVertice("C", "C");
		grafoSinCiclos.insertarVertice("D", "D");

		grafoSinCiclos.insertarArista("A", "B", 1);
		grafoSinCiclos.insertarArista("B", "C", 1);
		grafoSinCiclos.insertarArista("C", "D", 1);

		Grafo<Integer, String, Integer> g = new Grafo<>();
		g.insertarVertice(1, "1");
		g.insertarVertice(2, "2");
		g.insertarArista(1, 2, 1);

		Grafo<Integer, String, Integer> g2 = new Grafo<>();
		g2.insertarVertice(1, "1");
		g2.insertarVertice(2, "2");
		g2.insertarVertice(3, "3");
		g2.insertarArista(1, 2, 1);
		g2.insertarArista(2, 3, 1);
		//g2.insertarArista(3, 1, 1);
		g2.insertarArista(2, 1, 1);
		
		// Probar detección de ciclos
		System.out.println("¿El grafo contiene ciclos?: " + testCiclos(grafoConCiclos));
		System.out.println("¿El grafo contiene ciclos?: " + testCiclos(grafoSinCiclos));
		System.out.println("¿El grafo contiene ciclos?: " + testCiclos(g));
		System.out.println("¿El grafo contiene ciclos?: " + testCiclos(g2));

		// Crear otro grafo sin ciclos
		// Grafo<String, String, Integer> grafoSinCic

	}
}