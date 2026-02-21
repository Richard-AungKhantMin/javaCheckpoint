public class FirstUnique {
    public char findFirstUnique(String s) {
        if(s== null || s.isEmpty()){
            return '_';
        }
        char first = '_';
        first = s.charAt(0);
        if (!s.substring(1).contains(String.valueOf(first))) return first;
        for (int i = 0; i < s.length() - 1; i++){
            first = s.charAt(i);
            if (!s.substring(i+1).contains(String.valueOf(first)) 
            && !s.substring(0, i).contains(String.valueOf(first))){
                return first;
            }
        }
        first = s.charAt(s.length() - 1);
        if (!s.substring(0, s.length()-1).contains(String.valueOf(first))){
                return first;
        }
        return '_';
    }
}