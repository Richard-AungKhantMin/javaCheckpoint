import java.util.ArrayList;
import java.util.List;

public class ConcreteRegexBuilder implements RegexBuilder {
    List<String> component;
    public ConcreteRegexBuilder() {
        component = new ArrayList<>();
    }
    @Override
    public Regex getResult() {
        return new Regex(component);
    }
    @Override

    public void buildLiteral(String literal){
        String escaped = literal.replace("\\", "\\\\")
                .replace(".", "\\.")
                .replace("*", "\\*")
                .replace("+", "\\+")
                .replace("?", "\\?")
                .replace("^", "\\^")
                .replace("$", "\\$")
                .replace("[", "\\[")
                .replace("]", "\\]")
                .replace("{", "\\{")
                .replace("}", "\\}")
                .replace("(", "\\(")
                .replace(")", "\\)")
                .replace("|", "\\|");
        component.add(escaped);
    }
    @Override
    public void buildAnyCharacter(){
        component.add(".");
    }
    @Override
    public void buildDigit(){
        component.add("\\d");
    }
    @Override
    public void buildWhitespace(){
        component.add("\\s");
    }
    @Override
    public void buildWordCharacter() {
        component.add("\\w");
    }

}
