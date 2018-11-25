package Filter;

import java.util.HashMap;

public class Filter {

	HashMap<String, String> store;
	int indice = 0;

	public Filter(String path, String query) {
		store = new HashMap<String, String>();
		loadTokens(path);

	}

	public void loadTokens(String path) { // carga los tokens en el hash
		char c = 'x';
		String data = Utilities.Utilities.readArch(path);
		String buffer = "";
		String temp = "";
		String group = "";
		boolean pointer = false;
		String actualChamp = ""; // campeon actual
		boolean selectChamp = false; // indica que tiene un champ seleccionado
		while (indice < data.length()) {
			c = data.charAt(indice);
			if (c != ']' && c != '}' && c != '[' && c != '{') {
				if (c == ':' && !pointer) {
					temp = buffer.replace("\"", "");
					pointer = true;
					buffer = "";
				} else {
					if (c == ':' && pointer && group == "") {
						group = temp + "-" + buffer.replace("\"", "");
						temp = group ;
						buffer = "";
					} else {
						if (c == ':' && pointer && group != "") {
							temp = group + "-" +buffer.replace("\"", "");
							buffer = "";
						}
					}
					if (c == ',' && pointer) { // encontre par
						if (selectChamp) {
							actualChamp = buffer.replace(":", "");
							temp = "";
							pointer = false;
							buffer = "";
							selectChamp = false;
						} else {
							if (store.containsKey((temp + "-" + actualChamp))){
								System.out.println("xdd");
							}
							store.put(temp.replace("\"", "") + "-" + actualChamp, buffer.replace("\"", "")); //Temp contiene el campo y buffer su valor
							pointer = false;
							temp = "";
							buffer = "";
						}
					} else {
						buffer = buffer + c;
					}
				}
			}
		
			if (temp.equals("_id")) { // Limpia las variables auxiliares porque va a entrar otro campeon
				actualChamp = "000";
				selectChamp = false;
			}
			if (temp.contains("championId") && !selectChamp) { // actualiza el FLAG para capturar el campeon
				temp = "";
				selectChamp = true;																									
			}
			indice++;												
		}
	}
}

