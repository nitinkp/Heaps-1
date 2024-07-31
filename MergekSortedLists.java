import java.util.PriorityQueue;

public class MergekSortedLists {
    /*
    The intuition is to add all the heads of the list of linked-lists into a min-heap. Keep polling the min element
    and store it in a pointer to the actual result.Now, whichever head gave the minimum element, add the next node of it
    to the heap. At any point, we will only be maintaining a max of length of input list size in heap. Once the heap
    becomes empty, that means all nodes are traversed and the result node contains the sorted merged nodes.
     */
    public static ListNode mergeKLists(ListNode[] lists) { //Overall T.C, O(klogk) + O(Nlogk) = O(Nlogk)
        //null check
        if(lists.length == 0) return null;

        PriorityQueue<ListNode> pq =
                new PriorityQueue<>((ListNode a, ListNode b) -> a.val - b.val); //min-heap based on listnode's values

        for(ListNode head : lists) { //loop over the list of linked lists, O(klogk) T.C, O(k) S.C
            if(head != null)    pq.offer(head); //and add each linked lists' head if it is present into heap
        }

        ListNode result = new ListNode(Integer.MIN_VALUE); //to store the final merged array
        ListNode current = result; //pointer to result as it will be changing

        while(!pq.isEmpty()) { //until the heap becomes empty, O(nlogk) T.C
            ListNode node = pq.poll(); //poll the minimum valued list node
            current.next = node; //attach it to the current pointer
            current = current.next; //and move current to its next pointer
            if(node.next != null)   pq.offer(node.next); //whichever head was the minimum, add its next node into heap
        }

        return result.next; //since current was a pointer from result, next of result starts storing the actual merged
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(6);

        // List 2: 1 -> 3 -> 4
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(2);
        l2.next.next = new ListNode(3);

        // List 3: 2 -> 6
        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(5);

        ListNode[] lists = new ListNode[]{l1, l2, l3};

        ListNode result = mergeKLists(lists);

        // Print the merged list
        System.out.print("The merged sorted linked list is: " );
        printList(result);
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}
