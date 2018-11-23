package Utilities;

import Filter.Filter;
import Query.Client;

public class Test {

	public static void main(String[] args) {
		try {
			//Client.search("");
			Filter f = new Filter("Resultados.txt","");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
