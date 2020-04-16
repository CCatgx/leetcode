package easy;

/**
 * @ClassName easy021
 * @Description TODO
 * @Author CcatGX
 * @Date 2019/11/5 11:31
 * @Version 1.0
 **/
public class easy021 {

    /**
     * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     *  示例：
     *  输入：1->2->4, 1->3->4
     *  输出：1->1->2->3->4->4
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode temp = null;
        if(l1!=null&&l2!=null){
            //如果l1，比l2小，则l1节点在前面
            if(l1.val <= l2.val){
                temp = mergeTwoLists(l1.next,l2);
                System.out.println("此时的l1为"+l1);
                System.out.println("返回的temp为1"+temp);
            } else {
                temp = mergeTwoLists(l1,l2.next);
                System.out.println("此时的l2为"+l2);
                System.out.println("返回的temp为2"+temp);
            }
        }
        if(l1 == null ){
            temp = new ListNode(l2.val);
            System.out.println("到尾部了");
        }
        if(l2 == null){
            temp = new ListNode(l1.val);
        }
        System.out.println("temp="+temp);
        return temp;
    }

    static class ListNode {
        int val;
        ListNode next;
        public ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return val  + "--->"+next;
        }
    }

    public static void main(String[] args){
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l4 = new ListNode(4);
        l1.next = l2;
        l2.next = l4;

        ListNode l11 = new ListNode(1);
        ListNode l13 = new ListNode(3);
        ListNode l14 = new ListNode(4);
        l11.next = l13;
        l13.next = l14;



        easy021 e021 = new easy021();
        System.out.println("mergeTwoLists result: "+e021.mergeTwoLists(l1,l11));
    }
}
