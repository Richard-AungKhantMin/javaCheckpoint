public class AnagramChecker {
    public boolean isAnagram(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        if (str1.length() != str2.length()){
            return false;
        }
        for (int i = 0; i < str1.length(); i++){
                if(!str2.contains(String.valueOf(str1.charAt(i)))){
                    return false;
                }
        }
        return true;        
    }
}