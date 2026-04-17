import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

public class BreakdownURL {
    public Map<String, String> parseURL(String url) {
        Map<String, String> result = new LinkedHashMap();
        
        try{
            URI uri = new URI(url);
            
            if(uri.getScheme()!= null){
                result.put("protocol", uri.getScheme());
            }
            
            if (uri.getHost()!= null){
                result.put("domain", uri.getHost());
            }
            
            if (uri.getPort() > 0){
                result.put("port", String.valueOf(uri.getPort()));
            }
            
            if(uri.getPath()!= null && !uri.getPath().isEmpty()){
                result.put("path", uri.getPath());
            }

            else if (result.containsKey("protocol") && result.containsKey("domain")) {
                result.put("path", "/");
            }
            
            if (uri.getQuery()!= null){
                result.put("query", uri.getQuery());
            }
            
            
        }catch(Exception e){
           return null;
        }
        return result;
    }
}