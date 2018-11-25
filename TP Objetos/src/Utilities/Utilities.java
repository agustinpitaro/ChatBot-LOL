package Utilities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Utilities {
	public static String readArch(String ruta) { // Lee el archivo y retorna
													// su contenido en un String
		File file = new File(ruta);
		Scanner input = null;
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String list = "";

		while (input.hasNextLine()) {
			String aux = input.nextLine();
		    list = list + aux;
		}
		if (!input.hasNextLine())
			input.close();
		return list;
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