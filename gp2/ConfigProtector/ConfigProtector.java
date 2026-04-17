package gp2.ConfigProtector;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ConfigProtector {
    public String hideSensitiveData(String configFile, List<String> sensitiveKeys) {
        String protectedConfig = configFile;

        for (String key : sensitiveKeys) {
            Pattern pattern = Pattern.compile("(?<=" + Pattern.quote(key) + "=).*");
            Matcher matcher = pattern.matcher(protectedConfig);

            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                String value = matcher.group();                   
                String masked = "*".repeat(value.length());       
                matcher.appendReplacement(sb, masked);          
            }
            matcher.appendTail(sb);                               
            protectedConfig = sb.toString();                       
        }
        return protectedConfig;
    }
}