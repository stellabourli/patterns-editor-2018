package junitTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import classes.Pattern;
import classes.PatternComponent;
import classes.PatternPart;
import classes.TemplateFactory;
import classes.PatternLanguage;


class PatternTest {

	@Test //T01, testing getName and setName in PatternLanguage object 
	void testPatternLanguage() {
		PatternLanguage newPatternLanguage = new PatternLanguage("", new ArrayList<PatternComponent>());
		newPatternLanguage.setName("NewName");
		assertEquals("NewName", newPatternLanguage.getName());
	}
	
	@Test //T02, testing createTemplate and clone methods for Micro-Pattern Template
	void testClone() {
		Pattern newPattern = new Pattern("", new ArrayList<PatternComponent>());
		TemplateFactory temp = new TemplateFactory();
		newPattern = temp.createTemplate("Micro-Pattern Template");
		
		assertEquals(4, newPattern.getComponentsList().size());
		
		assertEquals("Name", newPattern.getComponentsList().get(0).getName());
		assertEquals("Template", newPattern.getComponentsList().get(1).getName());
		assertEquals("Problem", newPattern.getComponentsList().get(2).getName());
		assertEquals("Solution", newPattern.getComponentsList().get(3).getName());
	}
	
	@Test //T03, testing createTemplate and clone methods for Inductive Mini-Pattern
	void testClone2() {
		Pattern newPattern = new Pattern("", new ArrayList<PatternComponent>());
		TemplateFactory temp = new TemplateFactory();
		newPattern = temp.createTemplate("Inductive Mini-Pattern");
		
		assertEquals(5, newPattern.getComponentsList().size());
		
		assertEquals("Name", newPattern.getComponentsList().get(0).getName());
		assertEquals("Template", newPattern.getComponentsList().get(1).getName());
		assertEquals("Context", newPattern.getComponentsList().get(2).getName());
		assertEquals("Forces", newPattern.getComponentsList().get(3).getName());
		assertEquals("Solution", newPattern.getComponentsList().get(4).getName());
	}
	
	@Test //T04, testing createTemplate and clone methods for Deductive Mini-Pattern
	void testClone3() {
		Pattern newPattern = new Pattern("", new ArrayList<PatternComponent>());
		TemplateFactory temp = new TemplateFactory();
		newPattern = temp.createTemplate("Deductive Mini-Pattern");
		
		assertEquals(6, newPattern.getComponentsList().size());
		
		assertEquals("Name", newPattern.getComponentsList().get(0).getName());
		assertEquals("Template", newPattern.getComponentsList().get(1).getName());
		assertEquals("Problem", newPattern.getComponentsList().get(2).getName());
		assertEquals("Solution", newPattern.getComponentsList().get(3).getName());
		assertEquals("Benefits", newPattern.getComponentsList().get(4).getName());
		assertEquals("Consequences", newPattern.getComponentsList().get(5).getName());
	}
	
	@Test //T05, testing createTemplate and clone methods for Gang-of-Four Pattern
	void testClone4() {
		Pattern newPattern = new Pattern("", new ArrayList<PatternComponent>());
		TemplateFactory temp = new TemplateFactory();
		newPattern = temp.createTemplate("Gang-of-Four Pattern");
		
		assertEquals(15, newPattern.getComponentsList().size());
		
		assertEquals("Name", newPattern.getComponentsList().get(0).getName());
		assertEquals("Template", newPattern.getComponentsList().get(1).getName());
		assertEquals("Pattern Classification", newPattern.getComponentsList().get(2).getName());
		assertEquals("Intent", newPattern.getComponentsList().get(3).getName());
		assertEquals("Also known As", newPattern.getComponentsList().get(4).getName());
		assertEquals("Motivation", newPattern.getComponentsList().get(5).getName());
		assertEquals("Applicability", newPattern.getComponentsList().get(6).getName());
		assertEquals("Structure", newPattern.getComponentsList().get(7).getName());
		assertEquals("Participants", newPattern.getComponentsList().get(8).getName());
		assertEquals("Collaborations", newPattern.getComponentsList().get(9).getName());
		assertEquals("Consequences", newPattern.getComponentsList().get(10).getName());
		assertEquals("Implementation", newPattern.getComponentsList().get(11).getName());
		assertEquals("Sample Code", newPattern.getComponentsList().get(12).getName());
		assertEquals("Known Uses", newPattern.getComponentsList().get(13).getName());
		assertEquals("Related Patterns", newPattern.getComponentsList().get(14).getName());
	}
	
	@Test //T06, testing createTemplate and clone methods for System of Patterns Template
	void testClone5() {
		Pattern newPattern = new Pattern("", new ArrayList<PatternComponent>());
		TemplateFactory temp = new TemplateFactory();
		newPattern = temp.createTemplate("System of Patterns Template");
		
		assertEquals(14, newPattern.getComponentsList().size());
		
		assertEquals("Name", newPattern.getComponentsList().get(0).getName());
		assertEquals("Template", newPattern.getComponentsList().get(1).getName());
		assertEquals("Also known As", newPattern.getComponentsList().get(2).getName());
		assertEquals("Example", newPattern.getComponentsList().get(3).getName());
		assertEquals("Context", newPattern.getComponentsList().get(4).getName());
		assertEquals("Problem", newPattern.getComponentsList().get(5).getName());
		assertEquals("Solution", newPattern.getComponentsList().get(6).getName());
		assertEquals("Structure", newPattern.getComponentsList().get(7).getName());
		assertEquals("Dynamics", newPattern.getComponentsList().get(8).getName());
		assertEquals("Implementation", newPattern.getComponentsList().get(9).getName());
		assertEquals("Example Resolved", newPattern.getComponentsList().get(10).getName());
		assertEquals("Variants", newPattern.getComponentsList().get(11).getName());
		assertEquals("Known Uses", newPattern.getComponentsList().get(12).getName());
		assertEquals("Consequences", newPattern.getComponentsList().get(13).getName());
	}
	
	@Test //T07, testing list, add method in Pattern object
	void testList() {
		PatternLanguage newPatternLanguage = new PatternLanguage("", new ArrayList<PatternComponent>());
		Pattern newPattern = new Pattern("name", new ArrayList<PatternComponent>());
		newPatternLanguage.add(newPattern);
		assertEquals("name", newPatternLanguage.getComponentsList().get(0).getName());
	}
	
	@Test //T08, testing list, add method in Pattern object
	void testList1() {
		Pattern newPattern = new Pattern("", new ArrayList<PatternComponent>());
		PatternPart newPatternPart = new PatternPart("name", "contents");
		newPattern.add(newPatternPart);
		assertEquals("name", newPattern.getComponentsList().get(0).getName());
		assertEquals("contents", newPattern.getComponentsList().get(0).getContents());
	}
	
	@Test //T09, testing list, remove method in PatternLanguage object
	void testList2() {
		PatternLanguage newPatternLanguage = new PatternLanguage("", new ArrayList<PatternComponent>());
		Pattern newPattern = new Pattern("name", new ArrayList<PatternComponent>());
		newPatternLanguage.add(newPattern);
		assertEquals(1, newPatternLanguage.getComponentsList().size());
		newPatternLanguage.remove("name");
		assertEquals(0, newPatternLanguage.getComponentsList().size());
	}
	
	@Test //T10, testing list, remove method Pattern object
	void testList3() {
		Pattern newPattern = new Pattern("", new ArrayList<PatternComponent>());
		PatternPart newPatternPart = new PatternPart("name", "contents");
		newPattern.add(newPatternPart);
		assertEquals(1, newPattern.getComponentsList().size());
		newPattern.remove("name");
		assertEquals(0, newPattern.getComponentsList().size());
	}
	
	@Test //T11, testing getContents and setContents methods in PatternPart object
	void testContent() {
		PatternPart newPatternPart = new PatternPart("name", "contents");
		assertEquals("contents", newPatternPart.getContents());
		newPatternPart.setContents("newContents");
		assertEquals("newContents", newPatternPart.getContents());
	}
}




