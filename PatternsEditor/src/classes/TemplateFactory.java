package classes;
import java.util.HashMap;
import java.util.ArrayList;

public class TemplateFactory {
	
	private HashMap<String,Pattern> templatesList;
	
	public TemplateFactory() {
		//create HashMap for templates
		templatesList = new HashMap<String,Pattern>(); 
		
		//define PatternParts
		PatternPart name = new PatternPart("Name","");
		PatternPart template = new PatternPart("Template","");
		PatternPart problem = new PatternPart("Problem","");
		PatternPart solution = new PatternPart("Solution","");
		PatternPart context = new PatternPart("Context","");
		PatternPart forces = new PatternPart("Forces","");
		PatternPart benefits = new PatternPart("Benefits","");
		PatternPart consequences = new PatternPart("Consequences","");
		PatternPart patternClassification = new PatternPart("Pattern Classification","");
		PatternPart intent = new PatternPart("Intent","");
		PatternPart alsoKnownAs = new PatternPart("Also known As","");
		PatternPart motivation = new PatternPart("Motivation","");
		PatternPart applicability = new PatternPart("Applicability","");
		PatternPart structure = new PatternPart("Structure","");
		PatternPart participants = new PatternPart("Participants","");
		PatternPart collaborations = new PatternPart("Collaborations","");
		PatternPart implementation = new PatternPart("Implementation","");
		PatternPart sampleCode = new PatternPart("Sample Code","");
		PatternPart knownUses = new PatternPart("Known Uses","");
		PatternPart relatedPatterns = new PatternPart("Related Patterns","");
		PatternPart example = new PatternPart("Example","");
		PatternPart dynamics = new PatternPart("Dynamics","");
		PatternPart exampleResolved = new PatternPart("Example Resolved","");
		PatternPart variants = new PatternPart("Variants","");
		
		//create the Patterns/templates with their PatternParts
		//micro pattern
		Pattern template0 = new Pattern("template0", new ArrayList<PatternComponent>());
		template0.add(name);
		template0.add(template);
		template0.add(problem);
		template0.add(solution);
		//inductive mini pattern
		Pattern template1 = new Pattern("template1", new ArrayList<PatternComponent>());
		template1.add(name);
		template1.add(template);
		template1.add(context);
		template1.add(forces);
		template1.add(solution);
		//deductive mini pattern
		Pattern template2 = new Pattern("template2", new ArrayList<PatternComponent>());
		template2.add(name);
		template2.add(template);
		template2.add(problem);
		template2.add(solution);
		template2.add(benefits);
		template2.add(consequences);
		//gang of four pattern
		Pattern template3 = new Pattern("template3", new ArrayList<PatternComponent>());
		template3.add(name);
		template3.add(template);
		template3.add(patternClassification);
		template3.add(intent);
		template3.add(alsoKnownAs);
		template3.add(motivation);
		template3.add(applicability);
		template3.add(structure);
		template3.add(participants);
		template3.add(collaborations);
		template3.add(consequences);
		template3.add(implementation);
		template3.add(sampleCode);
		template3.add(knownUses);
		template3.add(relatedPatterns);
		//system of patterns pattern
		Pattern template4 = new Pattern("template4", new ArrayList<PatternComponent>());
		template4.add(name);
		template4.add(template);
		template4.add(alsoKnownAs);
		template4.add(example);
		template4.add(context);
		template4.add(problem);
		template4.add(solution);
		template4.add(structure);
		template4.add(dynamics);
		template4.add(implementation);
		template4.add(exampleResolved);
		template4.add(variants);
		template4.add(knownUses);
		template4.add(consequences);
		
		//put the templates in HashMap
		templatesList.put("Micro-Pattern Template", template0);
		templatesList.put("Inductive Mini-Pattern", template1);
		templatesList.put("Deductive Mini-Pattern", template2);
		templatesList.put("Gang-of-Four Pattern", template3);
		templatesList.put("System of Patterns Template", template4);
	}
	
	public Pattern createTemplate(String templateName){
		
		Pattern template = templatesList.get(templateName);
		return template.clone();
	}
	
}