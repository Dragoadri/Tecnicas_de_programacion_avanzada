package pgraf;

import java.util.Comparator;
import java.util.HashMap;

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
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class TestCiclicidad {

	public static <Clave, InfoVertice, Coste> boolean testCiclos(Grafo<Clave, InfoVertice, Coste> grafo) {

		/**
		 * Utilizamos un HashSet para almacenar los vértices visitados en la BPD
		 * (Búsqueda en Profundidad) HashSet es una colección que no permite elementos
		 * duplicados y no garantiza el orden de los elementos basicamente Esto nos
		 * vienee muuy bien para ver si efectivamente hemos pasado por un vértice o no y
		 * habra ciclo o no
		 * 
		 */

		Set<Clave> visitados = new HashSet<>();

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
	 * @param actual    Vértice actual en el recorrido.
	 * @return true si se encontró un ciclo, false en caso contrario.
	 */

	private static <Clave, InfoVertice, Coste> boolean cicloEnProfundidad(Grafo<Clave, InfoVertice, Coste> grafo,
			Set<Clave> visitados, Clave actual) {
		boolean ciclo = false;

		int i = 1;
		// Recorremos los vértices del grafo y mientras no haya ciclo y no hayamos
		// recorrido todos los vértices
		while (i <= grafo.listaVertices().longitud() && !ciclo) {

			// Si el vértice actual no está en visitados y hay un ciclo en profundidad
			ciclo = !visitados.contains(actual) && recorrerEnProfundidad(grafo, actual, visitados);
			// Pasamos al siguiente vértice
			actual = grafo.listaVertices().consultar(i);

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
	 * @param actual    Vértice actual en el recorrido.
	 * @param visitados Conjunto de vértices visitados en el recorrido.
	 * @return true si se encontró un ciclo, false en caso contrario.
	 */

	private static <Clave, InfoVertice, Coste> boolean recorrerEnProfundidad(Grafo<Clave, InfoVertice, Coste> grafo,
			Clave actual, Set<Clave> visitados) {
		// Marcado del vértice actual como visitado
		visitados.add(actual);

		// Recorrido de sucesores
		Lista<Clave> sucesores = grafo.listaSucesores(actual);

		// Comprobación de ciclo en profundidad
		boolean cicloEnProfundidad = tieneCicloEnProfundidad(grafo, actual, sucesores, visitados);

		/**
		 * Solo se elimina el vértice actual de visitados si no se encontró un ciclo en
		 * profundidad esto se debe a que si se encontró un ciclo en profundidad se debe
		 * mantener el vértice actual en visitados para poder detectar ciclos
		 * posteriores posibles.
		 * 
		 */
		visitados.remove(!cicloEnProfundidad ? actual : null);

		return cicloEnProfundidad;
	}

	/**
	 * Método auxiliar para determinar si un vértice tiene un ciclo en profundidad.
	 * Revisando si el sucesor ya ha sido visitado o si es predecesor actual o si
	 * hay un ciclo en profundidad
	 * 
	 * @param grafo     Grafo en el que se buscarán ciclos.
	 * @param actual    Vértice actual en el recorrido.
	 * @param sucesores Lista de sucesores del vértice actual.
	 * @param visitados Conjunto de vértices visitados en el recorrido.
	 * @return true si se encontró un ciclo, false en caso contrario.
	 */

	private static <Clave, InfoVertice, Coste> boolean tieneCicloEnProfundidad(Grafo<Clave, InfoVertice, Coste> grafo,
			Clave actual, Lista<Clave> sucesores, Set<Clave> visitados) {

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

//funcion de kruskal que verifica si hay ciclos en un grafo y nos devuelve el grafo, arbol de expansion minima
	
	
	
	
	//grado del grafo ___________________________________________________________________________________________________________
		
	private static <Clave, InfoVertice, Coste> int grado(Grafo<Clave, InfoVertice, Coste> graf) {
		Lista <Clave> vertices = graf.listaVertices();
		int max = 0;
		//recorremos los vertices y vamos sumando los grados de entrada y salida
		//si el total es mayor que el maximo lo actualizamos
		
		for (int i = 1; i <= vertices.longitud(); i++) {
			
			Clave vertice = vertices.consultar(i);
			int total=graf.gradoEntrada(vertice)+graf.gradoSalida(vertice);
			max= total>max?total:max;
			
		}
		return max;
	}

	//vertices aislados ___________________________________________________________________________________________________________
	private static <Clave, InfoVertice, Coste> int verticesAislados(Grafo<Clave, InfoVertice, Coste> graf) {
		Lista<Clave> vertices = graf.listaVertices();
		int aislados = 0;

		//recorremos los vertices y si el total de grados de entrada y salida es cero
		for (int i = 1; i <= vertices.longitud(); i++) {

			Clave vertice = vertices.consultar(i);
			int total = graf.gradoEntrada(vertice) + graf.gradoSalida(vertice);
			aislados = total == 0 ? aislados + 1 : aislados;

		}
		return aislados;
	}
	
	//bucle for 
		
	private static <Clave, InfoVertice, Coste> int numdeBucles(Grafo<Clave, InfoVertice, Coste> grafo) {
	    int bucles = 0;
	    
	    //Lista sucesores 
//utilizamos funcion buscar
	    //para cada vertice buscamos sucesores y si llega a ser igual a sucesor es un bucle
	    
		for (int i = 1; i <= grafo.listaVertices().longitud(); i++) {
			Clave vertice = grafo.listaVertices().consultar(i);
			Lista<Clave> sucesores = grafo.listaSucesores(vertice);

			for (int j = 1; j <= sucesores.longitud(); j++) {
				Clave sucesor = sucesores.consultar(j);
				if (grafo.listaAristas().buscar(new Par<Clave, Clave>(vertice, sucesor)) != 0) {
					bucles++;
				}
			}
		}
		return bucles;
	    
	
	    

	    
	}
//_____________________________________________TEMA SIGUIENTE 

	

	//ALGORITMO DE CAMBIO MINIMO
	public static int cambioMinimo(int[] monedas, int cantidad) {
	    int[] cambio = new int[cantidad + 1];
	    cambio[0] = 0;
	    for (int i = 1; i <= cantidad; i++) {
	        cambio[i] = Integer.MAX_VALUE;
	        for (int j = 0; j < monedas.length; j++) {
	            
	        	cambio[i] = monedas[j] <= i ? 
	        			
	        			Math.min(cambio[i], cambio[i - monedas[j]] + 1) : 
	        				cambio[i];
	        	
	        	
	            
	        }
	    }
	    return cambio[cantidad];
	}

		
		//ALGORITMO DE LA MOCHILA, SE PUEDE DIVIDIR LOS OBJETOS
		
	public static int mochila(int[] pesos, int[] valores, int capacidad) {
		  int n = pesos.length; // Número de objetos
		  int[][] matriz = new int[n + 1][capacidad + 1]; // Matriz para almacenar soluciones parciales

		  // Inicialización de la matriz
		  for (int i = 0; i <= n; i++) {
		    for (int j = 0; j <= capacidad; j++) {
		    
		    	
		    	matriz[i][j]=(i == 0 || j == 0) ? 0
						: (pesos[i - 1] <= j)
								? Math.max(valores[i - 1] + matriz[i - 1][j - pesos[i - 1]], matriz[i - 1][j])
								: matriz[i - 1][j];
		    }
		  }

		  // Retorno del valor máximo
		  return matriz[n][capacidad];
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
		

		Grafo<Integer, String, Integer> g = new Grafo<>();
		g.insertarVertice(1, "1");
		g.insertarVertice(2, "2");
		g.insertarVertice(3, "3");
		
		g.insertarArista(1, 2, 1);
		g.insertarArista(1, 1, 1);
		
		g.insertarArista(2, 1, 1);

		// Probar detección de ciclos
		System.out.println("¿El grafo contiene ciclos?: " + testCiclos(grafoConCiclos));
		System.out.println("¿El grafo contiene ciclos?: " + testCiclos(grafoSinCiclos));
		System.out.println("¿El grafo contiene ciclos?: " + testCiclos(g));
		

		
		// Probar grado
		System.out.println("Grado del vertice 1: " + grado(g));

		// Probar vertices aislados
		System.out.println("Numero de vertices aislados: " + verticesAislados(g));
		
		// Probar numero de bucles
		System.out.println("Numero de bucles: " + numdeBucles(g));

		// Probar cambio minimo
		int[] monedas = {1, 2, 5, 10, 20, 50, 100, 200};
		int cantidad = 589;
		
		System.out.println("Cambio minimo: " + cambioMinimo(monedas, cantidad));
		
		// Probar mochila
		
		int[] pesos = {1, 3, 4, 5};
		int[] valores = {1, 2, 5, 7};
		
		int capacidad = 7;
		
		System.out.println("Valor maximo: " + mochila(pesos, valores, capacidad));

		
		// Probar avance rápido
		
		// Crear otro grafo sin ciclos
		// Grafo<String, String, Integer> grafoSinCic

	}
	
	
}