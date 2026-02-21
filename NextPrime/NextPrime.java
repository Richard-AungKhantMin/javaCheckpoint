public class NextPrime {
    public static Integer nextPrime(Integer n) {
        if (n <= 1 ){
            return 2;
        }
        if ( n == 2){
            return 3;
        }
        for (int i = n + 1;; i++){
            if (IsPrime(i)){
                return i;
            }
        }
    }
    public static boolean IsPrime(int n) {
        if (n <= 1) {
            return false;
        }
        if (n == 2){
            return true;
        }
        if (n % 2 == 0){
            return false;
        }
        for (int i = 3; i <= n / 2; i += 2){
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }
}