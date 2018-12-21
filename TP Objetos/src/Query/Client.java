package Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.lucene.queryparser.classic.ParseException;

public class Client {
	
	private String actualFile;
	private Integer actualChampId;
	private String actualChamp;
	private Indexador ind;
	private ArrayList<String> queryData;
		 
	
	public Client (Indexador i) {
		this.ind = i;
		queryData = new ArrayList<String>();
		this.actualFile = "";
		this.actualChampId = 0;
		queryDataArraySetter();
	}
	
	private void queryDataArraySetter() {
		queryData.add("kills");
		queryData.add("role");
		queryData.add("winrate");
		queryData.add("wins");
		queryData.add("items");
		queryData.add("skill");
		queryData.add("juegos jugados");
		queryData.add("playRate");
	}
	
	public String talk(String input) throws Exception {
		String data = "";
		String response = "";
		Boolean wantsData = false;
		Collection<String> champs = ind.getTranslator().values();
	
		
		while(input.charAt(input.length()-1)=='!' || input.charAt(input.length()-1)=='.' || input.charAt(input.length()-1)=='?'){
			input=input.substring(0,input.length()-1);
			}
		
		input.toLowerCase();
		input.trim();
			
		for(String champ : champs) {
				if(input.contains(champ)) {
					actualChamp = champ;
					actualChampId = ind.getChamp(champ);
			}
		}
			for(String value : queryData) {
				if(input.contains(value)) {
					data = value;
					wantsData = true;
				}
			}
			
			if (input.contains("juegos jugados")) {
				data = "gamesPlayed";
				wantsData = true;
			}
			
			if(wantsData) {
				response = query(data);
			}else{
				
				response = "No entiendo ¿qué quieres decir?";
			}
			
			if (input.equals("hola")) {
				response = "Hola! Que deseas saber?";
			}
			
	   return response;		
	}
	
	public String query(String data) throws Exception {
		String output = "";
		this.actualFile = ind.searchIndex(data, actualChampId.toString());
		output = "Para " + actualChamp + " " + ind.simpleDataGetter(actualFile, data);
		
		return output;
	}
	
}


