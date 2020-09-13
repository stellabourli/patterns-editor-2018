package junitTests;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import classes.Decorator;
import classes.LatexDecoratorFactory;
import classes.Pattern;
import classes.PatternComponent;
import classes.PatternLanguage;
import classes.PatternPart;
import classes.TemplateFactory;

class SaveLoadLatexTest {

	@Test //T15, testing saving PatternLanguage in LaTeX File and load it  
	void saveLoadLatexFileTest(){
		//create new PatternLanguage
		PatternLanguage LatexLanguage = new PatternLanguage("LatexLanguage", new ArrayList<PatternComponent>());
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
		LatexLanguage.add(firstPattern);
				
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
		LatexLanguage.add(secondPattern);
		
		//save PatternLanguage in a LaTeX file
		String fileName = LatexLanguage.getName();
		LatexDecoratorFactory dFactory = new LatexDecoratorFactory();
		LatexLanguage.decorateComponents(dFactory); //decorate the PatternLanguage
		Decorator decoratedLanguage = (Decorator)(dFactory.createLanguageDecorator(LatexLanguage));
		
		FileOutputStream outputStream = null;
		try
		{
			outputStream = new FileOutputStream(fileName+".tex"); //open file with PatternLanguage's Name for writing
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error opening the file "+fileName+".tex.");
			System.exit(0);
		}
		PrintWriter outputWriter = new PrintWriter(outputStream);
		outputWriter.write("\\documentclass{article}\n"); //write start labels for LaTeX file
		outputWriter.write("\\begin{document}\n");
		decoratedLanguage.saveName(outputWriter); //call saveName method for the decorated PatternLanguage
		decoratedLanguage.saveContents(outputWriter); //call saveContents method for the decorated PatternLanguage
		outputWriter.write("\\end{document}"); //write end label for LaTeX file
		outputWriter.close( ); //close file
		
		//load PatternLanguage from LaTeX file
		Scanner inputStream = null;
		String fileName2 =  "LatexLanguage";
		try
		{
			inputStream = new Scanner(new FileInputStream(fileName2+".tex")); //open file for loading
		}
		catch(FileNotFoundException ex)
		{
			System.out.println("File "+ fileName2+".tex was not found");
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
		PatternLanguage newPatternLanguage = new PatternLanguage("", new ArrayList<PatternComponent>()); //create a new PatternLanguage
		newPatternLanguage.setName(PatternLanguageName); //the new PatternLanguage has the loaded Name
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
					newPatternLanguage.add(Patterns.get(0)); //add the previous Pattern to PatternLanguage
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
				newPatternLanguage.add(Patterns.get(0)); //add the last Pattern to PatternLanguage
				Patterns.remove(0); //delete it
			}
		}
		inputStream.close(); //close file

		//check if PatternLanguages have same name
		assertEquals("LatexLanguage", newPatternLanguage.getName()); 
		
		//check if patterns have same name
		assertEquals("A", newPatternLanguage.getComponentsList().get(0).getName()); 
		assertEquals("B", newPatternLanguage.getComponentsList().get(1).getName());
		
		Pattern A = (Pattern)newPatternLanguage.getComponentsList().get(0); 
		Pattern B = (Pattern)newPatternLanguage.getComponentsList().get(1); 
		
		//check if parts in first Pattern have same name
		assertEquals("Name", A.getComponentsList().get(0).getName()); 
		assertEquals("Template", A.getComponentsList().get(1).getName()); 
		assertEquals("Problem", A.getComponentsList().get(2).getName());
		assertEquals("Solution", A.getComponentsList().get(3).getName());
		
		//check if parts in second Pattern have same name
		assertEquals("Name", B.getComponentsList().get(0).getName()); 
		assertEquals("Template", B.getComponentsList().get(1).getName()); 
		assertEquals("Context", B.getComponentsList().get(2).getName());
		assertEquals("Forces", B.getComponentsList().get(3).getName());
		assertEquals("Solution", B.getComponentsList().get(4).getName());
		
		//check if parts in first Pattern have same contents
		assertEquals("A", A.getComponentsList().get(0).getContents()); 
		assertEquals("b", A.getComponentsList().get(1).getContents()); 
		assertEquals("c", A.getComponentsList().get(2).getContents());
		assertEquals("d", A.getComponentsList().get(3).getContents());
			
		//check if parts in second Pattern have same contents
		assertEquals("B", B.getComponentsList().get(0).getContents()); 
		assertEquals("g", B.getComponentsList().get(1).getContents()); 
		assertEquals("h", B.getComponentsList().get(2).getContents());
		assertEquals("f", B.getComponentsList().get(3).getContents());
		assertEquals("u", B.getComponentsList().get(4).getContents());
	}
}
