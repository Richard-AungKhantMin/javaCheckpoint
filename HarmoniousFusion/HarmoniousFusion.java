public class HarmoniousFusion {
    public int[] merge(int[] arr1, int[] arr2) {
        int length1 = arr1.length;
        int length2 = arr2.length;
        int[] result = new int[length1 + length2];
        int i = 0;
        int j = 0;
        for (int k = 0; k < length1 + length2; k++){
            if ((j >= length2)|| (i < length1 && arr1[i] <= arr2[j])  ) {
                result[k] = arr1[i];
                i++;
               
            } else if ((i >= length1) || (j < length2 && arr2[j] < arr1[i])){
                result[k] = arr2[j];
                j++;
            

            }
        }
        return result;
    }
}