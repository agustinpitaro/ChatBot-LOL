package Query;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Utilities.*;

public class Client {
	
;
		   
	public static void dbGenerator() throws Exception {
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
	     	response.append(inputLine + '\n');
	     }
	     String entrada = response.toString();
	     JSONArray array = new JSONArray(entrada); //Creo estructura de la consulta
	     
	     List<Object> DataBase = Utilities.JSONArraytoList(array); //Convierto a Lista de Maps
	     
	     for (Object j : DataBase){
	    	 Integer aux = (Integer) ((HashMap<String, Object>) j).get("championId");
	    	 Utilities.createTxt(j.toString().replace("," , "," + System.lineSeparator()), "C:/Users/Agustin/git/ChatBot-LOL/database/" + aux.toString() + ".txt");
	
	     }

	    // Utilities.createTxt(entrada, "");
	   }
}


