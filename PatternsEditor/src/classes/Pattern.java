package classes;
import java.util.ArrayList;

public class Pattern extends PatternComposite {
	
	public Pattern(String name, ArrayList<PatternComponent> componentsList){
		super(name,componentsList);
	}
	
	
	public Pattern clone(){
		Pattern newPattern = new Pattern("",new ArrayList<PatternComponent>());
		for(PatternComponent c : getComponentsList()) {
			String newName = c.getName();
			String newContents = c.getContents();
			newPattern.add(new PatternPart(newName, newContents));
		}
		return newPattern;
	}
	
	public void decorateComponents(DecoratorAbstractFactory dFactory){
		for(PatternComponent c : getComponentsList()) {
			dFactory.createPartDecorator((PatternPart)c);
		}
	}
	
}
