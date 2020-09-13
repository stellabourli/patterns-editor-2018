package junitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import classes.Decorator;
import classes.LatexDecoratorFactory;
import classes.Pattern;
import classes.PatternComponent;
import classes.PatternLanguage;
import classes.PatternPart;
import classes.TemplateFactory;

class SaveLoadTests {
	
	@Test //T12, testing saving PatternLanguage in text File and load it  
	void saveLoadTextFileTest() {
		//create PatternLanguage
		PatternLanguage TextLanguage = new PatternLanguage("TextLanguage", new ArrayList<PatternComponent>());
		//add a micro Pattern to it
		TemplateFactory micro = new TemplateFactory();
		Pattern firstPattern = micro.createTemplate("Micro-Pattern Template");
		for(int i = 0; i < firstPattern.getComponentsList().size(); i++) {
			if (firstPattern.getComponentsList().get(i).getName().equals("Name")) {
				firstPattern.getComponentsList().get(i).setContents("A");
			}
			else if(firstPattern.getComponentsList().get(i).getName().equals("Template")) {
				firstPattern.getComponentsList().get(i).setContents("b");
			}
			else if(firstPattern.getComponentsList().get(i).getName().equals("Problem")) {
				firstPattern.getComponentsList().get(i).setContents("c");
			}
			else if(firstPattern.getComponentsList().get(i).getName().equals("Solution")) {
				firstPattern.getComponentsList().get(i).setContents("d");
			}
		}
		
		firstPattern.setName("A");
		TextLanguage.add(firstPattern);
		
		//add a mini Pattern to it
		TemplateFactory mini = new TemplateFactory();
		Pattern secondPattern = mini.createTemplate("Inductive Mini-Pattern");
		for(int i = 0; i < secondPattern.getComponentsList().size(); i++) {
			if (secondPattern.getComponentsList().get(i).getName().equals("Name")) {
				secondPattern.getComponentsList().get(i).setContents("B");
			}
			else if(secondPattern.getComponentsList().get(i).getName().equals("Template")) {
				secondPattern.getComponentsList().get(i).setContents("g");
			}
			else if(secondPattern.getComponentsList().get(i).getName().equals("Context")) {
				secondPattern.getComponentsList().get(i).setContents("h");
			}
			else if(secondPattern.getComponentsList().get(i).getName().equals("Forces")) {
				secondPattern.getComponentsList().get(i).setContents("f");
			}
			else if(secondPattern.getComponentsList().get(i).getName().equals("Solution")) {
				secondPattern.getComponentsList().get(i).setContents("u");
			}
		}
		secondPattern.setName("B");
		TextLanguage.add(secondPattern);
		
		//save to file
		FileOutputStream outputStream = null;
		try
		{
			outputStream = new FileOutputStream(TextLanguage.getName()+".txt");
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error opening the file "+TextLanguage.getName()+".txt."); //open file with PatternLanguage's name for writing
			System.exit(0);
		}
		PrintWriter outputWriter = new PrintWriter(outputStream);
		TextLanguage.saveName(outputWriter); //call saveName method for the PatternLanguage
		TextLanguage.saveContents(outputWriter); //call saveContents method for the PatternLanguage
		outputWriter.close( ); //close file
		
		//load from file
		PatternLanguage myPatternLanguage = new PatternLanguage("", new ArrayList<PatternComponent>()); //create a new PatternLanguage
		Scanner inputStream = null;
		String fileName =  "TextLanguage";
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
		myPatternLanguage.setName(PatternLanguageName); //the new PatternLanguage has the loaded Name
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
					Patterns.get(0).setName(""+loadName.charAt(1)); //the Pattern has the loaded Name
					loadPart.setName("Name"); //the PatternPart with the Name "Name" has also this name
					loadPart.setContents(""+loadName.charAt(1)); //So, the PatternPart with Name "Name" has as contents the loaded Name
					Parts.add(loadPart); //add it to PatternPart's ArrayList
					store = 1; //change flag's value
				}
				else //if it's not the first line with Name as content
				{
					for(PatternComponent p : Parts) { //read now a New Pattern so add all the PatternPart's we had to the previous Pattern
						Patterns.get(0).add(p);
					}
					Parts.clear(); //delete the previous PatternParts, so Parts ArrayList is empty
					myPatternLanguage.add(Patterns.get(0)); //add the previous Pattern to PatternLanguage
					Patterns.remove(0); //delete it
					Pattern loadPattern = new Pattern("", new ArrayList<PatternComponent>()); //create new Pattern
					Patterns.add(loadPattern); //add it to Pattern's ArrayList
					PatternPart loadPart = new PatternPart("",""); //create new PatternPart
					String loadName = inputStream.nextLine( ); //next line has the name value
					Patterns.get(0).setName(""+loadName.charAt(1));  //the Pattern has the loaded Name
					loadPart.setName("Name"); //the PatternPart with the Name "Name" has also this name
					loadPart.setContents(""+loadName.charAt(1)); //So, the PatternPart with Name "Name" has as contents the loaded Name
					Parts.add(loadPart); //add it to PatternPart's ArrayList
				}
				
			}
			else if(mySet.contains(line)) //if line has as content a PatternPart's Name
			{
				PatternPart loadPart = new PatternPart("",""); //create new PatternPart
				loadPart.setName(line); //PatternPart has the loaded Name
				String loadContent = inputStream.nextLine( ); //next line has the contents for the PatternPart
				loadPart.setContents(""+loadContent.charAt(1)); //PatternPart has the loaded contents
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
		myPatternLanguage.add(Patterns.get(0)); //add the last Pattern to PatternLanguage
		Patterns.remove(0); //delete it
		inputStream.close(); //close file
		
		assertEquals(TextLanguage.getName(), myPatternLanguage.getName()); //check if PatternLanguages have same name
		
		for(int i=0;i<TextLanguage.getComponentsList().size();i++)
		{
			assertEquals(TextLanguage.getComponentsList().get(i).getName(), myPatternLanguage.getComponentsList().get(i).getName()); //check if patterns have same name
			
			Pattern p  = (Pattern)TextLanguage.getComponentsList().get(i); 
			Pattern p2 = (Pattern)myPatternLanguage.getComponentsList().get(i); 
			int size = p.getComponentsList().size();
			for(int j=0;j<size;j++)
			{
				assertEquals(p.getComponentsList().get(j).getName(), p2.getComponentsList().get(j).getName()); //check if parts have same name
				assertEquals(p.getComponentsList().get(j).getContents(), p2.getComponentsList().get(j).getContents()); //check if parts have same contents
			}
		}
	}

	@Test //T13, testing decorateComponents and createPatternDecorator for Pattern object
	void decoratePatternTest() {
		Pattern myPattern = new Pattern("Name", new ArrayList<PatternComponent>());
		LatexDecoratorFactory dFactory = new LatexDecoratorFactory();
		myPattern.decorateComponents(dFactory);
		Decorator decoratedPattern = (Decorator)(dFactory.createPatternDecorator(myPattern));
		assertEquals("\\section{Name}", decoratedPattern.getComponentsList().get(0).getName());
	}
	
	@Test //T14, testing decorateComponents and createLanguageDecorator for PatternLanguage object
	void decoratePatternLanguageTest() {
		PatternLanguage myPatternLanguage = new PatternLanguage("Name", new ArrayList<PatternComponent>());
		LatexDecoratorFactory dFactory = new LatexDecoratorFactory();
		myPatternLanguage.decorateComponents(dFactory);
		Decorator decoratedPatternLanguage = (Decorator)(dFactory.createLanguageDecorator(myPatternLanguage));
		assertEquals("\\title{Name}\n\\maketitle", decoratedPatternLanguage.getComponentsList().get(0).getName());
	}
}
