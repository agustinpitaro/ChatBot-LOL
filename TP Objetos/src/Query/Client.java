package Query;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import Utilities.*;

public class Client {
		   
	public static void search(String asd) throws Exception {
		 String apiKey = "ec545d402ebd648449b6cf282cf288fb";
		 String elo = "GOLD";
		 String parameters = "kda,hashes";
	     String url = "http://api.champion.gg/v2/champions?elo=" + elo + "&champData=" + parameters +"&limit=200&api_key=" + apiKey;
	     URL obj = new URL(url);
	     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	     // optional default is GET
	     con.setRequestMethod("GET");
	     //add request header
	     con.setRequestProperty("User-Agent", "Mozilla/5.0");
	     int responseCode = con.getResponseCode();
	     System.out.println("\nSending 'GET' request to URL : " + url);
	     System.out.println("Response Code : " + responseCode);
	     BufferedReader in = new BufferedReader(
	             new InputStreamReader(con.getInputStream()));
	     String inputLine;
	     StringBuffer response = new StringBuffer();
	     while ((inputLine = in.readLine()) != null) {
	     	response.append(inputLine);
	     }
	     String entrada = response.toString();
	     String salida = "";
	     int cursor = 0;
	     for (int i = 0;i<entrada.length();i++){
	    	 if(entrada.charAt(i) == '{' || entrada.charAt(i) == '}'){
	    		 salida = salida + entrada.substring(cursor, i) + "\n";
	    		 i++;
	    		 cursor = i;
	    	 }
	     }
	     Utilities.createTxt(salida);
	   }
}


