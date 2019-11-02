1.
给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。

要求返回这个链表的深拷贝。 
/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
*/
class Solution {
    public Node copyRandomList(Node head) {
        if(head==null){
            return null;
        }
        Node node=head;
        while(node!=null){
            Node clone=new Node(node.val,node.next,node.random);
            clone.next=node.next;
            node.next=clone;
            node=node.next.next;
        }
        node=head;
        while(node!=null){
            if(node.random!=null){
                node.next.random=node.random.next;
            }else{
                node.next.random=null;
            }
            node=node.next.next;
        }
         node=head;
        Node newHead=head.next;
        while(node.next!=null){
            Node cur=node.next;
            node.next=node.next.next;
            node=cur;
        }
        return newHead;
    }
}

2.
给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
示例 1:

输入: 1->2->3->4->5->NULL, k = 2
输出: 4->5->1->2->3->NULL
解释:
向右旋转 1 步: 5->1->2->3->4->NULL
向右旋转 2 步: 4->5->1->2->3->NULL

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/rotate-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null){
            return null;
        }
        k%=getLen(head);
        if(k==0){
            return head;
        }
         k=getLen(head)-1-k;
        ListNode newHead=null;
        ListNode cur=head;
        while(cur.next!=null){
            cur=cur.next;
        }
        cur.next=head;
        cur=head;
        while(k!=0){
            cur=cur.next;
            k--;
        }
            newHead=cur.next;
            cur.next=null;
        return newHead;
    }
    public int getLen(ListNode head){
        ListNode cur=head;
        int len=0;
        while(cur!=null){
            len++;
            cur=cur.next;
        }
        return len;
    }
}


