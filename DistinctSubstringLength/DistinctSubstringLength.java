public class DistinctSubstringLength {
    public int maxLength(String s) {
       int[] result = new int[s.length()];
       String biggestSubstring;
        int MAX_VALUE = 0;
       if (s == null || s.isEmpty()){
        return 0;
       }
       for (int start = 0; start < s.length(); start++){
            result[start] = 0;
            biggestSubstring = String.valueOf(s.charAt(start));
            result[start]++;
            for (int i = start + 1; i < s.length(); i++){
                String charachter = String.valueOf(s.charAt(i));
                if(biggestSubstring.contains(charachter)){
                    break;
                }
                biggestSubstring += charachter;
                result[start]++;  
            }
       }
        for(int i = 0; i < result.length;i++){
            if (result[i] > MAX_VALUE){
                MAX_VALUE = result[i];
            }
        } 
        return MAX_VALUE; 
    }
}