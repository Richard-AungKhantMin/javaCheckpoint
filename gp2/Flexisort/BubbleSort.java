package gp2.Flexisort;

public class BubbleSort extends Sorter {
    @Override
    public void sort() {
        int[] numbers = this.getArray();
        if (numbers == null || numbers.length < 2) {
            return;
        }

        boolean swapped = true;
        for (int i = 0; i < numbers.length && swapped; i++) {
            swapped = false;
            for (int j = 0; j < numbers.length - i - 1; j++) {
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

/*
b, d, e, c, a |
b, d, c, a, e
b, d, c, a, | e
b, c, d, a, | e
b, c, a, d, | e
b, c, a, | d, e
b, c, a, | d, e
b, a, c | d, e
b, a, | c, d, e
a, b, | c, d, e (r[0] > r[0+1])
a, | 




*/