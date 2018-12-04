package Query;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import org.apache.lucene.analysis.Analyzer;
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

	

public class Indexador {
	
	private static StandardAnalyzer sA;
	private static Directory directory ;
	private static IndexWriterConfig config;
	private static FileReader fr;
	
	public Indexador() throws IOException {
		this.sA = new StandardAnalyzer();
		this.directory = new SimpleFSDirectory(Paths.get("‪asd"));
		this.config = new IndexWriterConfig(sA);
		
	}
	
	public static void createIndex() throws CorruptIndexException, LockObtainFailedException, IOException {
		IndexWriter indexWriter = new IndexWriter(directory, config);
		File dir = new File("1.txt");
		Document document = new Document();

		//String path = dir.getCanonicalPath();
		document.add(new TextField("path", "‪C://Users//Agustin//git//ChatBot-LOL//TP Objetos//1.txt", Field.Store.YES));
		
		Reader reader = new FileReader(dir);
		document.add(new TextField("Darius", reader));
		indexWriter.addDocument(document);
		
		/*File[] files = dir.listFiles();
		for (File file : files) {
			Document document = new Document();

			String path = file.getCanonicalPath();
			document.add(new TextField("path", path, Field.Store.YES));
			
			Reader reader = new FileReader(file);
			document.add(new TextField("Darius", "OP TOP", Field.Store.YES));
			indexWriter.addDocument(document);
		}*/
		indexWriter.close();
	}

	public static void searchIndex(String searchString, String content) throws IOException, ParseException {
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
		System.out.println("La ruta es: " + path);
	}
	
	
	public static void main(String[] args) throws IOException, ParseException {
	
		/*StandardAnalyzer standardAnalyzer = new StandardAnalyzer();
		IndexWriterConfig config = new IndexWriterConfig(standardAnalyzer);
	     
	     //Create a writer
	     IndexWriter writer = new IndexWriter(directory, config);
	     Document document = new Document ();
	     //In a real world example, content would be the actual content that needs to be indexed.
	     //Setting content to Hello World as an example.
	     document.add(new TextField("Champ", "Darius", Field.Store.YES));
	     document.add(new TextField("Winrate", "0,10", Field.Store.YES));
	     writer.addDocument(document);
	     writer.close();
	     
	     //Now let's try to search for Hello
	     IndexReader reader = DirectoryReader.open(directory);
	     IndexSearcher searcher = new IndexSearcher (reader);
	     QueryParser Champsparser = new QueryParser ("Champs", standardAnalyzer);
	     //QueryParser Itemsparser = new QueryParser ("Items", standardAnalyzer);
	     //QueryParser Runesparser = new QueryParser ("Runes", standardAnalyzer);
	     Query query = Champsparser.parse("Darius");
	     TopDocs results = searcher.search(query, 5);*/
		Indexador i = new Indexador();
		i.createIndex();
		i.searchIndex("DUO_SUPPORT", "Darius");
	     
    }
}


