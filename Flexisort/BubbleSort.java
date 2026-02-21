
public class BubbleSort extends Sorter {
    @Override
    public void sort() {
        int[] numbers = this.getArray();
        if (numbers == null || numbers.length < 2) {
            return;
        }
        int n = numbers.length;
        if(n < 2) return;
        boolean swapped = false;
        for(int i = 0 ; i < n ; i++){
            if(i != 0 && !swapped ){
                break;
            }
            swapped = false;
            for (int j = 0; j < n - i - 1; j++ ) {
                if (numbers[j] > numbers[j + 1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                    swapped = true;
                }
            }
        }
    }
}