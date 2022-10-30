package hw6;
/** A class to perform QuickSort algorithm.
 *  @author Ali Kaya
 */
public class QuickSort {
    /**
     * This function takes last element as pivot, places
     * the pivot element at its correct position in sorted
     * array, and places all smaller (smaller than pivot)
     * to left of pivot and all greater elements to right
     * of pivot
     * @param arr -our initial array
     * @param low - our start index (0)
     * @param high - our last index  (arr.length-1)
     * @return - int  returns partitioning index
     */
    public int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);

        for(int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    /**
     * The main function that implements QuickSort algorithm itself
     * @param arr - our initial array
     * @param low - our beginning index
     * @param high - our last index
     */
    public void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    /**
     * A utility function to swap two elements
     * @param arr - our initial array
     * @param first - first element's index number
     * @param second - seconds element's index number
     */
    private void swap(int[] arr , int first , int second){
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
}
