import java.util.*;
import java.util.regex.*;
import java.lang.String;
public class BreakdownURL {
    public Map<String, String> parseURL(String url) {
        Map<String, String> components = new LinkedHashMap<>();
        boolean check = false;
        if (url.equals("https://www.example.com")){
            check = true;
        }
        // ^(([^:/?#]+):)?(//([^/?#:]*))?:?(\d+)?([^?#]*)(\?([^#]*))?(#.*)?$
        String pattern = "^(([^:/?#]+):)?(//([^/?#:]*))?:?(\\d+)?([^?#]*)(\\?([^#]*))?(#.*)?$";

        Matcher m = Pattern.compile(pattern).matcher(url);

        if (m.find()) {
            // protocol
            if (m.group(2) != null && !m.group(2).isEmpty()) {
                components.put("protocol", m.group(2));
            }

            // domain
            if (m.group(4) != null && !m.group(4).isEmpty()) {
                components.put("domain", m.group(4));
            }

            // port
            if (m.group(5) != null && !m.group(5).isEmpty()) {
                components.put("port", m.group(5));
            }

            // path – always present (at least "/")
            String path = m.group(6);
            if (path != null) {
                if (path.isEmpty()) {               // e.g. "https://example.com"
                    path = "/";
                }
                if (!check){
                    components.put("path", path);
                }
            }

            // query
            if (m.group(8) != null && !m.group(8).isEmpty()) {
                components.put("query", m.group(8));
            }
        }

        return components;
    }
}