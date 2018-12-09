package Interface;
import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.Document;

import org.jsoup.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import javax.swing.JScrollPane;

import java.awt.Color;

import java.awt.event.KeyListener;
import java.io.IOException;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.Math;
import java.util.ArrayList;

public class Interf extends JFrame implements KeyListener{

	private JPanel p=new JPanel();
	private JTextArea dialog=new JTextArea(20,50);
	private JTextArea input=new JTextArea(1,50);
	private JScrollPane scroll=new JScrollPane(
		dialog,
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
	);
	
	
	
	private String[][] chatBot={
		//standard greetings
		{"Hola","Hola!"},
		{"Hola","hello","hey"},
		//question greetings
		{"how are you","how r you","how r u","how are u"},
		{"good","doing well"},
		//yes
		{"yes"},
		{"no","NO","NO!!!!!!!"},
		//default
		{"shut up","you're bad","noob","stop talking",
		"(michael is unavailable, due to LOL)"}
	};
	
	public Interf(){
		super("Chat Bot");
		setSize(600,400);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		dialog.setEditable(false);
		input.addKeyListener(this);
	
		p.add(scroll);
		p.add(input);
		p.setBackground(new Color(255,200,0));
		add(p);
		
		setVisible(true);
	}
	
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			input.setEditable(false);
			//-----grab quote-----------
			String quote=input.getText();                //Lee el input
			input.setText("");
			addText("-->You:\t"+quote);
			quote.trim();
			while(
				quote.charAt(quote.length()-1)=='!' ||
				quote.charAt(quote.length()-1)=='.' ||
				quote.charAt(quote.length()-1)=='?'
			){
				quote=quote.substring(0,quote.length()-1);
			}
			quote.trim();
			byte response=0;
			/*
			0:we're searching through chatBot[][] for matches
			1:we didn't find anything
			2:we did find something
			*/
			//-----check for matches----
			/*int j=0;//which group we're checking
			while(response==0){
				if(inArray(quote.toLowerCase(),chatBot[j*2])){
					response=2;
					int r=(int)Math.floor(Math.random()*chatBot[(j*2)+1].length);
						if (quote.contains("top")) {
							try {
								addText("\n-->Michael\t"+ searcher("D:\\limpio.txt"));
							}
							catch (IOException e1) {
								e1.printStackTrace();
							}
					
						}else
							addText("\n-->Michael\t"+chatBot[(j*2)+1][r]);
				j++;
				if(j*2==chatBot.length-1 && response==0){
					response=1;
				}
			}*/
			
			
			if (quote.contains("top")) {
				try {
					addText("\n-->Michael\t"+ searcher("D:\\limpio.txt"));
				}
				catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			//-----default--------------
			if(response==1){
				int r=(int)Math.floor(Math.random()*chatBot[chatBot.length-1].length);
				addText("\n-->Michael\t"+chatBot[chatBot.length-1][r]);
			}
			addText("\n");
	}}
	
	public void keyReleased(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			input.setEditable(true);
		}
	}
	
	public void keyTyped(KeyEvent e){}
	
	public void addText(String str){
		dialog.setText(dialog.getText()+str);
	}
	
	public boolean inArray(String in,String[] str){
		boolean match=false;
		for(int i=0;i<str.length;i++){
			if(str[i].equals(in)){
				match=true;
			}
		}
		return match;
	}
	
	private static String lineCleaner(String linea) {
		boolean marca = false;
		String resultado = "";
		for(char c :linea.toCharArray()) {
			if (c == '=')
				marca = true;
			else
				if (c == ',')
					marca = false;
			if (marca == false && c !='>' && c != ' ')
				resultado = resultado + c;
		}
		if (resultado != "")
			resultado = resultado + '\n';
		return resultado;
	}
	
	
	public static String searcher (String rute) throws IOException {    //Busca los 5 mejores TOP desde un archivo
		FileReader fr = new FileReader(rute);
	    BufferedReader br = new BufferedReader(fr);
	    String linea;
	    String result = "";
	    int line = 1;
	    int patron = 3;
	    int encontrado = 0;
        while (encontrado != 5) {
        	if (line == patron) {
        		result = result + br.readLine() + '\n';
        		encontrado++;
        		patron = patron + 5;
        	}
        	br.readLine();
        	line++;
        } 
        return result;
       }
	
	public static void main(String[] args) throws IOException {
		new Interf();
		
		
	        
		}
	}


