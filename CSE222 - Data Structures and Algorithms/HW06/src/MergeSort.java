package hw6;
/** A class to perform MergeSort algorithm.
 *  @author Ali Kaya
 */
public class MergeSort {
    /**
     * Merges two subarrays of arr.
     * First subarray is arr[l,...,m]
     * Second subarray is arr[m+1,...,r]
     * @param arr -our initial array
     * @param l - our start index (0)
     * @param m - middle element of array
     * @param r - our last index  (arr.length-1)
     */
    public void merge(int[] arr , int l , int m , int r){

        int i , j , k;
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] Left = new int[n1];
        int[] Right = new int[n2];

        for (i = 0; i < n1; i++) {
            Left[i] = arr[l + i];
        }
        for (j = 0; j < n2; j++) {
            Right[j] = arr[m + 1 + j];
        }

        i = 0;
        j = 0;
        k = l;
        while(i < n1 && j < n2){
            if (Left[i] <= Right[j]){
                arr[k] = Left[i];
                i++;
            }
            else{
                arr[k] = Right[j];
                j++;
            }
            k++;
        }
        while(i < n1){
            arr[k] = Left[i];
            i++;
            k++;
        }
        while(j < n2){
            arr[k] = Right[j];
            j++;
            k++;
        }
    }
    /**
     * The main function that implements MergeSort algorithm itself
     * @param arr - our initial array
     * @param l - our beginning index
     * @param r - our last index
     */
    public void sort(int[] arr , int l , int r){

        if(l < r){
            int m = l + (r-l)/2;
            sort(arr , l , m);
            sort(arr , m + 1 ,r);
            merge(arr , l , m , r);
        }
    }

}
