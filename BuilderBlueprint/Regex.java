import java.util.List;
public class Regex {
    private StringBuilder pattern;

    public Regex(List<String> components) {
        this.pattern = new StringBuilder();
        for (String comp : components) {
            pattern.append(comp);
        }
    }
    public Regex() {
        this.pattern = new StringBuilder();
    }

    public String getPattern() {
        return pattern.toString();
    }
}
