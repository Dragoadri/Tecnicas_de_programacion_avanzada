package TEMA2;

public class EJERCICIOS {

	public static void main(String[] args) {

		int a[] = { 2, 3, 8, 4, 5, 6, 9, 1, 4, 7, 4, 9, -1 };
		int b[] = { 2, 3, 8, 4, 5, 6, 9, 6, 3, 5, 1, 8, -1 };
		int imparPar[] = { 1, 3, 5, 7, 9, 2, 4, 6, 8, 10 };
		int todosPares[] = { 2, 4, 6, 8, 10, 12, 14, 16, 18, 20 };
		int todosImpares[] = { 1, 3, 5, 7, 9, 11, 13, 15, 17, 19 };

		//System.out.println(primeraPosicionDistinta(a, b, 0, a.length - 1));
		//System.out.println(minimo(a, 0, a.length - 1));
		
		int c[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		System.out.println(busquedaBinaria(c, 0, c.length - 1, 5));
		System.out.println(busquedaTernaria(c, 0, c.length - 1, 5));
		System.out.println(busquedaCuaternaria(c, 0, c.length - 1, 5));
		System.out.println(primeraPosicionPAR(todosImpares, 0, imparPar.length - 1));
		System.out.println(primeraPosicionDistinta(a, b, 0, a.length - 1));
		System.out.println(media(c));
		
		int[][] matriz1 = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 1, 2, 3, 4 }, { 5, 6, 7, 8 } };
		int[][] matriz2 = { { 1, 2, 3, 4 }, { 5, 6, 7, 2 }, { 1, 2, 3, 4 }, { 5, 6, 7, 8 } };
		
		System.out.println(iguales(matriz1, matriz2));
		System.out.println(buscar(a, 99, a.length-1));
		

	}
	
	public static boolean buscar (int []a,int x,int i) {
		
		if (i == - 1) {
			return false;
		}
		if (a[i] == x) {
			return true;
		}
		
		return buscar(a, x, i-1);
	}
	
	
	//EXAMEN PARCIAL 1 TPA
	public static boolean iguales(int[][] matriz1, int[][] matriz2) {	   
        return sonigualesDyV(matriz1, matriz2, 0, matriz1.length - 1, 0, matriz1[0].length - 1);
    }
	//EXAMEN PARCIAL 1 TPA
    private static boolean sonigualesDyV(int[][] matriz1,int [][] matriz2, int iniFila, int finFila, int iniCol, int finCol) {

        if (iniFila == finFila && iniCol == finCol) {
            return matriz1[iniFila][iniCol]== matriz2[iniFila][iniCol]; 
        }
        if (iniFila > finFila || iniCol > finCol) {
            return false;
        }

        int midFila = iniFila + (finFila - iniFila) / 2;
      
        int midColumna = iniCol + (finCol - iniCol) / 2;
      
        return  sonigualesDyV(matriz1, matriz2, iniFila, midFila, iniCol, midColumna)
				&& sonigualesDyV(matriz1, matriz2, midFila + 1, finFila, iniCol, midColumna)
				&& sonigualesDyV(matriz1, matriz2, iniFila, midFila, midColumna + 1, finCol)
				&& sonigualesDyV(matriz1, matriz2, midFila + 1, finFila, midColumna + 1, finCol);       
    }
 
    public static int sumararrya(int[] a, int ini, int fin) {
		if (ini == fin) {
			return a[ini];
		}
		int mitad = (ini + fin) / 2;
		int suma1 = sumararrya(a, ini, mitad);
		int suma2 = sumararrya(a, mitad + 1, fin);
		return suma1 + suma2;
	}
	
	public static int media(int[] a) {
		return sumararrya(a, 0, a.length - 1) / a.length;
	}
	
	
	public static int primeraPosicionPAR(int[] a, int ini, int fin) {
		if (ini == fin && a[ini]%2==0) {
			return ini;
		} else if (ini == fin && a[ini] % 2 != 0) {
			return -1;
		}
		
		int mitad = (ini + fin) / 2;

		
		
		
		if (a[mitad]%2!=0) {
			// Voy por la derecha
			return primeraPosicionPAR(a, mitad + 1, fin);
		} else {
			// Voy por la izquierda
			return primeraPosicionPAR(a, ini, mitad);
		}

	}

	

	public static int primeraPosicionDistinta(int[] a, int[] b, int ini, int fin) {
		if (ini == fin) {
			return ini;
		}
		int mitad = (ini + fin) / 2;

		if (a[mitad] == b[mitad]) {
			// Voy por la derecha
			return primeraPosicionDistinta(a, b, mitad + 1, fin);
		} else {
			// Voy por la izquierda
			return primeraPosicionDistinta(a, b, ini, mitad);
		}

	}

	/**
	 * Escribir un pseudocódigo que resuelva con un enfoque de Divide y Vencerás el
	 * problema de encontrar el elemento mínimo dentro de un array de números
	 * enteros. Calcular su complejidad y compararla con la que obtendríamos de
	 * haber resuelto el mismo problema con un enfoque secuencial.
	 */


	
	public static int minimo(int[] a, int ini, int fin) {
		if (ini == fin) {
			return a[ini];
		}
		int mitad = (ini + fin) / 2;
		int min1 = minimo(a, ini, mitad);
		int min2 = minimo(a, mitad + 1, fin);
		return Math.min(min1, min2);
	}
	/*
	 * Busqueda binaria en un array ordenado
	 * 
	 */
	
	public static int busquedaBinaria(int[] a, int ini, int fin, int x) {
		if (fin >= ini) {
			int mitad = ini + (fin - ini) / 2;

			if (a[mitad] == x) {
				return mitad;
			} else if (a[mitad] > x) {
				return busquedaBinaria(a, ini, mitad - 1, x);
			} else {
				return busquedaBinaria(a, mitad + 1, fin, x);
			}
		}
		return -1;
		
	}
	
	

	/*
	 * Busqueda ternaria en un array ordenado
	 * 
	 */

	public static int busquedaTernaria(int[] a, int ini, int fin, int x) {
	
		if (fin >= ini) {
			int tercio1 = ini + (fin - ini) / 3;
			int tercio2 = fin - (fin - ini) / 3;

			if (a[tercio1] == x) {
				return tercio1;
			} else if (a[tercio2] == x) {
				return tercio2;
			} else if (a[tercio1] > x) {
				return busquedaTernaria(a, ini, tercio1 - 1, x);
			} else if (a[tercio2] < x) {
				return busquedaTernaria(a, tercio2 + 1, fin, x);
			} else {
				return busquedaTernaria(a, tercio1 + 1, tercio2 - 1, x);
			}
		}
		return -1;
		

	}
	
	/*
	 * Busqueda cuaternaria en un array ordenado
	 * 
	 */
	
	public static int busquedaCuaternaria(int[] a, int ini, int fin, int x) {
		if (fin >= ini) {
			int cuarto1 = ini + (fin - ini) / 4;
			int cuarto2 = ini + (fin - ini) / 2;
			int cuarto3 = fin - (fin - ini) / 4;

			if (a[cuarto1] == x) {
				return cuarto1;
			} else if (a[cuarto2] == x) {
				return cuarto2;
			} else if (a[cuarto3] == x) {
				return cuarto3;
			} else if (a[cuarto1] > x) {
				return busquedaCuaternaria(a, ini, cuarto1 - 1, x);
			} else if (a[cuarto3] < x) {
				return busquedaCuaternaria(a, cuarto3 + 1, fin, x);
			} else {
				return busquedaCuaternaria(a, cuarto1 + 1, cuarto3 - 1, x);
			}
		}
		return -1;
		
	}

}
