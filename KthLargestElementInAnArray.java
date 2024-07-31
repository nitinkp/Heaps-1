import java.util.Arrays;
import java.util.PriorityQueue;

public class KthLargestElementInAnArray {
    /*
    The intuition is to have a min-heap with exactly k largest values. So whenever the (k+1)th element
    is inserted to the heap, we remove the root of the heap which is the minimum of all those, hence still
    keeping only k largest values at any point. Once all values are iterated, we are left with top k largest
    elements in the heap and out of which, the root would be the minimum i.e., kth largest.
     */
    public static int findKthLargest(int[] nums, int k) { //overall T.C O(Nlogk)
        PriorityQueue<Integer> pq = new PriorityQueue<>(); //min-heap
        for (int num : nums) { //loop over the input array, O(N) T.C
            pq.add(num); //add each value into the heap, O(logk) T.C
            if (pq.size() > k) { //when the heap size reaches that of k, O(k) S.C
                pq.poll(); //keep polling the min-value. O(logk) T.C
            }
        }
        return pq.peek(); //whatever is left in the root of heap is the kth largest value
    }

    public static void main(String[] args) {
        int[] nums = {1,3,2,6,4,5,8,7};
        int k = 3;

        System.out.println(k + " = kth largest element in the array " + Arrays.toString(nums) +
                " is: " + findKthLargest(nums, k));
    }
}