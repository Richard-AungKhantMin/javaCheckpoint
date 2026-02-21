public class LongestCommonPrefix {
    public String findLongestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0){
            return "";
        }
        int firstIndex = findTheLongestIndex(strs);
        String commonPrefix = strs[firstIndex];
        
        for (int i = 0; i < strs.length; i++){
            for (int j = 0; j < strs[i].length(); j++){
                if(j == 0 && commonPrefix.charAt(j) != strs[i].charAt(j) ){
                    return "";
                } else if ((commonPrefix.length() - 1) < j){
                    break;
                } else if (commonPrefix.charAt(j) != strs[i].charAt(j)) {
                        commonPrefix = commonPrefix.substring(0, j);
                        break; 
                }
            }
        }
        return commonPrefix;
    }
    public int findTheLongestIndex(String[] strs){
        int index = 0;
        int size = strs[0].length();
        for (int i = 1; i < strs.length; i++){
            if(strs[i].length() > size) {
                size = strs[i].length();
                index = i;
            }
        }
        return index;
    }
}