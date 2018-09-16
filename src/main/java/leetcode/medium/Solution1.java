package leetcode.medium;

/**
 * Created by zhangwt n 2018/8/28.
 * 2. Add Two Numbers
 */
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
}
public class Solution1 {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode l3 = new ListNode(0);
        boolean flag = false;
        ListNode node = l3;
        while (true) {
            int t = ((l1 != null?l1.val:0)+(l2 != null?l2.val:0))%10;
            node.val = (t+(flag?1:0))%10;
            if (((l1 != null?l1.val:0)+(l2 != null?l2.val:0)+(flag?1:0))/10 == 0){
                flag=false;
            }else{
                flag=true;
            }
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }

            if (l1 == null && l2 == null && !flag){
                return l3;
            }else {
                ListNode newNode = new ListNode(0);
                ListNode temp = l3;
                while(temp.next != null){
                    temp = temp.next;
                }
                temp.next = newNode;
                node = temp.next;
            }
        }

    }

}
