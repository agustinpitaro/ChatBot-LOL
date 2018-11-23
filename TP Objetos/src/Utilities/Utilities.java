package Utilities;

import java.io.*;


public class Utilities {
	public static String readArch(String ruta) { // Lee el archivo y retorna
													// su contenido en un String
		String codigoFuente = "";
		BufferedReader objReader = null;
		try {
			objReader = new BufferedReader(new FileReader(ruta));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (objReader.readLine() != null) {
					codigoFuente = codigoFuente + objReader.readLine();

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		try {
			if (objReader.readLine() == null)
				objReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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