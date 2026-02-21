import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigProtector {
    public String hideSensitiveData(String configFile, List<String> sensitiveKeys) {
        String result = "";
        if (sensitiveKeys.isEmpty()){
            return configFile;
        }
        String pattern = "^([^=]+)=(.*?)$";
        Matcher matcher = Pattern.compile(pattern, Pattern.MULTILINE).matcher(configFile);
        while (matcher.find()){
            result += matcher.group(1)+ "=";
            if (sensitiveKeys.contains(matcher.group(1))){
                for (int i = 0; i < matcher.group(2).length(); i++){
                    result += "*";
                }
            } else {
                result += matcher.group(2);
            }
            result += "\n";
        }
        return result;
    }

}