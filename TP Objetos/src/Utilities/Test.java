package Utilities;

import Query.Client;

public class Test {
	
	public static void main(String[] args) {
		try {
			Client.search("");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
