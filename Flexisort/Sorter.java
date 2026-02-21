public abstract class Sorter {
    private int[] array;

    public int[] getArray() {
        return this.array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public abstract void sort();
}