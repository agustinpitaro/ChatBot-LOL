package Filter;

import java.util.HashMap;

public class Filter {

	HashMap<String, String> store;
	int indice = 0;
	boolean EOF = false;
	String data;
	String buffer;
	String temp;
	boolean pointer = false;

	public Filter(String path, String query) {
		store = new HashMap<String, String>();
		data = Utilities.Utilities.readArch(path);
		buffer = "";
		temp = "";
		loadTokens();

	}

	public void loadTokens() { // carga los tokens en el hash
		char c;
		while (indice < data.length() || data.charAt(indice) == -1) {
			c = data.charAt(indice);
			if (c != ']' && c != '}' && c != '[' && c != '{') {
				if (c == ':' && !pointer) {
					temp = buffer;
					pointer = true;
					buffer = "";
				} else {
					if (c == ':' && pointer) {
						temp = temp + "-" + buffer;
						buffer = "";
					} else {
						if (c == ',' && pointer) { // encontre par
							store.put(temp.replace("\"", ""), buffer);
							pointer = false;
							temp = "";
							buffer = "";
						} else {
							buffer = buffer + c;
						}
					}
				}
			}
			indice++;
		}
	boolean end = true;
	}
	
}

