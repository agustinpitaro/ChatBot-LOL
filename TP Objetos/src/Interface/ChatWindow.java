package Interface;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.lucene.queryparser.classic.ParseException;

import Query.Client;
import Query.Indexador;
import Utilities.DBGenerator;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;



public class ChatWindow {

	private JFrame frame;
	private static Indexador indexer;
	private static Client client;
	private static DBGenerator dbg;

	
	public ChatWindow() {
		initialize();
	}


	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				indexer.clear();
			}
		});
		frame.setBounds(100, 100, 695, 585);

		frame.setTitle("ChatBot-LoL ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 679, 500);
		frame.getContentPane().add(scrollPane);
		
		final JTextArea Chat = new JTextArea();
		Chat.setEditable(false);
		scrollPane.setViewportView(Chat);
		
		final JTextArea input = new JTextArea();
		input.setFont(new Font("Verdana", Font.PLAIN, 13));
		input.setBounds(0, 503, 679, 43);
		frame.getContentPane().add(input);
		input.addKeyListener(
								new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					input.setEditable(false);
					if(!input.getText().isEmpty()) {
						String quote=input.getText();                //Lee el input
						input.setText("");
						Chat.append("-->Yo: "+quote);
						quote.trim();
						while(
							quote.charAt(quote.length()-1)=='!' ||
							quote.charAt(quote.length()-1)=='.' ||
							quote.charAt(quote.length()-1)=='?'
						){
							quote=quote.substring(0,quote.length()-1);
						}
						quote.trim();
												//Aca iria el metodo del buscar con QOUTE como buscador
						int outPutType = 1 ; 	//Este seria el tipo devuelto por el metodo 
												//que indica de que forma se debe representar
						
						if( outPutType==1){
							String resultado = null;
			
							try {
								resultado = client.talk(quote);       
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							Chat.append("\n-->Bot: " + resultado);
					
						}	
					}
					Chat.append("\n");
			}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
						input.setEditable(true);
					}
				}
			}
		);
		
	}
	
	public static void main(String[] args) throws Exception {
		
		dbg = new DBGenerator("");        //Generación base de datos
		indexer = new Indexador();
		client = new Client(indexer);
		indexer.createIndex();                //Creación del indice
			
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatWindow window = new ChatWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
