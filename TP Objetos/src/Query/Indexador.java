package Query;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockFactory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.SimpleFSDirectory;
import org.json.JSONArray;
import org.json.JSONObject;

import Utilities.Utilities;

	

public class Indexador {
	
	private static StandardAnalyzer sA;
	private static Directory directory ;
	private static IndexWriterConfig config;
	private static FileReader fr;
	private static String actualFile;
	private static HashMap<Integer, String> champsTranslator;
	private static HashMap<Integer, String> itemsTranslator;
	
	public Indexador() throws IOException {
		CharArraySet stopSet = CharArraySet.copy(StandardAnalyzer.STOP_WORDS_SET);
		stopSet.add("el");
	    stopSet.add("la");
	    stopSet.add("las");
	    stopSet.add('?');
	    stopSet.add("de");
	    stopSet.add("dame");
		Indexador.sA = new StandardAnalyzer(stopSet);
		Indexador.directory = new SimpleFSDirectory(Paths.get("‪Index"));
		Indexador.config = new IndexWriterConfig(sA);
		Indexador.champsTranslator = new HashMap<Integer, String>();
	}
	
	public HashMap<Integer, String> getTranslator() {
		return champsTranslator;
	};
	
	
	public static String getChampsTranslator(int id) {
		return champsTranslator.get(id);
	}

	private static void ChampSetter() throws Exception {

	     String url = "http://raw.communitydragon.org/latest/plugins/rcp-be-lol-game-data/global/default/v1/champion-summary.json";
		 URL obj = new URL(url);
	     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	     con.setRequestMethod("GET");
	     con.setRequestProperty("User-Agent", "Mozilla/5.0");
	     BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	     String inputLine;
	     StringBuffer response = new StringBuffer();
	     while ((inputLine = in.readLine()) != null) {
	     	response.append(inputLine);
	     }
	     String entrada = response.toString();
	     JSONArray array = new JSONArray(entrada); //Creo estructura de la consulta
	     
	     List<Object> DataBase = Utilities.JSONArraytoList(array); //Convierto a Lista de Maps
	     
	     for (Object j : DataBase){
	    	 Integer id = (Integer) ((HashMap<String, Object>) j).get("id");
	    	 String name = (String) ((HashMap<String, Object>) j).get("name");
	    	 champsTranslator.put(id, name);
	     }
	   }
	
	private static void itemSetter() throws Exception{
		String url = "http://ddragon.leagueoflegends.com/cdn/7.8.1/data/es_ES/item.json";
		 URL obj = new URL(url);
	     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	     con.setRequestMethod("GET");
	     con.setRequestProperty("User-Agent", "Mozilla/5.0");
	     BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	     String inputLine;
	     StringBuffer response = new StringBuffer();
	     while ((inputLine = in.readLine()) != null) {
	     	response.append(inputLine);
	     }
	     String entrada = response.toString();
	     
	     //Creo estructura de la consulta
	     
	     
	     JSONObject asd = new JSONObject(entrada);
	     JSONObject data = (JSONObject) asd.get("data");
	     JSONObject prueba = (JSONObject) data.get("2009");
	     System.out.println(prueba.get("name"));
	     
	     
	     
	     //JSONArray items = new JSONArray(entrada);
	     
	     
	     /*List<Object> DataBase = Utilities.JSONArraytoList(items); //Convierto a Lista de Maps
	     
	     for (Object j : DataBase){
	    	 Integer id = (Integer) ((HashMap<String, Object>) j).get("id");
	    	 String name = (String) ((HashMap<String, Object>) j).get("name");
	    	 itemsTranslator.put(id, name);
	     }*/
	}
	
	public static void createIndex() throws Exception {
		
		ChampSetter();
		
		IndexWriter indexWriter = new IndexWriter(directory, config);
		File dir = new File("C:/Users/Agustin/git/ChatBot-LOL/database/");
		File[] files = dir.listFiles();
		for (File file : files) {
			Document document = new Document();
			String path = file.getCanonicalPath();
			document.add(new TextField("path", path, Field.Store.YES));
			Reader reader = new FileReader(file);
			String AuxName = file.getName();
			document.add(new TextField(AuxName.substring(0, AuxName.length()-4), reader));
			indexWriter.addDocument(document);
		}
		indexWriter.close();
	}

	public static String searchIndex(String searchString, String content) throws IOException, ParseException {
		String path = "";
		System.out.println("Buscando " + searchString);
		IndexReader indexReader = DirectoryReader.open(directory);
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);

		QueryParser queryParser = new QueryParser(content, sA);
		Query query = queryParser.parse(searchString);
		TopDocs hits = indexSearcher.search(query, 5);
		System.out.println("Number of hits: " + hits.totalHits);

		for (int i = 0; i<hits.scoreDocs.length; i++) {
			int documentId = hits.scoreDocs[i].doc;
			Document d = indexSearcher.doc(documentId);
			path = d.get("path");
		}
		System.out.println(path);
		return path;
	}
	
	public String simpleDataGetter(String file, String dato) throws IOException {
		String data = "";
		String line;
		fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		while ((line = br.readLine()) != null) {
		       if (line.contains(dato)) {
		    	   data = line.substring(0, line.length()-1);
		       }
		    }
		br.close();
		System.out.println(data);
		return data;
	}
	
	public static void main(String[] args) throws Exception {
		
		Indexador i = new Indexador();
		ImageGetter img = new ImageGetter();
		//i.createIndex();
		//i.searchIndex("cuantos gamesPlayed 555", "15");
		//i.simpleDataGetter(actualFile, "gamesPlayed");
	    //i.itemSetter();
		img.getChampImage("Neeko");
    }
}


