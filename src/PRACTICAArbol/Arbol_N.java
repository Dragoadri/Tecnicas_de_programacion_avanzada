package PRACTICAArbol;



import java.util.LinkedList;

public class Arbol_N <T> {
	
	/**
	 * Esta clase contiene una subclase NODO
	 * con atributos y metodos:
	 * 
	 * info : T
	 * hijos : LinkedList<Arbol_N<T>>
	 * NODO(T , LinkedList<Arbol_N<T>>)
	 * 
	 */
	
	class Nodo {
		T info;
		LinkedList<Arbol_N<T>> hijos;

		Nodo(T info, LinkedList<Arbol_N<T>> hijos) {
			this.info = info;
			this.hijos = hijos;
		}
	}
	
	// Atributos de la clase Arbol_N
	
	private Nodo raiz;
	
	// metodos de la clase Arbol_N
	
	/**
	 * Arbol_N()
	 * Arbol_N(T info, LinkedList<Arbol_N<T>> hijos)
	 * esVacio() : boolean
	 * getRaiz() : T
	 * getHijos() : LinkedList<Arbol_N<T>>
	 * getHijoN(int n) : Arbol_N<T>
	 * getNumHijos() : int
	 * 
	 * 
	 * La clase Arbol_N tendrá un único atributo de tipo Nodo, privado, llamado raíz.
		• Al igual que sucedía con la clase ArbolBin estudiada, un árbol vacío tendrá
		dicho atributo “raíz” a null.
		• Ahora, un nodo hoja no puede tener su hijo izquierdo y su hijo derecho
		haciendo referencia a árboles vacíos. En su lugar su listado de hijos (el campo
		“hijos” del Nodo) contendrá un único elemento, que será un árbol vacío.
		• Debe prestarse especial atención a este detalle, ya que técnicamente el listado
		de hijos de un nodo hoja no será vacío, sino que contendrá un dato, que es un
		árbol vacío.
		
	 */
	
	
	// constructor por defecto, que pone el atributo raíz a null
	public Arbol_N() {
		this.raiz = null;
	}
	
/**
 *  constructor por parámetros,
	que crea un árbol cuya raíz contiene un nodo con el dato T como información, y cuyos
	hijos serán los indicados en la lista.
	
 * @param info
 * @param hijos
 */
	public Arbol_N(T info, LinkedList<Arbol_N<T>> hijos){
		this.raiz = new Nodo(info, hijos);
	}

	/**
	 * método booleano que devuelve verdadero si el árbol está vacío, y falso
		en caso contrario.
	 * @return
	 */
	public boolean esVacio() {
		return raiz == null;
	}
	
	public T getRaiz() {
		return raiz.info;
	}
	
	
	/**
	 * Nota importante: La lista (LinkedList) indexa de 0 a m-1, siendo m el tamaño de la
misma. Será necesario gestionar la conversión entre el número del hijo en nuestra
representación, y el índice que realmente ocupa en la lista (ver figura 2).
	 * @param n
	 * @return
	 */
	public Arbol_N<T> getHijoN(int n) {
		return raiz.hijos.get(n);
	}
	public LinkedList<Arbol_N<T>> getHijos() {
		return raiz.hijos;
	}
	public int getNumHijos() {
		return  raiz.hijos.size();
	}
	

}