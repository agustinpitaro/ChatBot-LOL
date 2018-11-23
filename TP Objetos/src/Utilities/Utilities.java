package Utilities;

import java.io.*;


public class Utilities {
	public static String readArch(String ruta) { // Lee el archivo y retorna
													// su contenido en un String
		String codigoFuente = "";
		// Declarar una variable FileReader
		FileReader fr = null;
		try {
			// Abrir el fichero indicado en la variable nombreFichero
			fr = new FileReader(ruta);
			// Leer el primer carácter
			// Se debe almacenar en una variable de tipo int
			int caract = fr.read();
			// Se recorre el fichero hasta encontrar el carácter -1
			// que marca el final del fichero
			while (caract != -1) {
				// Concatenamos el caracter leido convertido a char(ascii) al
				// codigoFuente
				codigoFuente = codigoFuente + ((char) caract);
				// Leer el siguiente carácter
				caract = fr.read();
			}
		} catch (FileNotFoundException e) {
			// Operaciones en caso de no encontrar el fichero
			System.out.println("Error: Fichero no encontrado");
			// Mostrar el error producido por la excepción
			System.out.println(e.getMessage());
		} catch (Exception e) {
			// Operaciones en caso de error general
			System.out.println("Error de lectura del fichero");
			System.out.println(e.getMessage());
		} finally {
			// Operaciones que se harán en cualquier caso. Si hay error o no.
			try {
				// Cerrar el fichero si se ha abierto
				if (fr != null)
					fr.close();
			} catch (Exception e) {
				System.out.println("Error al cerrar el fichero");
				System.out.println(e.getMessage());
			}
		}
		return codigoFuente;
	}

	public static void createTxt(String f) {
		try {

			String ruta = "Resultados.txt";
			BufferedWriter salida = new BufferedWriter(new FileWriter(ruta));

			salida.write("Resultados de la busqueda:");
			salida.newLine();
			salida.write(f);
			salida.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}