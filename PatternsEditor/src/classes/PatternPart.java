package classes;

import java.io.FileOutputStream;
import java.io.PrintWriter;

public class PatternPart extends PatternComponent {

	private String contents;
	
	public PatternPart(String name, String contents) {
		super(name);
		this.contents = contents;
	}
	
	public String getContents() {
		return contents;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public void saveContents(PrintWriter outputStream) {
		outputStream.println("\t"+getContents());
	}
}
