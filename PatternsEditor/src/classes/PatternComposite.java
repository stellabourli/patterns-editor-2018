package classes;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileOutputStream;

public class PatternComposite extends PatternComponent{
	
	private ArrayList<PatternComponent> componentsList;
	
	public PatternComposite(String name, ArrayList<PatternComponent> componentsList) {
		super(name);
		this.componentsList = new ArrayList<PatternComponent>();
	}
	
	public void saveContents(PrintWriter outputStream) {
		for(PatternComponent c : getComponentsList()) {
			c.saveName(outputStream);
			c.saveContents(outputStream);
		}
	}
	
	public void add(PatternComponent patternComponent) {
		componentsList.add(patternComponent);
	}
	
	public void remove(String patternComponentTitle) {
		for(int i = 0; i< componentsList.size(); i++) {
			if(componentsList.get(i).getName().equals(patternComponentTitle)) {
				componentsList.remove(componentsList.get(i));
			}
		}
	}
	
	public void decorateComponents(DecoratorAbstractFactory decoratorFactory) {
		//only define here
	}
	
	public ArrayList<PatternComponent> getComponentsList() {
		return componentsList;
	}
}
