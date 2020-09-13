package GUI;
import javax.swing.JFrame;
import javax.swing.JTextField;

import classes.Decorator;
import classes.LatexDecoratorFactory;
import classes.Pattern;
import classes.PatternComponent;
import classes.PatternLanguage;
import classes.PatternPart;
import classes.TemplateFactory;

import javax.swing.JLabel;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.HashSet;


public class Menu {

	
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_17;
	
	private String name;
	private String template;
	private String problem;
	private String solution;
	private String context;
	private String forces;
	private String benefits;
	private String consequences;
	private String classification;
	private String intent;
	private String also;
	private String motivation;
	private String applicability;
	private String structure;
	private String participants;
	private String collaborations;
	private String implementation;
	private String sample;
	private String known;
	private String related;
	private String example;
	private String dynamics;
	private String variants;

	ArrayList<PatternLanguage> languages = new ArrayList<PatternLanguage>();
	PatternPart myPart;
	Pattern myPattern;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Menu() {
		startMenu();
	}
	
	public ArrayList<PatternLanguage> getLanguages() {
		return languages;
	}
	
	//first window with choices: New PatternLanguage, Load PatternLanguage
	private void startMenu() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 463);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPatternsEditor = new JLabel("'Patterns Editor'");
		lblPatternsEditor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPatternsEditor.setBounds(145, 13, 144, 32);
		frame.getContentPane().add(lblPatternsEditor);
		
		JButton btnNewPatternlanguage = new JButton("New PatternLanguage");
		btnNewPatternlanguage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false); //close this window
				initialize(); //call initialize function
			}
		});
		btnNewPatternlanguage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewPatternlanguage.setBounds(86, 69, 267, 44);
		frame.getContentPane().add(btnNewPatternlanguage);
		
		JButton btnLoadPattern = new JButton("Load PatternLanguage");
		btnLoadPattern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//load the PatternLanguage
				frame.setVisible(false);
				loadPatternLanguage(); //call loadPatternLanguage method for loading
			}
		});
		btnLoadPattern.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLoadPattern.setBounds(86, 207, 267, 44);
		frame.getContentPane().add(btnLoadPattern);
		
		JButton btnSavePatternlanguage = new JButton("Save PatternLanguage");
		btnSavePatternlanguage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//save the PatternLanguage
				FileOutputStream outputStream = null;
				try
				{
					outputStream = new FileOutputStream(languages.get(0).getName()+".txt"); //open file with PatternLanguage's name for writing
				}
				catch(FileNotFoundException e)
				{
					System.out.println("Error opening the file "+languages.get(0).getName()+".txt.");
					System.exit(0);
				}
				PrintWriter outputWriter = new PrintWriter(outputStream);
				languages.get(0).saveName(outputWriter); //call SaveName method for PatternLanguage
				languages.get(0).saveContents(outputWriter); //call saveContents method for PatternLanguage
				outputWriter.close( ); //close file
				frame.setVisible(false);
				if (languages.get(0).getComponentsList().size() == 0) { //if PatternLanguage is empty, send message
					message("PatternLanguage contains no Patterns!", "savePatternLanguage");
				}
				else 
				{
					message("Storage was successful!!!", "savePatternLanguage");
				}
			}
		});
		btnSavePatternlanguage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSavePatternlanguage.setBounds(86, 138, 267, 44);
		frame.getContentPane().add(btnSavePatternlanguage);
		
		JButton btnNewButton = new JButton("Save PatternLanguage in LaTeX");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//save the PatternLanguage in Latex File
				String fileName = languages.get(0).getName();
				LatexDecoratorFactory dFactory = new LatexDecoratorFactory();
				languages.get(0).decorateComponents(dFactory); //decorate PatternLanguage
				Decorator decoratedLanguage = (Decorator)(dFactory.createLanguageDecorator(languages.get(0)));
				
				FileOutputStream outputStream = null;
				try
				{
					outputStream = new FileOutputStream(fileName+".tex"); //open file with PatternLanguage's name for writing
				}
				catch(FileNotFoundException e)
				{
					System.out.println("Error opening the file "+fileName+".tex.");
					System.exit(0);
				}
				PrintWriter outputWriter = new PrintWriter(outputStream);
				outputWriter.write("\\documentclass{article}\n"); //write start labels for LaTeX 
				outputWriter.write("\\begin{document}\n");
				decoratedLanguage.saveName(outputWriter); //call saveName method for decorated PatternLanguage
				decoratedLanguage.saveContents(outputWriter); //call saveContents method for decorated PatternLanguage
				outputWriter.write("\\end{document}"); //write end Label for LaTeX
				outputWriter.close( ); //close file
				frame.setVisible(false);
				startMenu();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(86, 277, 267, 44);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnLoadPatternlanguageFrom = new JButton("Load PatternLanguage from LaTeX");
		btnLoadPatternlanguageFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				////load the PatternLanguage from Latex File
				frame.setVisible(false);
				loadLatexPatternLanguage(); //call loadLatexPatternLanguage method for loading
			}
		});
		btnLoadPatternlanguageFrom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLoadPatternlanguageFrom.setBounds(86, 345, 267, 44);
		frame.getContentPane().add(btnLoadPatternlanguageFrom);
		frame.setVisible(true);
	}
	
	//window for loading PatternLanguage from Latex File
	public void loadLatexPatternLanguage() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 322);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblInsertHereThe = new JLabel("Insert here the name of the LaTeX PatternLanguage to load:"); //ask name for PatternLanguage 
		lblInsertHereThe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInsertHereThe.setBounds(10, 35, 414, 32);
		frame.getContentPane().add(lblInsertHereThe);
		
		textField_8 = new JTextField();
		textField_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_8.setBounds(10, 86, 414, 64);
		frame.getContentPane().add(textField_8);
		textField_8.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Scanner inputStream = null;
				String fileName =  textField_8.getText();
				try 
				{
					inputStream = new Scanner(new FileInputStream(fileName+".tex")); //open File to load PatternLanguage
				}
				catch(FileNotFoundException ex)
				{
					System.out.println("File "+ fileName+".tex was not found");
					System.out.println("or could not be opened.");
					System.exit(0);
				}
				
				inputStream.nextLine(); //1st Line nothing
				inputStream.nextLine(); //2nd Line nothing
				String title = inputStream.nextLine(); //3rd line has the Name for PatternLanguage
				String PatternLanguageName = "";
				for(int i = 7;i<title.length()-1;i++) {
					PatternLanguageName += title.charAt(i); //load the name for PatternLanguage
				}
				PatternLanguage newPatternLanguage = new PatternLanguage("", new ArrayList<PatternComponent>());
				languages.clear(); //delete old PatternLanguage
				languages.add(newPatternLanguage); //add the new PatternLanguage
				languages.get(0).setName(PatternLanguageName); //the new PatternLanguage has the loaded Name
				inputStream.nextLine(); //4th line nothing
				int store = 0; //flag 
				ArrayList<PatternComponent> Parts =  new ArrayList<PatternComponent>(); //store PatternParts from loading in this ArrayList
				ArrayList<PatternComponent> Patterns =  new ArrayList<PatternComponent>(); //store Patterns from loading in this ArrayList
				while (inputStream.hasNextLine( )){ //while it is not end of file
					String line = inputStream.nextLine( ); //read line from file
					//check if line is for Pattern or for PatternPart or end of file
					String sec = ""; //for checking section label
					String sub = ""; //for checking subsection label
					String end = ""+ line.charAt(1) + line.charAt(2) + line.charAt(3); //for checking end of file label
					if ((""+line.charAt(0)).equals("\\") || line.length()>6)
					{
						for(int i=2;i<5;i++) {
							sub += line.charAt(i);
						}

						for(int i=1;i<8;i++) {
							sec += line.charAt(i);
						}
					}
	
					if(sec.equals("section")) { //if it is section label
						if(store == 0){ //if it's the first section we read
							Pattern loadPattern = new Pattern("", new ArrayList<PatternComponent>()); //create new Pattern
							Patterns.add(loadPattern); //add it to Pattern's ArrayList
							PatternPart loadPart = new PatternPart("",""); //create new PatternPart
							String loadName = ""; //line with section label has the name for the Pattern
							for(int i=9;i<line.length()-1;i++) {
								loadName += line.charAt(i);
							}
							Patterns.get(0).setName(loadName); //the Pattern has the loaded Name
							loadPart.setName("Name"); //the PatternPart with the Name "Name" has also this name
							loadPart.setContents(loadName); //So, the PatternPart with Name "Name" has as contents the loaded Name
							Parts.add(loadPart); //add it to PatternPart's ArrayList
							store = 1; //change flag's value
							inputStream.nextLine(); //do nothing
							inputStream.nextLine(); //do nothing
						}
						else { //if it's not the first section we read
							for(PatternComponent p : Parts) { //read now a New Pattern so add all the PatternPart's we had to the previous Pattern
								Patterns.get(0).add(p);
							}
							Parts.clear(); //delete the previous PatternParts, so Parts ArrayList is empty
							languages.get(0).add(Patterns.get(0)); //add the previous Pattern to PatternLanguage
							Patterns.remove(0); //delete it
							Pattern loadPattern = new Pattern("", new ArrayList<PatternComponent>()); //create new Pattern
							Patterns.add(loadPattern); //add it to Pattern's ArrayList
							PatternPart loadPart = new PatternPart("",""); //create new PatternPart
							String loadName = ""; //line with section label has the name for the Pattern
							for(int i=9;i<line.length()-1;i++)
							{
								loadName += line.charAt(i);
							}
							Patterns.get(0).setName(loadName); //the Pattern has the loaded Name
							loadPart.setName("Name"); //the PatternPart with the Name "Name" has also this name
							loadPart.setContents(loadName); //So, the PatternPart with Name "Name" has as contents the loaded Name
							Parts.add(loadPart); //add it to PatternPart's ArrayList
							inputStream.nextLine(); //do nothing
							inputStream.nextLine(); //do nothing
						}
					}
					else if(sub.equals("sub")) {  //if it is subsection label
						PatternPart loadPart = new PatternPart("",""); //create new PatternPart
						String partName = ""; //load from this line it's Name
						for(int i=13;i<line.length()-1;i++)
						{
							partName += line.charAt(i);
						}
						loadPart.setName(partName); //PatternPart has the loaded Name
						String loadContentLine = inputStream.nextLine( ); //next line has the contents for the PatternPart
						String loadContent = ""; //load from this line the contents
						
						for(int i=1;i<loadContentLine.length();i++)
						{
							loadContent += loadContentLine.charAt(i);
						}
						loadPart.setContents(loadContent); //PatternPart has the loaded contents
						Parts.add(loadPart); //add the PatternPart to PatternPart's ArrayList
					}
					else if(end.equals("end")){ //if it is end label
						for(PatternComponent p : Parts) { //add all the PatternPart's we had to the last Pattern
							Patterns.get(0).add(p);
						}
						Parts.clear(); //delete the last PatternParts, so Parts ArrayList is empty
						languages.get(0).add(Patterns.get(0)); //add the last Pattern to PatternLanguage
						Patterns.remove(0); //delete it
					}
				}
				inputStream.close(); //close file
				frame.setVisible(false);
				PatternMenu();
			}
		});
		btnOk.setBackground(Color.GREEN);
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnOk.setBounds(316, 195, 89, 39);
		frame.getContentPane().add(btnOk);
		frame.setVisible(true);
	}
	
	//window for loading PatternLanguage from text File
	public void loadPatternLanguage() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 322);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblInsertHereThe = new JLabel("Insert here the name of the PatternLanguage to load:"); //ask name for PatternLanguage to load
		lblInsertHereThe.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInsertHereThe.setBounds(35, 43, 360, 26);
		frame.getContentPane().add(lblInsertHereThe);
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_7.setBounds(35, 102, 360, 62);
		frame.getContentPane().add(textField_7);
		textField_7.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Scanner inputStream = null;
				String fileName =  textField_7.getText();
				try 
				{
					inputStream = new Scanner(new FileInputStream(fileName+".txt")); //open File to load PatternLanguage
				}
				catch(FileNotFoundException ex)
				{
					System.out.println("File "+ fileName+".txt was not found");
					System.out.println("or could not be opened.");
					System.exit(0);
				}
				
				HashSet<String> mySet = new HashSet<String>(); //HashSet with all PatternPart's Names 
				mySet.add("Template");
				mySet.add("Problem");
				mySet.add("Solution");
				mySet.add("Context");
				mySet.add("Forces");
				mySet.add("Benefits");
				mySet.add("Consequences");
				mySet.add("Pattern Classification");
				mySet.add("Intent");
				mySet.add("Also known As");
				mySet.add("Motivation");
				mySet.add("Applicability");
				mySet.add("Structure");
				mySet.add("Participants");
				mySet.add("Collaborations");
				mySet.add("Implementation");
				mySet.add("Sample Code");
				mySet.add("Known Uses");
				mySet.add("Related Patterns");
				mySet.add("Example");
				mySet.add("Dynamics");
				mySet.add("Example Resolved");
				mySet.add("Variants");
				
				String PatternLanguageName = inputStream.nextLine( ); //load PatternLanguage's Name
				PatternLanguage newPatternLanguage = new PatternLanguage("", new ArrayList<PatternComponent>()); //create new PatternLanguage
				languages.clear(); //delete old PatternLanguage
				languages.add(newPatternLanguage); //add the new PatternLanguage
				languages.get(0).setName(PatternLanguageName); //the new PatternLanguage has the loaded Name
				int store = 0; //flag 
				ArrayList<PatternComponent> Parts =  new ArrayList<PatternComponent>();  //store PatternParts from loading in this ArrayList
				ArrayList<PatternComponent> Patterns =  new ArrayList<PatternComponent>(); //store Patterns from loading in this ArrayList
				while (inputStream.hasNextLine( )){ //while it is not end of file
					String line = inputStream.nextLine( ); //read line from file
					if(line.equals("Name")) //if line has Name as content
					{
						if(store == 0) //if it's the first line with Name as content
						{
							Pattern loadPattern = new Pattern("", new ArrayList<PatternComponent>()); //create new Pattern
							Patterns.add(loadPattern); //add it to Pattern's ArrayList
							PatternPart loadPart = new PatternPart("",""); //create new PatternPart
							String loadName = inputStream.nextLine( ); //next line has the name value
							Patterns.get(0).setName(loadName); //the Pattern has the loaded Name
							loadPart.setName("Name"); //the PatternPart with the Name "Name" has also this name
							loadPart.setContents(loadName); //So, the PatternPart with Name "Name" has as contents the loaded Name
							Parts.add(loadPart); //add it to PatternPart's ArrayList
							store = 1; //change flag's value
						}
						else //if it's not the first line with Name as content
						{
							for(PatternComponent p : Parts) { //read now a New Pattern so add all the PatternPart's we had to the previous Pattern
								Patterns.get(0).add(p);
							}
							Parts.clear(); //delete the previous PatternParts, so Parts ArrayList is empty
							languages.get(0).add(Patterns.get(0)); //add the previous Pattern to PatternLanguage
							Patterns.remove(0); //delete it
							Pattern loadPattern = new Pattern("", new ArrayList<PatternComponent>()); //create new Pattern
							Patterns.add(loadPattern); //add it to Pattern's ArrayList
							PatternPart loadPart = new PatternPart("",""); //create new PatternPart
							String loadName = inputStream.nextLine( ); //next line has the name value
							Patterns.get(0).setName(loadName);  //the Pattern has the loaded Name
							loadPart.setName("Name"); //the PatternPart with the Name "Name" has also this name
							loadPart.setContents(loadName); //So, the PatternPart with Name "Name" has as contents the loaded Name
							Parts.add(loadPart); //add it to PatternPart's ArrayList
						}
						
					}
					else if(mySet.contains(line)) //if line has as content a PatternPart's Name
					{
						PatternPart loadPart = new PatternPart("",""); //create new PatternPart
						loadPart.setName(line); //PatternPart has the loaded Name
						String loadContent = inputStream.nextLine( ); //next line has the contents for the PatternPart
						loadPart.setContents(loadContent); //PatternPart has the loaded contents
						Parts.add(loadPart); //add the PatternPart to PatternPart's ArrayList
					}
					else 
					{
						continue;
					}
				}
				//for the last Pattern
				for(PatternComponent p : Parts) { //add all the PatternPart's we had to the last Pattern
					Patterns.get(0).add(p);
				}
				Parts.clear(); //delete the last PatternParts, so Parts ArrayList is empty
				languages.get(0).add(Patterns.get(0)); //add the last Pattern to PatternLanguage
				Patterns.remove(0); //delete it
				inputStream.close(); //close file
				frame.setVisible(false);
				PatternMenu();
			}
		});
		btnOk.setBackground(Color.GREEN);
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnOk.setBounds(280, 201, 102, 41);
		frame.getContentPane().add(btnOk);
		frame.setVisible(true);
	}
	
	//window for New PatternLanguage choice
	public void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		JLabel lblPleaseInsertName = new JLabel("Please insert name for PatternLanguage:"); //ask name for PatternLanguage
		lblPleaseInsertName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPleaseInsertName.setBounds(71, 51, 286, 20);
		frame.getContentPane().add(lblPleaseInsertName);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(71, 105, 286, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBackground(Color.GREEN);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PatternLanguage newPLanguage = new PatternLanguage("", new ArrayList<PatternComponent>());
				languages.add(newPLanguage);
				String name2 =  textField.getText();
				if (name2.equals("")){ //if no name given, name set to default and call message function
					frame.setVisible(false);
					name2 = "Default";
					languages.get(0).setName(name2);
					String m = "No name given. Name set to Default!";
					String n = "menu";
					message(m,n); //call message function
				}
				else {
					languages.get(0).setName(name2); //create new PatternLanguage
					frame.setVisible(false); //close this window
					PatternMenu(); // call PatternMenu function
				}
			}
		});
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnOk.setBounds(100, 160, 97, 35);
		frame.getContentPane().add(btnOk);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.setBackground(Color.RED);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//my code
				System.exit(0);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancel.setBounds(224, 159, 97, 36);
		frame.getContentPane().add(btnCancel);
	}
	
//window with choices: New Pattern, Remove Pattern, Edit Pattern
	public void PatternMenu() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 322);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JButton btnNewPattern = new JButton("New Pattern");
		btnNewPattern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				newPattern();
			}
		});
		btnNewPattern.setBounds(127, 41, 155, 38);
		btnNewPattern.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(btnNewPattern);
		
		JButton button = new JButton("Remove Pattern");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//remove pattern
				frame.setVisible(false);
				removePattern();
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 16));
		button.setBounds(127, 102, 155, 38);
		frame.getContentPane().add(button);
		
		JButton btnEditPattern = new JButton("Edit Pattern");
		btnEditPattern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//edit pattern
				frame.setVisible(false);
				editPattern();
			}
		});
		btnEditPattern.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEditPattern.setBounds(127, 162, 152, 38);
		frame.getContentPane().add(btnEditPattern);
		
		JButton btnNewButton = new JButton("\u2190 Back to PatternLanguage Menu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				startMenu();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(156, 233, 257, 27);
		frame.getContentPane().add(btnNewButton);
	}
	
	//window for new Pattern with Pattern Templates
	public void newPattern() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 375);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel(" 'Pattern Templates' ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(126, 11, 171, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnMicroPattern = new JButton("Micro - Pattern Template");
		btnMicroPattern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				microPattern();
			}
		});
		btnMicroPattern.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnMicroPattern.setBounds(82, 42, 262, 37);
		frame.getContentPane().add(btnMicroPattern);
		
		JButton btnInductiveMini = new JButton("Inductive Mini - Pattern");
		btnInductiveMini.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				miniPattern();
			}
		});
		btnInductiveMini.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnInductiveMini.setBounds(82, 90, 262, 37);
		frame.getContentPane().add(btnInductiveMini);
		
		JButton btnDeductiveMini = new JButton("Deductive Mini - Pattern");
		btnDeductiveMini.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				dminiPattern();
			}
		});
		btnDeductiveMini.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDeductiveMini.setBounds(82, 139, 262, 37);
		frame.getContentPane().add(btnDeductiveMini);
		
		JButton btnGangOf = new JButton("Gang - of - Four Pattern");
		btnGangOf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				gofPattern();
			}
		});
		btnGangOf.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGangOf.setBounds(82, 186, 262, 37);
		frame.getContentPane().add(btnGangOf);
		
		JButton btnSystemOfPatterns = new JButton("System of Patterns Template");
		btnSystemOfPatterns.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				sopPattern();
			}
		});
		btnSystemOfPatterns.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSystemOfPatterns.setBounds(82, 234, 262, 37);
		frame.getContentPane().add(btnSystemOfPatterns);
		
		JButton btnNext = new JButton("Next");
		btnNext.setBackground(Color.GREEN);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if no pattern selected, pattern set to default and call message function
				frame.setVisible(false);
				message("<html>No Pattern Template given.<br/> Pattern Template set to default, Micro - Pattern Template!</html>","PatternTemplate");
			}
		});
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNext.setBounds(302, 288, 89, 37);
		frame.getContentPane().add(btnNext);
		frame.setVisible(true);
	}
	
	//window for microPattern
	public void microPattern() {
		frame = new JFrame();
		frame.setBounds(100, 100, 697, 533);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblmicroPattern = new JLabel("'Micro - Pattern Template'");
		lblmicroPattern.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblmicroPattern.setBounds(246, 11, 181, 14);
		frame.getContentPane().add(lblmicroPattern);
		
		JLabel lblNameWhatShall = new JLabel("Name (What shall this pattern be called by practitioners?)");
		lblNameWhatShall.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNameWhatShall.setBounds(28, 60, 380, 19);
		frame.getContentPane().add(lblNameWhatShall);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setBounds(28, 90, 609, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblTemplateWhichTemplate = new JLabel("Template (Which template is followed for the pattern specification?)");
		lblTemplateWhichTemplate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTemplateWhichTemplate.setBounds(28, 131, 453, 25);
		frame.getContentPane().add(lblTemplateWhichTemplate);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_1.setBounds(28, 161, 609, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblProblemWhatIs = new JLabel("Problem (What is motivating us to apply this pattern?) ");
		lblProblemWhatIs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblProblemWhatIs.setBounds(28, 204, 474, 19);
		frame.getContentPane().add(lblProblemWhatIs);
		
		JLabel lblSolutionHowDo = new JLabel("Solution (How do we solve the problem?)");
		lblSolutionHowDo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSolutionHowDo.setBounds(28, 323, 391, 19);
		frame.getContentPane().add(lblSolutionHowDo);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea.setBounds(28, 234, 609, 70);
		frame.getContentPane().add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea_1.setBounds(28, 353, 609, 70);
		frame.getContentPane().add(textArea_1);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//the form was completed
				name =  textField.getText();
				template =  textField_1.getText();
				problem =  textArea.getText();
				solution =  textArea_1.getText();
				TemplateFactory micro = new TemplateFactory();
				myPattern = micro.createTemplate("Micro-Pattern Template");
				
				for(int i = 0; i < myPattern.getComponentsList().size(); i++) {
					if (myPattern.getComponentsList().get(i).getName().equals("Name")) {
						myPattern.getComponentsList().get(i).setContents(name);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Template")) {
						myPattern.getComponentsList().get(i).setContents(template);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Problem")) {
						myPattern.getComponentsList().get(i).setContents(problem);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Solution")) {
						myPattern.getComponentsList().get(i).setContents(solution);
					}
				}
				
				myPattern.setName(name);
				languages.get(0).add(myPattern);
				frame.setVisible(false);
				PatternMenu();
			}
		});
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnOk.setBackground(Color.GREEN);
		btnOk.setBounds(438, 456, 89, 23);
		frame.getContentPane().add(btnOk);
		
		JButton btnNewButton = new JButton("CANCEL");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				PatternMenu();
			}
		});
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(548, 458, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		frame.setVisible(true);
	}
	
	//window for miniPattern
	public void miniPattern() {
		frame = new JFrame();
		frame.setBounds(100, 100, 697, 628);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblinductiveMini = new JLabel("'Inductive Mini - Pattern'");
		lblinductiveMini.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblinductiveMini.setBounds(242, 11, 171, 25);
		frame.getContentPane().add(lblinductiveMini);
		
		JLabel lblNameWhatShall = new JLabel("Name (What shall this pattern be called by practitioners?)");
		lblNameWhatShall.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNameWhatShall.setBounds(23, 64, 377, 19);
		frame.getContentPane().add(lblNameWhatShall);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_4.setBounds(23, 94, 628, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblTemplateWhichTemplate = new JLabel("Template (Which template is followed for the pattern specification?) ");
		lblTemplateWhichTemplate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTemplateWhichTemplate.setBounds(23, 125, 465, 19);
		frame.getContentPane().add(lblTemplateWhichTemplate);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_5.setBounds(23, 155, 628, 20);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblContextWhatAre = new JLabel("Context (What are the assumed environment or a priori assumptions for applying this pattern?) ");
		lblContextWhatAre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblContextWhatAre.setBounds(23, 186, 628, 19);
		frame.getContentPane().add(lblContextWhatAre);
		
		JLabel lblForcesWhatAre = new JLabel("Forces (What are the different design motivations that must be balanced?)");
		lblForcesWhatAre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblForcesWhatAre.setBounds(23, 307, 628, 19);
		frame.getContentPane().add(lblForcesWhatAre);
		
		JLabel lblSolutionHowDo = new JLabel("Solution (How do we solve the problem?)");
		lblSolutionHowDo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSolutionHowDo.setBounds(23, 435, 628, 19);
		frame.getContentPane().add(lblSolutionHowDo);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea.setBounds(23, 216, 628, 80);
		frame.getContentPane().add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea_1.setBounds(23, 337, 628, 80);
		frame.getContentPane().add(textArea_1);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea_2.setBounds(23, 465, 628, 80);
		frame.getContentPane().add(textArea_2);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//the form was completed
				name =  textField_4.getText();
				template =  textField_5.getText();
				context =  textArea.getText();
				forces =  textArea_1.getText();
				solution =  textArea_2.getText();
				TemplateFactory mini = new TemplateFactory();
				myPattern = mini.createTemplate("Inductive Mini-Pattern");
				for(int i = 0; i < myPattern.getComponentsList().size(); i++) {
					if (myPattern.getComponentsList().get(i).getName().equals("Name")) {
						myPattern.getComponentsList().get(i).setContents(name);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Template")) {
						myPattern.getComponentsList().get(i).setContents(template);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Context")) {
						myPattern.getComponentsList().get(i).setContents(context);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Forces")) {
						myPattern.getComponentsList().get(i).setContents(forces);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Solution")) {
						myPattern.getComponentsList().get(i).setContents(solution);
					}
				}
				myPattern.setName(name);
				languages.get(0).add(myPattern);
				frame.setVisible(false);
				PatternMenu();
			}
		});
		btnOk.setBackground(Color.GREEN);
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnOk.setBounds(456, 555, 89, 23);
		frame.getContentPane().add(btnOk);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				PatternMenu();
			}
		});
		btnCancel.setBackground(Color.RED);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancel.setBounds(562, 556, 89, 23);
		frame.getContentPane().add(btnCancel);	
		
		frame.setVisible(true);
	}
	
	//window for dminiPattern
	public void dminiPattern() {
		frame = new JFrame();
		frame.setBounds(100, 100, 697, 702);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbldeductiveMini = new JLabel("'Deductive Mini - Pattern'");
		lbldeductiveMini.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbldeductiveMini.setBounds(252, 11, 175, 25);
		frame.getContentPane().add(lbldeductiveMini);
		
		JLabel lblNameWhatShall = new JLabel("Name (What shall this pattern be called by practitioners?) ");
		lblNameWhatShall.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNameWhatShall.setBounds(40, 63, 402, 19);
		frame.getContentPane().add(lblNameWhatShall);
		
		textField_9 = new JTextField();
		textField_9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_9.setBounds(40, 93, 614, 20);
		frame.getContentPane().add(textField_9);
		textField_9.setColumns(10);
		
		JLabel lblTemplateWhichTemplate = new JLabel("Template (Which template is followed for the pattern specification?) ");
		lblTemplateWhichTemplate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTemplateWhichTemplate.setBounds(40, 124, 598, 19);
		frame.getContentPane().add(lblTemplateWhichTemplate);
		
		textField_10 = new JTextField();
		textField_10.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_10.setBounds(40, 154, 614, 20);
		frame.getContentPane().add(textField_10);
		textField_10.setColumns(10);
		
		JLabel lblProblemWhatIs = new JLabel("Problem (What is motivating us to apply this pattern?) ");
		lblProblemWhatIs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblProblemWhatIs.setBounds(40, 185, 598, 19);
		frame.getContentPane().add(lblProblemWhatIs);
		
		JLabel lblSolutionHowDo = new JLabel("Solution (How do we solve the problem?)");
		lblSolutionHowDo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSolutionHowDo.setBounds(40, 296, 598, 19);
		frame.getContentPane().add(lblSolutionHowDo);
		
		JLabel lblBenefitsWhatAre = new JLabel("Benefits (What are the potential positive outcomes of applying this pattern?) ");
		lblBenefitsWhatAre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBenefitsWhatAre.setBounds(40, 407, 598, 19);
		frame.getContentPane().add(lblBenefitsWhatAre);
		
		JLabel lblConsequencesWhatAre = new JLabel("Consequences (What are potential shortcomings and consequences of applying this pattern?) ");
		lblConsequencesWhatAre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConsequencesWhatAre.setBounds(40, 518, 614, 19);
		frame.getContentPane().add(lblConsequencesWhatAre);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea.setBounds(40, 215, 614, 70);
		frame.getContentPane().add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea_1.setBounds(40, 326, 614, 70);
		frame.getContentPane().add(textArea_1);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea_2.setBounds(40, 437, 614, 70);
		frame.getContentPane().add(textArea_2);
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea_3.setBounds(40, 548, 614, 70);
		frame.getContentPane().add(textArea_3);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//the form was completed
				name =  textField_9.getText();
				template =  textField_10.getText();
				problem =  textArea.getText();
				solution =  textArea_1.getText();
				benefits =  textArea_2.getText();
				consequences =  textArea_3.getText();
				TemplateFactory dmini = new TemplateFactory();
				myPattern = dmini.createTemplate("Deductive Mini-Pattern");
				for(int i = 0; i < myPattern.getComponentsList().size(); i++) {
					if (myPattern.getComponentsList().get(i).getName().equals("Name")) {
						myPattern.getComponentsList().get(i).setContents(name);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Template")) {
						myPattern.getComponentsList().get(i).setContents(template);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Problem")) {
						myPattern.getComponentsList().get(i).setContents(problem);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Solution")) {
						myPattern.getComponentsList().get(i).setContents(solution);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Benefits")) {
						myPattern.getComponentsList().get(i).setContents(benefits);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Consequences")) {
						myPattern.getComponentsList().get(i).setContents(consequences);
					}
				}
				myPattern.setName(name);
				languages.get(0).add(myPattern);
				frame.setVisible(false);
				PatternMenu();
			}
		});
		btnOk.setBackground(Color.GREEN);
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnOk.setBounds(458, 632, 89, 25);
		frame.getContentPane().add(btnOk);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				PatternMenu();
			}
		});
		btnCancel.setBackground(Color.RED);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancel.setBounds(565, 633, 89, 23);
		frame.getContentPane().add(btnCancel);
		
		frame.setVisible(true);
	}
	
	//first window for gang of four Pattern 
	public void gofPattern() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.setBounds(100, 100, 697, 671);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblgangOf = new JLabel("'Gang - of - Four Pattern'");
		lblgangOf.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblgangOf.setBounds(246, 11, 176, 20);
		frame.getContentPane().add(lblgangOf);
		
		JLabel lblNameWhatIs = new JLabel("Name (What is the pattern called?) ");
		lblNameWhatIs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNameWhatIs.setBounds(36, 71, 611, 20);
		frame.getContentPane().add(lblNameWhatIs);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_1.setBounds(36, 93, 621, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblTemplateWhichTemplate = new JLabel("Template (Which template is followed for the pattern specification?) ");
		lblTemplateWhichTemplate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTemplateWhichTemplate.setBounds(36, 124, 621, 20);
		frame.getContentPane().add(lblTemplateWhichTemplate);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_2.setBounds(36, 155, 621, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblPatternClassificationIs = new JLabel("Pattern Classification (Is the pattern creational, structural, or behavioral?) ");
		lblPatternClassificationIs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPatternClassificationIs.setBounds(36, 186, 621, 20);
		frame.getContentPane().add(lblPatternClassificationIs);
		
		textField_17 = new JTextField();
		textField_17.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_17.setBounds(36, 217, 621, 20);
		frame.getContentPane().add(textField_17);
		textField_17.setColumns(10);
		
		JLabel lblIntentWhatProblem = new JLabel("Intent: What problem does this pattern solve? ");
		lblIntentWhatProblem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIntentWhatProblem.setBounds(36, 248, 621, 20);
		frame.getContentPane().add(lblIntentWhatProblem);

		JLabel lblAlsoKnownAs = new JLabel("Also Known As (What are other names for this pattern?) ");
		lblAlsoKnownAs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAlsoKnownAs.setBounds(36, 329, 621, 20);
		frame.getContentPane().add(lblAlsoKnownAs);
		
		JLabel lblMotivationWhatIs = new JLabel("Motivation (What is an example scenario for applying this pattern?)");
		lblMotivationWhatIs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMotivationWhatIs.setBounds(36, 410, 621, 20);
		frame.getContentPane().add(lblMotivationWhatIs);
		
		JLabel lblApplicabilityWhenDoes = new JLabel("Applicability (When does this pattern apply?) ");
		lblApplicabilityWhenDoes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblApplicabilityWhenDoes.setBounds(36, 491, 621, 20);
		frame.getContentPane().add(lblApplicabilityWhenDoes);

		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea.setBounds(36, 279, 621, 39);
		frame.getContentPane().add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea_1.setBounds(36, 360, 621, 39);
		frame.getContentPane().add(textArea_1);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea_2.setBounds(36, 441, 621, 39);
		frame.getContentPane().add(textArea_2);
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea_3.setBounds(36, 522, 621, 39);
		frame.getContentPane().add(textArea_3);
		
		JLabel label = new JLabel("1/2");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(635, 616, 22, 14);
		frame.getContentPane().add(label);
		
		JButton button = new JButton("\u2192");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name =  textField_1.getText();
				template =  textField_2.getText();
				classification =  textField_17.getText();
				intent =  textArea.getText();
				also =  textArea_1.getText();
				motivation =  textArea_2.getText();
				applicability =  textArea_3.getText();
				frame.setVisible(false);
				gofPattern2();
			}
		});
		button.setBackground(Color.CYAN);
		button.setFont(new Font("Tahoma", Font.BOLD, 20));
		button.setBounds(563, 572, 94, 23);
		frame.getContentPane().add(button);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				PatternMenu();
			}
		});
		btnCancel.setBackground(Color.RED);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancel.setBounds(448, 572, 94, 23);
		frame.getContentPane().add(btnCancel);
		frame.setVisible(true);
	}
	
	//second window for gang of four Pattern
	public void gofPattern2() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.setBounds(100, 100, 697, 748);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNameWhatIs = new JLabel("Structure (Which are the classes of the objects in this pattern?) ");
		lblNameWhatIs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNameWhatIs.setBounds(36, 11, 611, 20);
		frame.getContentPane().add(lblNameWhatIs);
		
		
		JLabel lblPatternClassificationIs = new JLabel("Participants (What are the objects that participate in this pattern?) ");
		lblPatternClassificationIs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPatternClassificationIs.setBounds(36, 84, 621, 20);
		frame.getContentPane().add(lblPatternClassificationIs);
		
		
		JLabel lblIntentWhatProblem = new JLabel("Collaborations (How do these objects interoperate?) ");
		lblIntentWhatProblem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIntentWhatProblem.setBounds(36, 158, 621, 20);
		frame.getContentPane().add(lblIntentWhatProblem);
		
		JLabel lblAlsoKnownAs = new JLabel("Consequences (What are the trade\u2212offs of using this pattern?)");
		lblAlsoKnownAs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAlsoKnownAs.setBounds(36, 243, 621, 20);
		frame.getContentPane().add(lblAlsoKnownAs);
		
		JLabel lblMotivationWhatIs = new JLabel("Implementation (Which techniques or issues arise in applying this pattern?)");
		lblMotivationWhatIs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMotivationWhatIs.setBounds(36, 328, 621, 20);
		frame.getContentPane().add(lblMotivationWhatIs);
		
		JLabel lblApplicabilityWhenDoes = new JLabel("Sample Code (What is an example of the pattern in source code?)");
		lblApplicabilityWhenDoes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblApplicabilityWhenDoes.setBounds(36, 416, 621, 20);
		frame.getContentPane().add(lblApplicabilityWhenDoes);
		
		JLabel label = new JLabel("2/2");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(634, 695, 22, 14);
		frame.getContentPane().add(label);
		
		JLabel lblKnownUsesWhat = new JLabel("Known Uses (What are some examples of real systems using this pattern?) ");
		lblKnownUsesWhat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblKnownUsesWhat.setBounds(36, 500, 621, 20);
		frame.getContentPane().add(lblKnownUsesWhat);
		
		JLabel lblRelatedPatternsWhat = new JLabel("Related Patterns (What other patterns from this pattern collection are related to this pattern?) ");
		lblRelatedPatternsWhat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRelatedPatternsWhat.setBounds(36, 578, 621, 20);
		frame.getContentPane().add(lblRelatedPatternsWhat);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea.setBounds(36, 34, 621, 39);
		frame.getContentPane().add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea_1.setBounds(36, 104, 621, 43);
		frame.getContentPane().add(textArea_1);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea_2.setBounds(36, 189, 621, 43);
		frame.getContentPane().add(textArea_2);
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea_3.setBounds(36, 274, 621, 43);
		frame.getContentPane().add(textArea_3);
		
		JTextArea textArea_4 = new JTextArea();
		textArea_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea_4.setBounds(36, 362, 621, 43);
		frame.getContentPane().add(textArea_4);
		
		JTextArea textArea_5 = new JTextArea();
		textArea_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea_5.setBounds(36, 450, 621, 39);
		frame.getContentPane().add(textArea_5);
		
		JTextArea textArea_6 = new JTextArea();
		textArea_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea_6.setBounds(36, 528, 621, 39);
		frame.getContentPane().add(textArea_6);
		
		JTextArea textArea_7 = new JTextArea();
		textArea_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea_7.setBounds(36, 609, 621, 43);
		frame.getContentPane().add(textArea_7);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//the form was completed
				structure =  textArea.getText();
				participants =  textArea_1.getText();
				collaborations =  textArea_2.getText();
				consequences =  textArea_3.getText();
				implementation =  textArea_4.getText();
				sample =  textArea_5.getText();
				known =  textArea_6.getText();
				related =  textArea_7.getText();
				TemplateFactory gof = new TemplateFactory();
				myPattern = gof.createTemplate("Gang-of-Four Pattern");
				for(int i = 0; i < myPattern.getComponentsList().size(); i++) {
					if (myPattern.getComponentsList().get(i).getName().equals("Name")) {
						myPattern.getComponentsList().get(i).setContents(name);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Template")) {
						myPattern.getComponentsList().get(i).setContents(template);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Pattern Classification")) {
						myPattern.getComponentsList().get(i).setContents(classification);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Intent")) {
						myPattern.getComponentsList().get(i).setContents(intent);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Also Known As")) {
						myPattern.getComponentsList().get(i).setContents(also);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Motivation")) {
						myPattern.getComponentsList().get(i).setContents(motivation);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Applicability")) {
						myPattern.getComponentsList().get(i).setContents(applicability);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Structure")) {
						myPattern.getComponentsList().get(i).setContents(structure);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Participants")) {
						myPattern.getComponentsList().get(i).setContents(participants);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Collaborations")) {
						myPattern.getComponentsList().get(i).setContents(collaborations);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Consequences")) {
						myPattern.getComponentsList().get(i).setContents(consequences);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Implementation")) {
						myPattern.getComponentsList().get(i).setContents(implementation );
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Sample Code")) {
						myPattern.getComponentsList().get(i).setContents(sample);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Known Uses")) {
						myPattern.getComponentsList().get(i).setContents(known);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Related Patterns")) {
						myPattern.getComponentsList().get(i).setContents(related);
					}
				}
				myPattern.setName(name);
				languages.get(0).add(myPattern);
				frame.setVisible(false);
				PatternMenu();
			}
		});
		btnOk.setBackground(Color.GREEN);
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnOk.setBounds(460, 663, 89, 23);
		frame.getContentPane().add(btnOk);
		
		JButton btnNewButton = new JButton("CANCEL");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				PatternMenu();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setBounds(568, 663, 89, 23);
		frame.getContentPane().add(btnNewButton);
		frame.setVisible(true);
	}
	
	//first window for system of Patterns
	public void sopPattern() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.setBounds(100, 100, 697, 636);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblgangOf = new JLabel("'System of Patterns Template'");
		lblgangOf.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblgangOf.setBounds(231, 11, 209, 20);
		frame.getContentPane().add(lblgangOf);
		
		JLabel lblNameWhatIs = new JLabel("Name (What is the pattern called?) ");
		lblNameWhatIs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNameWhatIs.setBounds(36, 56, 611, 20);
		frame.getContentPane().add(lblNameWhatIs);
		
		
		
		JLabel lblTemplateWhichTemplate = new JLabel("Template (Which template is followed for the pattern specification?) ");
		lblTemplateWhichTemplate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTemplateWhichTemplate.setBounds(36, 108, 621, 20);
		frame.getContentPane().add(lblTemplateWhichTemplate);
		
		
		
		JLabel lblPatternClassificationIs = new JLabel("Also Known As (What are other names for this pattern?) ");
		lblPatternClassificationIs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPatternClassificationIs.setBounds(36, 161, 621, 20);
		frame.getContentPane().add(lblPatternClassificationIs);
		
	
		
		JLabel lblIntentWhatProblem = new JLabel("Example (What is an example of the need for this pattern?) ");
		lblIntentWhatProblem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIntentWhatProblem.setBounds(36, 214, 621, 20);
		frame.getContentPane().add(lblIntentWhatProblem);
		
		
		
		JLabel lblAlsoKnownAs = new JLabel("Context (When does this pattern apply?)");
		lblAlsoKnownAs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAlsoKnownAs.setBounds(36, 296, 621, 20);
		frame.getContentPane().add(lblAlsoKnownAs);
		
		
		
		JLabel lblMotivationWhatIs = new JLabel("Problem (What is the problem solved by this pattern?)");
		lblMotivationWhatIs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMotivationWhatIs.setBounds(36, 378, 621, 20);
		frame.getContentPane().add(lblMotivationWhatIs);
		
		
		
		JLabel lblApplicabilityWhenDoes = new JLabel("Solution (What is the underlying principal underlying this pattern?)");
		lblApplicabilityWhenDoes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblApplicabilityWhenDoes.setBounds(36, 461, 621, 20);
		frame.getContentPane().add(lblApplicabilityWhenDoes);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_2.setBounds(36, 77, 621, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_3.setBounds(36, 130, 621, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField_6.setBounds(36, 183, 621, 20);
		frame.getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea.setBounds(36, 236, 621, 49);
		frame.getContentPane().add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea_1.setBounds(36, 318, 621, 49);
		frame.getContentPane().add(textArea_1);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea_2.setBounds(35, 401, 622, 49);
		frame.getContentPane().add(textArea_2);
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea_3.setBounds(36, 483, 621, 49);
		frame.getContentPane().add(textArea_3);
		
		
		JLabel label = new JLabel("1/2");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(635, 577, 22, 14);
		frame.getContentPane().add(label);
		
		JButton button = new JButton("\u2192");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name =  textField_2.getText();
				template =  textField_3.getText();
				classification =  textField_6.getText();
				intent =  textArea.getText();
				also =  textArea_1.getText();
				motivation =  textArea_2.getText();
				applicability =  textArea_3.getText();
				frame.setVisible(false);
				sopPattern2();
			}
		});
		button.setBackground(Color.CYAN);
		button.setFont(new Font("Tahoma", Font.BOLD, 20));
		button.setBounds(574, 543, 83, 23);
		frame.getContentPane().add(button);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				PatternMenu();
			}
		});
		btnCancel.setBackground(Color.RED);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancel.setBounds(469, 543, 89, 23);
		frame.getContentPane().add(btnCancel);
		frame.setVisible(true);
	}

	//second window for system of Patterns
	void sopPattern2() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.setBounds(100, 100, 697, 647);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNameWhatIs = new JLabel("Structure (What objects are involved and related?) ");
		lblNameWhatIs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNameWhatIs.setBounds(20, 11, 627, 20);
		frame.getContentPane().add(lblNameWhatIs);
		
		JLabel lblPatternClassificationIs = new JLabel("Dynamics (How do these objects collaborate?)");
		lblPatternClassificationIs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPatternClassificationIs.setBounds(20, 86, 621, 20);
		frame.getContentPane().add(lblPatternClassificationIs);
		
		JLabel lblIntentWhatProblem = new JLabel("Implementation (What are some guidelines for implementing this pattern?)");
		lblIntentWhatProblem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIntentWhatProblem.setBounds(20, 164, 621, 20);
		frame.getContentPane().add(lblIntentWhatProblem);
		
		JLabel lblAlsoKnownAs = new JLabel("Example Resolved (Show how the previous example is resolved using the pattern.)");
		lblAlsoKnownAs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAlsoKnownAs.setBounds(20, 243, 621, 20);
		frame.getContentPane().add(lblAlsoKnownAs);
		
		JLabel lblMotivationWhatIs = new JLabel("Variants (What are important variations of this pattern?)");
		lblMotivationWhatIs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMotivationWhatIs.setBounds(20, 321, 621, 20);
		frame.getContentPane().add(lblMotivationWhatIs);
		
		JLabel lblApplicabilityWhenDoes = new JLabel("Known Uses (What are real\u2212world systems using this pattern?)");
		lblApplicabilityWhenDoes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblApplicabilityWhenDoes.setBounds(20, 399, 621, 20);
		frame.getContentPane().add(lblApplicabilityWhenDoes);
		
		JLabel label = new JLabel("2/2");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(649, 591, 22, 14);
		frame.getContentPane().add(label);
		
		JLabel lblKnownUsesWhat = new JLabel("Consequences (What are the benefits and liabilities of using this pattern?)");
		lblKnownUsesWhat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblKnownUsesWhat.setBounds(20, 477, 621, 20);
		frame.getContentPane().add(lblKnownUsesWhat);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea.setBounds(20, 30, 651, 45);
		frame.getContentPane().add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea_1.setBounds(20, 108, 651, 45);
		frame.getContentPane().add(textArea_1);
		
		JTextArea textArea_2 = new JTextArea();
		textArea_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea_2.setBounds(20, 187, 651, 45);
		frame.getContentPane().add(textArea_2);
		
		JTextArea textArea_3 = new JTextArea();
		textArea_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea_3.setBounds(20, 265, 651, 45);
		frame.getContentPane().add(textArea_3);
		
		JTextArea textArea_4 = new JTextArea();
		textArea_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea_4.setBounds(20, 343, 651, 45);
		frame.getContentPane().add(textArea_4);
		
		JTextArea textArea_5 = new JTextArea();
		textArea_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea_5.setBounds(20, 421, 651, 45);
		frame.getContentPane().add(textArea_5);
		
		JTextArea textArea_6 = new JTextArea();
		textArea_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textArea_6.setBounds(20, 499, 651, 45);
		frame.getContentPane().add(textArea_6);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//the form was completed
				structure =  textArea.getText();
				dynamics =  textArea_1.getText();
				implementation =  textArea_2.getText();
				example =  textArea_3.getText();
				variants =  textArea_4.getText();
				known =  textArea_5.getText();
				consequences =  textArea_6.getText();
				TemplateFactory sop = new TemplateFactory();
				myPattern = sop.createTemplate("System of Patterns Template");
				for(int i = 0; i < myPattern.getComponentsList().size(); i++) {
					if (myPattern.getComponentsList().get(i).getName().equals("Name")) {
						myPattern.getComponentsList().get(i).setContents(name);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Template")) {
						myPattern.getComponentsList().get(i).setContents(template);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Pattern Classification")) {
						myPattern.getComponentsList().get(i).setContents(classification);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Intent")) {
						myPattern.getComponentsList().get(i).setContents(intent);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Also known As")) {
						myPattern.getComponentsList().get(i).setContents(also);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Motivation")) {
						myPattern.getComponentsList().get(i).setContents(motivation);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Applicability")) {
						myPattern.getComponentsList().get(i).setContents(applicability);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Structure")) {
						myPattern.getComponentsList().get(i).setContents(structure);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Dynamics")) {
						myPattern.getComponentsList().get(i).setContents(dynamics);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Implementation")) {
						myPattern.getComponentsList().get(i).setContents(implementation);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Example Resolved")) {
						myPattern.getComponentsList().get(i).setContents(example);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Variants")) {
						myPattern.getComponentsList().get(i).setContents(variants);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Known Uses")) {
						myPattern.getComponentsList().get(i).setContents(known);
					}
					else if(myPattern.getComponentsList().get(i).getName().equals("Consequences")) {
						myPattern.getComponentsList().get(i).setContents(consequences);
					}
				}
				myPattern.setName(name);
				languages.get(0).add(myPattern);
				frame.setVisible(false);
				PatternMenu();
			}
		});
		btnOk.setBackground(Color.GREEN);
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnOk.setBounds(477, 555, 89, 25);
		frame.getContentPane().add(btnOk);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				PatternMenu();
			}
		});
		btnCancel.setBackground(Color.RED);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancel.setBounds(582, 557, 89, 23);
		frame.getContentPane().add(btnCancel);
		frame.setVisible(true);
	}
	
	//window for remove pattern
	public void removePattern()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 588, 442);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.setBounds(36, 58, 343, 26);
		frame.getContentPane().add(comboBox);
		
		comboBox.addItem("");
		
		JLabel lblClickToShow = new JLabel("Click to show Patterns and select Pattern to remove:"); //show patterns
		lblClickToShow.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblClickToShow.setBounds(36, 27, 343, 26);
		frame.getContentPane().add(lblClickToShow);
		
		JButton btnRemove = new JButton("REMOVE");
		btnRemove.setBackground(Color.ORANGE);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//remove pattern
				String value = comboBox.getSelectedItem().toString();
				if(value.equals("")) { //if no pattern selected, call message function
					String m = "No Pattern selected to remove!";
					String n = "removePattern";
					frame.setVisible(false);
					message(m,n);
				}
				else {
					languages.get(0).remove(value);
					frame.setVisible(false);
					PatternMenu();
				}
			}
		});
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRemove.setBounds(435, 338, 111, 38);
		frame.getContentPane().add(btnRemove);
		
		for(int i = 0; i < languages.get(0).getComponentsList().size(); i++) {
            comboBox.addItem("" + languages.get(0).getComponentsList().get(i).getName());
        }    
		
		frame.setVisible(true);
	}
	
	//first window for editing a pattern
	public void editPattern() {
		frame = new JFrame();
		frame.setBounds(100, 100, 588, 442);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.setBounds(36, 58, 383, 26);
		frame.getContentPane().add(comboBox);
		
		comboBox.addItem("");
		
		JLabel lblClickToShow = new JLabel("Click to show Patterns and select Pattern to edit contents:"); //show patterns
		lblClickToShow.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblClickToShow.setBounds(36, 27, 383, 26);
		frame.getContentPane().add(lblClickToShow);
		
		JButton btnRemove = new JButton("EDIT");
		btnRemove.setBackground(Color.ORANGE);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//edit
				String value = comboBox.getSelectedItem().toString();
				if(value.equals("")) { //if no pattern selected to edit call message function
					String m = "No Pattern selected to edit!";
					String n = "removePattern";
					frame.setVisible(false);
					message(m,n);
				}
				else {
					for(int i = 0; i< languages.get(0).getComponentsList().size(); i++) {
						if(languages.get(0).getComponentsList().get(i).getName().equals(value)) {
							myPattern = (Pattern)languages.get(0).getComponentsList().get(i);
						}
					} 
					frame.setVisible(false);
					editPart();
				}
			}
		});
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRemove.setBounds(451, 342, 111, 38);
		frame.getContentPane().add(btnRemove);
		
		JButton btnNewButton_1 = new JButton("CANCEL");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				PatternMenu();
			}
		});
		btnNewButton_1.setBackground(Color.RED);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.setBounds(330, 342, 111, 38);
		frame.getContentPane().add(btnNewButton_1);
		
		
		for(int i = 0; i < languages.get(0).getComponentsList().size(); i++) {
            comboBox.addItem("" + languages.get(0).getComponentsList().get(i).getName());
        }    
		
		frame.setVisible(true);
	}
	
	//second window for editing a pattern
	public void editPart() {
		frame = new JFrame();
		frame.setBounds(100, 100, 588, 442);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.setBounds(36, 58, 429, 26);
		frame.getContentPane().add(comboBox);
		
		comboBox.addItem("");
		 
		
		JLabel lblClickToShow = new JLabel("Click to show PatternParts and select PatternPart to edit contents:"); //show patternparts for the pattern
		lblClickToShow.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblClickToShow.setBounds(36, 27, 429, 26);
		frame.getContentPane().add(lblClickToShow);
		
		JLabel lblSelectPatternTo = new JLabel((String) null); //select patternpart to edit
		lblSelectPatternTo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSelectPatternTo.setBounds(276, 27, 253, 22);
		frame.getContentPane().add(lblSelectPatternTo);
		
		JButton btnRemove = new JButton("EDIT");
		btnRemove.setBackground(Color.ORANGE);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//edit
				String value = comboBox.getSelectedItem().toString();
				if(value.equals("")) {
					String m = "No PatternPart selected to edit!";
					String n = "removePattern";
					frame.setVisible(false);
					message(m,n);
				}
				else {
					for(int i = 0; i< myPattern.getComponentsList().size(); i++) {
						if(myPattern.getComponentsList().get(i).getName().equals(value)) {
							myPart = (PatternPart) myPattern.getComponentsList().get(i);
						}
					} 
					frame.setVisible(false);
					editPart2();
				}
			}
		});
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRemove.setBounds(451, 343, 111, 38);
		frame.getContentPane().add(btnRemove);
		
		JButton btnNewButton_1 = new JButton("CANCEL");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				PatternMenu();
			}
		});
		btnNewButton_1.setBackground(Color.RED);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.setBounds(332, 343, 111, 38);
		frame.getContentPane().add(btnNewButton_1);   
		
		for(int i = 0; i < myPattern.getComponentsList().size(); i++) {
            comboBox.addItem("" + myPattern.getComponentsList().get(i).getName());
        }
		
		frame.setVisible(true);
	}
	
	//window for edit selected patternpart
	public void editPart2() {
		frame = new JFrame();
		frame.setBounds(100, 100, 612, 526);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblGetname = new JLabel(myPart.getName());
		lblGetname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGetname.setBounds(10, 32, 576, 19);
		frame.getContentPane().add(lblGetname);
		
		JTextArea txtrCc = new JTextArea();
		txtrCc.setFont(new Font("Tahoma", Font.ITALIC, 15));
		txtrCc.setText(myPart.getContents()); //show contents
		txtrCc.setBounds(10, 107, 576, 288);
		frame.getContentPane().add(txtrCc);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String newContent = txtrCc.getText();
				if(myPart.getName().equals("Name")) {
					myPart.setName(newContent);
					myPattern.setName(newContent);
				}
				else {
					myPart.setContents(newContent);
				}
				frame.setVisible(false);
				PatternMenu();
			}
		});
		btnOk.setBackground(Color.GREEN);
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnOk.setBounds(384, 438, 89, 34);
		frame.getContentPane().add(btnOk);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				PatternMenu();
			}
		});
		btnCancel.setBackground(Color.RED);
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancel.setBounds(497, 438, 89, 34);
		frame.getContentPane().add(btnCancel);
		
		JLabel lblYouCanEdit = new JLabel("(You can edit content)");
		lblYouCanEdit.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lblYouCanEdit.setBounds(10, 62, 149, 14);
		frame.getContentPane().add(lblYouCanEdit);
		frame.setVisible(true);
	}
	
	//window for messages
	public void message(String m, String n) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel(m);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(38, 47, 354, 129);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//my code
				if(n.contentEquals("menu"))
				{
					frame.setVisible(false);
					PatternMenu();
				}
				else if(n.contentEquals("PatternTemplate")) 
				{
					frame.setVisible(false);
					microPattern();
				}
				else if(n.equals("removePattern")) 
				{
					frame.setVisible(false);
					PatternMenu();
				}
				else if(n.equals("savePatternLanguage")) 
				{
					frame.setVisible(false);
					startMenu();
				}
			}
		});
		btnNewButton.setBounds(286, 193, 116, 37);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblMessage = new JLabel("Message!!");
		lblMessage.setForeground(Color.RED);
		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMessage.setBounds(38, 11, 101, 25);
		frame.getContentPane().add(lblMessage);
		frame.setVisible(true);
	}
}
