package classes;

import java.util.ArrayList;
import java.io.PrintWriter;

public class Decorator extends PatternComposite{
	
	private String beginTag;
	private String endTag;
	
	public Decorator(PatternComponent p, String beginTag, String endTag){
		super(p.getName(), new ArrayList<PatternComponent>());
		this.beginTag = beginTag;
		this.endTag = endTag;
		p.setName(beginTag+p.getName()+endTag);
		getComponentsList().add(p);
	}
	
	public void saveName(PrintWriter outputStream) {
		getComponentsList().get(0).saveName(outputStream);
	}
	
	public void saveContents(PrintWriter outputStream) {
		getComponentsList().get(0).saveContents(outputStream);
	}
	
	public void decorateComponents(DecoratorAbstractFactory decoratorFactory) {
		//only define here
	}
}
