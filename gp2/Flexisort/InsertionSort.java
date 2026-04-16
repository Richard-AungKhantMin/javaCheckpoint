package gp2.Flexisort;
public class InsertionSort extends Sorter {
    @Override
    public void sort() {
        int[] r = this.getArray();
        if (r == null || r.length < 2) {
            return;
        }

        for (int i = 1; i < r.length; i++) {
            int temp = r[i];            // 1. Pick unsorted element
            int j = i - 1;              // 2. Start comparing at previous position

            while (j >= 0 && r[j] > temp) {  // 3. If previous element is bigger
                r[j + 1] = r[j];        //    Shift: next becomes previous
                j--;                    //    Move left to before one
            }

            r[j + 1] = temp;            // 6. Insert picked element in gap
        }
    }
}

// {34, 64, 25, 12, 22, 11, 90} temp = r[i] = 25, i = 2, j = 1, 64 > 25 yes
// {34, 64, 64, 12, 22, 11, 90} j = 0, 34 > 25 yes
// {34, 34, 64, 12, 22, 11, 90} j = -1
// {25, 34, 64, 12, 22, 11, 90} r[-1 + 1] = r[0] = temp = 25

// temp = unsorted array element
// i = unsorted array index
// i-1 = sorted array idex


/*
b, d, e, c, a
temp = d
b, d, e, c, a
temp = e
b, d, e, c, a
temp = c
b, d, e, c, a
b, d, (e), e, a
b, (d), d, e, a
(b), b, d, e, a
(b), c, d, e, a
temp = a
b, c, d, e, a
b, c, d, (e), e
b, c, (d), d, e
b, (c), c, d, e
(b), b, c, d, e
(), b, b, c, d, e
(), a, b, c, d, e
*/
