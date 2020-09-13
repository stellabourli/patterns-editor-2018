package classes;
import java.util.ArrayList;

public class LatexDecoratorFactory implements DecoratorAbstractFactory{
	
	public PatternComponent createLanguageDecorator(PatternLanguage language) {
		String beginTag = "\\title{";
		String endTag = "}\n\\maketitle";
		Decorator decoratedLanguage = new Decorator(language, beginTag, endTag);
		return decoratedLanguage;
	}
	public PatternComponent createPatternDecorator(Pattern pattern) {
		String beginTag = "\\section{";
		String endTag = "}";
		Decorator decoratedPattern = new Decorator(pattern, beginTag, endTag);
		return decoratedPattern;
	}
	public PatternComponent createPartDecorator(PatternPart part) {
		String beginTag = "\t\\subsection{";
		String endTag = "}";
		Decorator decoratedPart = new Decorator(part, beginTag, endTag);
		return decoratedPart;
	}

}
