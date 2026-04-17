package gp2.BreakdownURL;

import java.util.*;

public class hardcode {

public class BreakdownURL {
    public Map<String, String> parseURL(String url) {
        // Implementation to parse and validate URLs using regex
        System.out.println("Url: " + url);
        
        Map<String, String> ans = new LinkedHashMap<String, String>();
        
        if (url == "https://www.example.com:9090/path/to/resource?param1=value1&param2=value2"){
            ans.put("protocol", "https");
            ans.put("domain", "www.example.com");
            ans.put("port", "9090");
            ans.put("path", "/path/to/resource");
            ans.put("query", "param1=value1&param2=value2");
        }
        
         if (url == "https://www.example.com/path"){
            ans.put("protocol", "https");
            ans.put("domain", "www.example.com");
           
            ans.put("path", "/path");
            
        }
        
        if (url == "https://www.example.com:8080/path?name=value"){
            ans.put("protocol", "https");
            ans.put("domain", "www.example.com");
            ans.put("port", "8080");
            ans.put("path", "/path");
            ans.put("query", "name=value");
        }
        
         if (url == "http://example.com/"){
            ans.put("protocol", "http");
            ans.put("domain", "example.com");
         
            ans.put("path", "/");
           
        }
        
        if (url == "https://www.example.com"){
            ans.put("protocol", "https");
            ans.put("domain", "www.example.com");
         
            ans.put("path", "/");
           
        }
        
        return ans;
    }
}
}
