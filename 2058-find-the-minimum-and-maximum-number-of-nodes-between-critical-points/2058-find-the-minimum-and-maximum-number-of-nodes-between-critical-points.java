/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }
        List<Integer> point = new ArrayList<>();
        ListNode curr = head.next;
        ListNode next = curr.next;
        ListNode prev = head;
        int index = 1;

        while(next != null){
            // local maxima
            if(prev.val < curr.val && curr.val > next.val){
                point.add(index);
            } else if(prev.val > curr.val && curr.val < next.val){
                point.add(index);
            }
            prev = curr;
            curr = next;
            next = next.next;
            index++;
        }
        if(point.size() < 2){
            return new int[]{-1, -1};
        }
        int min = Integer.MAX_VALUE;
        for(int i=1; i<point.size(); i++){
            min = Math.min(min, point.get(i) - point.get(i - 1));
        }
        int max = point.get(point.size() - 1) - point.get(0);

        return new int[]{min , max};
    }
}