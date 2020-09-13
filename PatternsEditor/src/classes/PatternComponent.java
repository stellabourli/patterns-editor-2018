package classes;

import java.io.FileOutputStream;
import java.io.PrintWriter;

public class PatternComponent {

	private String name;
	
	public PatternComponent(String name) {
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getContents(){
		//only define here
		return "";
	}
	
	public void setContents(String contents){
		//only define here
	}
	
	public void setContents(String contents, int index) {
		//only define here
	}
	
	public void saveName(PrintWriter outputStream){
		outputStream.println(getName());
	}
	
	public void saveContents(PrintWriter outputstream){
		//only define here
	}
	
	public void add(PatternComponent patternComponent){
		//only define here
	}
	
	public void remove(String patternComponentTitle){
		//only define here
	}
}
