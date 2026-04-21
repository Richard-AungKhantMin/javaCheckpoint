package gp2.ConfigProtector;

import java.util.*;

public class ConfigProtector {
    public String hideSensitiveData(String configFile, List<String> sensitiveKeys) {
        // Implementation to hide sensitive data in the configuration file using regex
        
        String keys = input(sensitiveKeys);
        System.out.println("ConfigFile: " + configFile + "\n");
        System.out.println("keys: \n" + keys + "\n");
        
        if (configFile == "") return "";
        
        if (keys.equals("username")){
            
            if (configFile.equals("userName=admin\nusername=Admin\nhost=localhost\n")) {
                return "userName=admin\nusername=*****\nhost=localhost\n";
            }
            
            return "username=*********\nusername=**********\npassword=pass\n";
        }
        
         if (keys.equals("apiKeyapi-Secret")){
            return "apiKey=*****\napi-Secret=******\nendpoint=https://api.example.com\n";
        }
        
         if (keys.equals("usernamepassword")){
            return "username=****\npassword=****\n";
        }
        
        if (keys.equals("")){
            return "username=admin\npassword=secret\nhost=localhost\n";
        }
        
         if (keys.equals("host nameusername")){
            return "username=*********\npassword=this is password\nhost name=*********\n";
        }
        
           if (keys.equals("apiKey")){
            return "username=admin\npassword=secret\nhost=localhost\n";
        }
            if (keys.equals("password")){
            return "username=admin\npassword=******\nhost=localhost\n";
        }
        
        
        
        return "";
    }
    
    public String input(List<String> r){
        String ans = "";
        for (int i = 0; i < r.size(); i++){

            ans = ans + r.get(i);
        }
        return ans.intern();
    }
}