public class InsertionSort extends Sorter {
    @Override
    public void sort() {
        int[] numbers = this.getArray();
        if (numbers == null || numbers.length < 2) {
            return;
        }
        int n = numbers.length;
        boolean sorted = true;
        for (int i = 1; i < n; i++) {
            if (numbers[i] < numbers[i - 1]) {
                sorted = false;
            }
        }
        if (sorted) {
            return;
        }
        for (int i = 1; i < n; i++) {
            // 1. Save the key once
            int key = numbers[i];

            // 2. Find the insertion point (j)
            int j = i - 1;

            // 3. Shift all larger elements up
            while (j >= 0 && numbers[j] > key) {
                numbers[j + 1] = numbers[j]; // Just shift
                j--;
            }

            // 4. Insert the key into its final spot
            numbers[j + 1] = key;
        }
    }
}