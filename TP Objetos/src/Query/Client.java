package Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.lucene.queryparser.classic.ParseException;

public class Client {
	
	private String actualFile;
	private String actualChamp;
	private Indexador ind;
	private ArrayList<String> queryData;
		 
	
	public Client (Indexador i) {
		this.ind = i;
		queryData = new ArrayList();
		this.actualFile = "";
	}
	
	public void queryDataArraySetter() {
		queryData.add("kills");
		queryData.add("role");
		queryData.add("winrate");
		queryData.add("wins");
		queryData.add("items");
		queryData.add("skill");
		queryData.add("gamesPlayed");
		queryData.add("playRate");
	}
	
	public String talk(String quote) throws IOException, ParseException {
		String data = "";
		String response = "";
		Boolean wantsData = false;
		Collection<String> champs = ind.getTranslator().values();
		
		while(quote.charAt(quote.length()-1)=='!' || quote.charAt(quote.length()-1)=='.' ||quote.charAt(quote.length()-1)=='?'){
				quote=quote.substring(0,quote.length()-1);
			}
		
			quote.toLowerCase();
			quote.trim();
			
			for(String champ : champs)
				if(quote.contains(champ))
					actualChamp = champ;
			for(String value : queryData)
				if(quote.contains(value)) {
					data = value;
					wantsData = true;
				}
			if(wantsData) {
				response = query(data);
			}
			response = "No entiendo ¿qué quieres decir?";
	   return response;		
	}
	
	public String query(String data) throws IOException, ParseException {
		String output = "";
		this.actualFile = ind.searchIndex(data, actualChamp);
		output = ind.simpleDataGetter(actualFile, data);
		return output;
	}
	
}


