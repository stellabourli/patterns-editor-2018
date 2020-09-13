package classes;

public interface DecoratorAbstractFactory {
	public PatternComponent createLanguageDecorator(PatternLanguage language);
	public PatternComponent createPatternDecorator(Pattern pattern);
	public PatternComponent createPartDecorator(PatternPart part);
}
