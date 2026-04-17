

public class AlmostPalindrome {
    public static boolean isAlmostPalindrome(String str) {

        if (str.length()<3)return false;
        if (isPalindrome(str)) return false;

        for (int i = 0; i < str.length();i++){
            String modifString = str.substring(0,i)+str.substring(i+1);

            if (isPalindrome(modifString)) return true;
        }
        return false;
    }



    private static boolean isPalindrome(String s) {
//       
            for (int i = 0; i < s.length() / 2; i++) {
                if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                    return false;
                }
            }
//        
        return true;
    }
}

