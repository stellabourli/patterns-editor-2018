package classes;
import java.util.ArrayList;

public class PatternLanguage extends PatternComposite {
	
	public PatternLanguage(String name, ArrayList<PatternComponent> componentsList) {
		super(name, componentsList);
	}
	
	
	public void decorateComponents(LatexDecoratorFactory dFactory){
		for(PatternComponent c : getComponentsList()) {
			((Pattern)c).decorateComponents(dFactory);
			dFactory.createPatternDecorator((Pattern)c);
		}
	}
}

