package gp2.FactorialMaster;
public class RecursiveFactorial extends Factorial {
    @Override
    public long calculate(int n) {
        if (n <= 1) {
            return 1;
        }
        long result = n;
        return result * calculate(n-1);
    }
}