package AlmostPalindrome;

public class AlmostPalindrome {
    public static boolean isAlmostPalindrome(String s) {
        if (s == null || s.length() < 3 || isPalindrome(s.toLowerCase())) {
            return false;
        }
        s = s.toLowerCase();
        for (int i = 0 ; i < s.length() ; i++ ){
                if(i == 0){
                    if(isPalindrome(s.substring(1))){
                        return true;
                    }
                }  else if ( i == s.length() - 1) {
                    if (isPalindrome(s.substring(0,i))){
                        return true;
                    }
                } else {
                    if (isPalindrome(s.substring(0,i) + s.substring(i+1))){
                        return true;
                    }
                }
        }
        return false;
    }
    private static boolean isPalindrome(String s) {
        int length = s.length();
//        if (length % 2 == 0) {
            for (int i = 0; i < s.length() / 2; i++) {
                if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                    return false;
                }
            }
//        }
//        } else {
//            for (int i = 0; i < int(s.length / 2); i++) {
//                if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
//                    return false;
//                }
//            }
//        }
        return true;
    }
}