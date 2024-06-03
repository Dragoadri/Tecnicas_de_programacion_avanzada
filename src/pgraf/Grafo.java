package pgraf;


/**
 * 
 * @author Borja
 *
 * @param <Clave> Identificador del vértice
 * @param <InfoVertice> Información asociada al vértice
 * @param <Coste> Peso de la arista
 */
public class Grafo <Clave, InfoVertice, Coste> {

	protected class NodoVertice {
		Clave clave;
		InfoVertice vertice;
		int gradoEntrada;
		int gradoSalida;

		public NodoVertice(Clave c, InfoVertice v) {
			clave = c;
			vertice = v;
			gradoEntrada = 0;
			gradoSalida = 0;
		}
	}

	protected class NodoArista {
		NodoVertice destino;
		Coste coste;

		public NodoArista(NodoVertice d) {
			destino = d;
			coste = null;
		}

		public NodoArista(NodoVertice d, Coste c) {
			destino = d;
			coste = c;
		}
	}
	
	//Atributos
	private Lista<NodoVertice> vertices; // conjunto de vértices

	private Lista< Lista<NodoArista> > aristas; // lista de adyacencia

	public Grafo() {
		vertices = new Lista<NodoVertice>();
		aristas = new Lista<Lista<NodoArista>>();
	}

	public boolean esVacio() {
		return vertices.longitud() == 0;
	}

	public void insertarVertice(Clave c, InfoVertice v) {
		vertices.insertar(vertices.longitud()+1, new NodoVertice(c, v));
		aristas.insertar(aristas.longitud()+1, new Lista<NodoArista>());
	}

	public void modificarVertice(Clave c, InfoVertice v) {
		int i = 1;
		while (i <= vertices.longitud()
				&& !vertices.consultar(i).clave.equals(c))
			i++;
		if (i <= vertices.longitud())
			vertices.consultar(i).vertice = v;
	}
	
	/**
	 * Eimina el vertice c si existe. Las aristas que inciden desde o hacia él, y actualzia los 
	 * grados de entrada y salida de los antecesores y sucedores
	 * @param c
	 * 
	 * ACTUALIZADA: 2-6-2022
	 */
	public void eliminarVertice(Clave c) {
		int i = 1;

		// Busca el vertice que se le indique
		while (i <= vertices.longitud()
				&& !vertices.consultar(i).clave.equals(c))
			i++;

		// Si el vertice a eliminar existe, se elimina, junto con todas las
		// aristas relacionadas.
		if (i <= vertices.longitud()) {
			//recorro todos los sucesores del vértice, decrementando su grado de entrada
			for (int sucesor = 1; sucesor <= this.aristas.consultar(i).longitud(); sucesor++) {
				this.aristas.consultar(i).consultar(sucesor).destino.gradoEntrada--;
			}//for
			
			//busco todos los predecesores del vértice que quiero borrar, para eliminarlo 
			//y reducir los grados de salida
			for (int origen=1; origen <= this.aristas.longitud(); origen++) {
				int destino = 1;
				boolean encontrado = false;
				while  (!encontrado && destino <= this.aristas.consultar(origen).longitud()) {
					//si el vertice a borrar está como destino desde otro
					if(this.aristas.consultar(origen).consultar(destino).destino.clave.equals(c)) {
						//decremento el grado de salida 
						this.vertices.consultar(origen).gradoSalida--;
						//borro el destino de la lista
						this.aristas.consultar(origen).borrar(destino);
						encontrado = true;
					}
					destino++;
				}//while
			}//for
			
			//borro la lista de los sucesores
			this.aristas.borrar(i);	
			//borro el vértice de la lista de vértices
			this.vertices.borrar(i);
		
		} // if
	} // eliminarVertice

	public boolean existeVertice(Clave c) {
		int i = 1;
		while (i <= vertices.longitud()
				&& !vertices.consultar(i).clave.equals(c))
			i++;
		return i <= vertices.longitud();
	}

	public void insertarArista(Clave o, Clave d, Coste c) {
		int i = 1;
		// Busca el vertice origen
		while (i <= vertices.longitud()
				&& !vertices.consultar(i).clave.equals(o))
			i++;

		int j = 1;
		// Busca el vertice destino
		while (j <= vertices.longitud()
				&& !vertices.consultar(j).clave.equals(d))
			j++;

		// Si existe, introduce la arista
		if (i <= vertices.longitud() && j <= vertices.longitud()) {
			aristas.consultar(i).insertar(1,
					new NodoArista(vertices.consultar(j), c));
			// Actualiza el grado de salida del vertice origen
			vertices.consultar(i).gradoSalida++;

			// Actualiza el grado de entrada del vertice destino
			vertices.consultar(j).gradoEntrada++;
		}

	}

	public void modificarArista(Clave o, Clave d, Coste c) {
		int i = 1;

		// Busca el vertice origen
		while (i <= vertices.longitud()
				&& !vertices.consultar(i).clave.equals(o))
			i++;

		// Si existe, busca el vertice destino y modifica el peso de la arista
		if (i <= vertices.longitud()) {
			int j = 1;
			boolean aristaModificada = false;
			while (!aristaModificada && j <= aristas.consultar(i).longitud()) {
				if (aristas.consultar(i).consultar(j).destino.clave.equals(d)) {
					aristas.consultar(i).consultar(j).coste = c;
					aristaModificada = true;
				}
				j++;
			}
		}
	}

	public void eliminarArista(Clave o, Clave d) {
		int i = 1;

		// Busca el vertice origen
		while (i <= vertices.longitud()
				&& !vertices.consultar(i).clave.equals(o))
			i++;

		// Si existe, busca el vertice destino y elimina la arista
		if (i <= vertices.longitud()) {
			int j = 1;
			boolean aristaEliminada = false;
			while (!aristaEliminada && j <= aristas.consultar(i).longitud()) {
				//Si es el destino
				if (aristas.consultar(i).consultar(j).destino.clave.equals(d)) {
					// Actualiza el grado de salida del vertice origen
					vertices.consultar(i).gradoSalida--;
					//System.out.println("** " + vertices.consultar(i).clave); //traza
					// Actualiza el grado de entrada del vertice destino
					aristas.consultar(i).consultar(j).destino.gradoEntrada--;
					// Borra el vertice 
					aristas.consultar(i).borrar(j);
				} else
					j++;
			}
		}
	}

	public Coste costeArista(Clave o, Clave d) {
		int i = 1;
		Coste coste = null;

		// Busca el vertice origen
		while (i <= vertices.longitud()
				&& !vertices.consultar(i).clave.equals(o))
			i++;

		// Si existe, busca el vertice destino y devuelve el peso de la arista
		if (i <= vertices.longitud()) {
			int j = 1;
			boolean aristaEncontrada = false;
			while (!aristaEncontrada && j <= aristas.consultar(i).longitud()) {
				if (aristas.consultar(i).consultar(j).destino.clave.equals(d)) {
					coste = aristas.consultar(i).consultar(j).coste;
					aristaEncontrada = true;
				}
				j++;
			}
		}

		return coste;
	}

	public int gradoEntrada(Clave v) {
		int i = 1;

		// Busca el vertice
		while (i <= vertices.longitud()
				&& !vertices.consultar(i).clave.equals(v))
			i++;

		// Si lo encuentra, devuelve su grado de entrada. Si no, devuelve cero
		if (i <= vertices.longitud())
			return vertices.consultar(i).gradoEntrada;
		else
			return 0;
	}

	public int gradoSalida(Clave v) {
		int i = 1;

		// Busca el vertice
		while (i <= vertices.longitud()
				&& !vertices.consultar(i).clave.equals(v))
			i++;

		// Si lo encuentra, devuelve su grado de salida. Si no, devuelve cero
		if (i <= vertices.longitud())
			return vertices.consultar(i).gradoSalida;
		else
			return 0;
	}

	public Lista<Clave> listaSucesores(Clave v) {
		int i = 1;
		Lista<Clave> sucesores = new Lista<Clave>();

		// Busca el vertice
		while (i <= vertices.longitud()
				&& !vertices.consultar(i).clave.equals(v))
			i++;

		// Si lo encuentra, introduce sus sucesores a la lista
		if (i <= vertices.longitud())
			for (int j = 1; j <= aristas.consultar(i).longitud(); j++)
				sucesores.insertar(j,
						aristas.consultar(i).consultar(j).destino.clave);

		return sucesores;
	}

	public Lista<Clave> listaPredecesores(Clave v) {
		Lista<Clave> predecesores = new Lista<Clave>();

		// Busca la aparicion del vertice como posible destino de otros vertices
		for (int i = 1; i <= vertices.longitud(); i++) {
			int j = 1;
			boolean verticeEncontrado = false;
			// Si encuentra el vertice v como destino de un vertice o, no
			// aparece mas veces como destino de o
			while (!verticeEncontrado && j <= aristas.consultar(i).longitud()) {
				if (v.equals(aristas.consultar(i).consultar(j).destino.clave)) {
					predecesores.insertar(1, vertices.consultar(i).clave);
					verticeEncontrado = true;
				} else
					j++;
			}
		}

		return predecesores;
	}

	public int numVertices() {
		return vertices.longitud();
	}

	public Lista<Clave> listaVertices() {
		Lista<Clave> listaVertices = new Lista<Clave>();

		for (int i = 1; i <= vertices.longitud(); i++)
			listaVertices.insertar(1, vertices.consultar(i).clave);

		return listaVertices;
	}
	
	public Lista <Par <Clave, Clave>> listaAristas () {
		
		Lista <Par <Clave, Clave> > listado = new  Lista <Par <Clave, Clave> > ();
		
		//para cada vértice, obtener sus sucesores, crear la arista como "par" a inserarlo en el listado:
		Lista <Clave> vertices = this.listaVertices(); 
		for (int i = 1; i <= vertices.longitud(); i++) {
			Clave origen = vertices.consultar(i);
			Lista <Clave> sucesores = this.listaSucesores(origen);
			for (int j = 1; j <= sucesores.longitud(); j++) {
				Clave destino = sucesores.consultar(j);
				Par <Clave, Clave> arista = new Par<Clave, Clave> (origen, destino);
				listado.insertar(1, arista); // Modificar la posición "1" en función de si la lista es estática o dinámica
			}
		}		
		return listado;		
	}

	public String toString() {
		String texto = "";
		for (int i = 1; i <= vertices.longitud(); i++) {
			texto += vertices.consultar(i).clave + " --> ";
			for (int j = 1; j <= aristas.consultar(i).longitud(); j++)
				texto += aristas.consultar(i).consultar(j).destino.clave + "("
						+ aristas.consultar(i).consultar(j).coste + ") ";
			texto += "\n";
		}

		return texto;
	}
	   
	public static <Clave, InfoVertice, Coste> void profREC(Grafo<Clave, InfoVertice, Coste> gr, 
			Clave inicio, Lista<Clave> noVisitados){
		
		System.out.println("*VISITO: "+inicio); //visito
		noVisitados.borrar(noVisitados.buscar(inicio));//elimino el vértice visitado de la lista de no visitados
		System.out.println("\tNoVisitados: "+noVisitados);
		
		Lista<Clave> sucesores = gr.listaSucesores(inicio); //sucesores del vértice visitado	
		System.out.println("\tSucesores de "+inicio+": "+sucesores);
		//recorro los sucesores. Si alguno NO está visitado profundizo y lo visito
		for (int i = 1; i <= sucesores.longitud() ; i++){
			Clave v = sucesores.consultar(i);
			if (noVisitados.buscar(v)!=0){ //si el sucesor i-esimo está en no visitados, profundizo
				profREC(gr, v, noVisitados);
			}//if	
		}//for			
	}//profREC
	
	public static <Clave, InfoVertice, Coste> void profundidad (Grafo<Clave, InfoVertice, Coste> gr, 
			Clave inicio){
		Lista<Clave> noVisitados = gr.listaVertices();
		profREC(gr, inicio, noVisitados);
		while (noVisitados.esVacia() == false){
			System.out.println("\n**Nueva componente conexa**\n");
			profREC(gr, noVisitados.consultar(1), noVisitados);			
		}//while
	}//profundidad
	
	
	public static void main(String args[]) {
/*		Grafo<String, String, Integer> grafoCiudades = new Grafo<String, String, Integer>();
		
		grafoCiudades.insertarVertice("BAR", "Barcelona");
		grafoCiudades.insertarVertice("MAD", "Madrid");
		grafoCiudades.insertarVertice("COR", "La Coruña");
		grafoCiudades.insertarVertice("SEV", "Sevilla");
		grafoCiudades.insertarVertice("VAL", "Valencia");
		grafoCiudades.insertarVertice("BIL", "Bilbao");
		grafoCiudades.insertarVertice("CUE", "Cuenca");
		grafoCiudades.insertarVertice("JAE", "Jaen");

		grafoCiudades.insertarArista("COR", "BIL", 644);
		grafoCiudades.insertarArista("COR", "MAD", 609);

		grafoCiudades.insertarArista("BIL", "BAR", 620);
		grafoCiudades.insertarArista("BIL", "MAD", 395);

		grafoCiudades.insertarArista("BAR", "BIL", 620);
		grafoCiudades.insertarArista("BAR", "VAL", 649);

		grafoCiudades.insertarArista("MAD", "COR", 609);
		grafoCiudades.insertarArista("MAD", "VAL", 352);
		grafoCiudades.insertarArista("MAD", "SEV", 538);

		grafoCiudades.insertarArista("VAL", "MAD", 352);
		grafoCiudades.insertarArista("VAL", "SEV", 697);

		grafoCiudades.insertarArista("SEV", "MAD", 538);
		
		grafoCiudades.insertarArista("CUE", "JAE", 356);

		System.out.println(grafoCiudades);
		/*
		System.out.println("NumVertices= " + grafoCiudades.numVertices());
		System.out.println("ListaSucesores(BAR)\n"
				+ grafoCiudades.listaSucesores("BAR"));
		System.out.println("ListaPredecesores(MAD)\n"
				+ grafoCiudades.listaPredecesores("MAD"));

		System.out.println("Recorrido en profundidad");
		*/
		//grafoCiudades.testCiclicidad();
		
//		profundidad(grafoCiudades, "BAR");

		
		Grafo <String, String, Integer> gPrueba = new Grafo<String, String, Integer>();
		
		gPrueba.insertarVertice("A", "A");
		gPrueba.insertarVertice("B", "B");
		gPrueba.insertarVertice("C", "C");
		
		gPrueba.insertarArista("A", "B", 1);
		gPrueba.insertarArista("A", "C", 1);
		gPrueba.insertarArista("B", "A", 1);
		gPrueba.insertarArista("C", "B", 1);
		
		System.out.println(gPrueba);
		System.out.println("Grado salida A: " + gPrueba.gradoSalida("A"));
		System.out.println("Grado salida B: " + gPrueba.gradoSalida("B"));
		System.out.println("Grado salida C: " + gPrueba.gradoSalida("C"));
		System.out.println("Grado entrada A: " + gPrueba.gradoEntrada("A"));
		System.out.println("Grado entrada B: " + gPrueba.gradoEntrada("B"));
		System.out.println("Grado entrada C: " + gPrueba.gradoEntrada("C"));
		
		gPrueba.eliminarVertice("C");
		gPrueba.eliminarVertice("B");
		//gPrueba.eliminarVertice("A");
		//gPrueba.eliminarArista("A", "C");		
		
		System.out.println(gPrueba);
		System.out.println("Grado salida A: " + gPrueba.gradoSalida("A"));
		System.out.println("Grado salida B: " + gPrueba.gradoSalida("B"));
		System.out.println("Grado salida C: " + gPrueba.gradoSalida("C"));
		System.out.println("Grado entrada A: " + gPrueba.gradoEntrada("A"));
		System.out.println("Grado entrada B: " + gPrueba.gradoEntrada("B"));
		System.out.println("Grado entrada C: " + gPrueba.gradoEntrada("C"));
		
		System.out.println("*** FIN ***");
		
	}//main
}//class