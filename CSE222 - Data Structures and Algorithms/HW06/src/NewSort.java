package hw6;
/** A class to perform NewSort algorithm.
 *  @author Ali Kaya
 */
public class NewSort {
    /**
     * MyResult inner class contains two integer that indicates minimum index and maximum index
     */
    private static class MyResult {
        /**Indicates maximum element's index of our array */
        private int indexMax;
        /**Indicates minimum element's index of our array */
        private int indexMin;

        /**
         * This one parameter constructor initiates our MyResult class with assumption of minimum and maximum indices
         * @param second - indicates tail element
         */
        public MyResult(int second) {
            this.indexMin = second;
            this.indexMax = second;
        }

        /**
         * returns maximum number's index
         * @return int- returns indexMax
         */
        public int getIndexMax() {
            return indexMax;
        }
        /**
         * returns minimum number's index
         * @return int- returns indexMin
         */
        public int getIndexMin() {
            return indexMin;
        }

        /**
         * The min_max_finder() is a recursive function that returns
         * the indices of minimum and maximum items between the given head and tail items in a single execution together
         * @param arr - our integer array
         * @param head - our beginning index
         * @param tail - our last index
         * @return - MyResult class that contains indexMin and indexMax both
         */
        public MyResult min_max_finder(int[] arr, int head, int tail){
            if(head == tail){
                return this;
            }
            else{
                if (arr[head] > arr[getIndexMax()]){
                    indexMax = head;
                }
                if (arr[head] < arr[getIndexMin()]){
                    indexMin = head;
                }
                return this.min_max_finder(arr , head+1 , tail);
            }
        }
    }

    /**
     * The main function that implements NewSort algorithm itself
     * @param arr - our initial array
     * @param head - our beginning index
     * @param tail - our last index
     */
    public int[] newSort (int[] arr, int head, int tail) {
        if(head > tail)
            return arr;
        else
        {
            MyResult result = new MyResult(tail);
            result = result.min_max_finder(arr, head, tail);
            swap(arr , head , result.getIndexMin());
            swap(arr , tail , result.getIndexMax());
            return newSort(arr, head + 1, tail -1);
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
