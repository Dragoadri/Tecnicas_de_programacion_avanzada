package PracticaDyV;


/**
 *
 * @author1 Oussama FARHAT, MOHAMED
 * @expediente1 22052494
 * @author2 CAÑADAS GALLARDO, Adrian
 * @expediente2 22004996
 * @date 2024-03-06
 *
 */
public class Buscar {
    /**
     * Función booleana que busca un dato dentro de una matriz, aplicando un enfoque
     * de Divide y Vencerás para ello
     *
     * @param matriz Matriz NxM de números enteros
     * @param dato   Número entero a buscar dentro de la matriz
     * @return true/false si encuentra o no el dato en la matriz
     */
public static boolean buscar_DV(int[][] matriz, int dato) {
       
        /**La idea va a ser
         * dividir la matriz en 4
         * así recursivamente hasta
         * solo quedar un único el
         * elemento que será el que
         * revisaremos.
         
         | | |
         -----
         | | |
         
         la función tambien valdra para
         cualquier matriz que no sea cuadrada.
         por ejemplo 3x4 o que sus filas y columnas sean impares
         por ejemplo 3x3
         
         */
   
       
   
        return buscar(matriz,dato,0,matriz.length-1,0,matriz[0].length-1);
    }// buscar

/**
 * Función booleana que busca un dato dentro de una matriz, aplicando un enfoque
 * de Divide y Vencerás para ello
 *
 * @param matriz Matriz NxM de números enteros. Es la matriz en la que se buscará el dato.
 * @param dato   Número entero a buscar dentro de la matriz. Es el valor que se busca en la matriz.
 * @param iniFila Índice inicial de la fila. Define el inicio del rango de filas en el que se buscará.
 * @param finFila Índice final de la fila. Define el final del rango de filas en el que se buscará.
 * @param iniCol Índice inicial de la columna. Define el inicio del rango de columnas en el que se buscará.
 * @param finCol Índice final de la columna. Define el final del rango de columnas en el que se buscará.
 * @return true/false si encuentra o no el dato en la matriz
 */
    private static boolean buscar(int[][] matriz, int dato, int iniFila, int finFila, int iniCol, int finCol) {
       
     


        // Caso base: si la matriz solo tiene un elemento, comprobamos si es el dato que buscamos
        if (iniFila == finFila && iniCol == finCol) {
            return matriz[iniFila][iniCol] == dato;
        }
       


        // Caso base: si no hay elementos en la matriz, devolvemos false
        if (iniFila > finFila || iniCol > finCol) {
            return false;
        }


            // Calculamos el índice medio de las filas
            //Ejemplo:
            // 4 + (8 - 4)/2 = 4 + 4/2 = 4 + 2 = 6 | 1 2 3 -4 5 |6| 7 8-
        int midFila = iniFila + (finFila - iniFila) / 2;
        // Aquí, (finFila - inicioFila) calcula la longitud total de las filas que estamos considerando actualmente.
        // Dividimos esa longitud por 2 para encontrar el punto medio.
        // Luego, agregamos ese punto medio al índice de inicio de la fila para obtener el índice medio real en la matriz.


            // Calculamos el índice medio de las columnas de manera similar
        int midColumna = iniCol + (finCol - iniCol) / 2;
        // Aquí, (finColumna - inicioColumna) calcula la longitud total de las columnas que estamos considerando actualmente.
        // Dividimos esa longitud por 2 para encontrar el punto medio.
        // Luego, agregamos ese punto medio al índice de inicio de la columna para obtener el índice medio real en la matriz.




        // Buscamos en cada una de las cuatro partes, asegurándonos de que no excedemos los límites de la matriz
       
       
        return  
               
                /**Parte superior izquierda
                 
                 |*| |
                 -----
                 | | |
                 
                 */
               
                buscar(matriz, dato, iniFila, midFila, iniCol, midColumna) ||


                // Aquí estamos buscando en la submatriz superior izquierda.
                //Los índices de inicio y fin de las filas se mantienen igual,
                //pero los índices de las columnas se dividen en la columna media.


                //______________________________________________________________________________________________
               


               
                /**Parte superior derecha
                 
                 | |*|
                 -----
                 | | |
                 
                 */
               
                buscar(matriz, dato, iniFila, midFila, midColumna + 1, finCol) ||  


                //Aquí estamos buscando en la submatriz superior derecha.
                //Los índices de inicio y fin de las filas se mantienen igual,
                //pero los índices de las columnas comienzan desde la columna media + 1 hasta el final.
               
                //______________________________________________________________________________________________




               
               
                /**Parte inferior izquierda
                 
                 | | |
                 -----
                 |*| |
                 
                 */
               
                buscar(matriz, dato, midFila + 1,finFila, iniCol, midColumna) ||  


                // Aquí estamos buscando en la submatriz inferior izquierda.
                //Los índices de inicio y fin de las columnas se mantienen igual,
                //pero los índices de las filas comienzan desde la fila media + 1 hasta el final.
               
                //______________________________________________________________________________________________




               
               
                /**Parte inferior derecha
                 
                 | | |
                 -----
                 | |*|
                 
                 */
               
                buscar(matriz, dato, midFila + 1, finFila, midColumna + 1, finCol);  


                // Aquí estamos buscando en la submatriz inferior derecha.
                //Los índices de las filas comienzan desde la fila media + 1 hasta el final,
                //y los índices de las columnas comienzan desde la columna media + 1 hasta el final.


       
    }
 



	public static void main(String[] args) {
		int[][] matriz = {
			    {1, 2, 3, 4},
			    {5, 6, 7, 8},
			    {9, 10, 11, 12},
			    {9, 10, 11, 12},
			    {9, 16, 11, 12},
			  
			};
		
		int[][] matriz2 = {
			    {1, 2, 3},
			    {1, 2, 3},
			    {1, 2, 3}
			    
			  
			};
		
		int[][] matriz3 = {
			    {1, 2, 3, 4},
			    {5, 6, 7, 8},
			    {1, 2, 3, 4}
			  
			  
			};
		
		int numABuscar=16;
		System.out.println(numABuscar+(buscar_DV(matriz2, numABuscar)?" ENCONTRADO ":" NO ENCONTRADO ") );
		
		System.out.println("\n***** FIN *****");
	}// main
}// class