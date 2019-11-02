 //编写代码，以给定值x为基准将链表分割成两部分，所有小于x的结点排在大于或等于x的结点之前
    public ListNode partition(int x) {
        if (head == null) {
            return null;
        }
        ListNode cur = this.head;
        ListNode beforeStart = null;
        ListNode beforeEnd = null;
        ListNode afterStart = null;
        ListNode afterEnd = null;
        while (cur != null) {
            ListNode curNext = cur.next;
            cur.next = null;
            //cur.data < x
            if (cur.data < x) {
                //第一次插入
                if (beforeStart == null) {
                    beforeStart = cur;
                    beforeEnd = cur;
                } else {
                    beforeEnd.next = cur;
                    beforeEnd = beforeEnd.next;
                }

            } else {
                //第一次插入
                if (afterStart == null) {
                    afterStart = cur;
                    afterEnd = cur;
                } else {
                    afterEnd.next = cur;
                    afterEnd = afterEnd.next;
                }
            }
            cur = curNext;
        }

        if (beforeStart != null) {
            beforeEnd.next = afterStart;
            return beforeStart;
        } else {
            return afterStart;
        }
    }

    //在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针
    public ListNode deleteDuplication1() {
        ListNode node = new ListNode(-1);
        ListNode cur = this.head;
        ListNode tmp = node;
        while (cur != null) {
            if (cur.next != null &&
                    cur.data == cur.next.data) {
                //1、循环
                while (cur.next != null && cur.next.data == cur.data) {
                    cur = cur.next;
                }
                cur = cur.next;
                //2、退出循环 cur要多走一步
                //
            } else {
                //当前节点 不等于下一个节点的时候
                tmp.next = cur;
                cur = cur.next;
                tmp = tmp.next;
            }
        }
        tmp.next = null;
        return node.next;
    }

    //链表的回文结构
    public boolean chkPalindrome() {
        ListNode fast = this.head;
        ListNode slow = this.head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        //反转
        ListNode p = slow.next;
        while (p != null) {
            ListNode pNext = p.next;
            //反转
            p.next = slow;
            slow = p;
            p = pNext;
        }

        //slow往前    head 往后  .data不一样 返回false
        //直到相遇
        while (head != slow) {
            if (this.head.data != slow.data) {
                return false;
            }
            if (head.next != slow) {
                return true;
            }
            this.head = this.head.next;
            slow = slow.next;
        }
        return true;

    }

    //给定一个链表，判断链表中是否有环
    public void creatCycle() {
        ListNode cur = this.head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = this.head.next;
    }

    public boolean hasCycle() {
        ListNode fast = this.head;
        ListNode slow = this.head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    //给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null
    public ListNode entranceCycle() {
        ListNode fast = this.head;
        ListNode slow = this.head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        if (fast != slow) {
            return null;
        }
        slow = this.head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    //找环的长度
    public int getLenCycle() {
        ListNode fast = this.head;
        ListNode slow = this.head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        if (fast != slow) {
            return 0;
        }
        int len = 0;
        do {
            slow = slow.next;
            len++;
        } while (slow != fast);
        return len;
    }

    //输入两个链表，找出它们的第一个公共结点
    private static int getLen(ListNode head) {
        ListNode cur = head;
        int len = 0;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        return len;
    }

    public static ListNode getIntersectionNode
            (ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        //永远指向最长的单链表
        ListNode pL = headA;
        //永远指向最短的单链表
        ListNode pS = headB;
        //分别求长度
        int lenA = getLen(headA);
        int lenB = getLen(headB);
        //求长度的差值
        int len = lenA - lenB;
        //如果是负数-》pL = headB;  pS = headA
        //只需要让pL走len步就好了
        if (len < 0) {
            pL = headB;
            pS = headA;
            len = -len;
        }
        while (len != 0) {
            pL = pL.next;
            len--;
        }
        //走完len步之后  两个同时开始走
        //一直走 走到next值相同时 就是交点
        while (pL != pS && pL != null) {
            pL = pL.next;
            pS = pS.next;
        }
        if (pL == pS && pL != null) {
            return pS;
        }
        return null;
    }

    //将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的
    public ListNode mergeTwoLists(
            ListNode headA, ListNode headB) {
        ListNode node = new ListNode(-1);
        ListNode cur = node;
        while (headA != null && headB != null) {
            if (headA.data < headB.data) {
                cur.next = headA;
                headA = headA.next;
                cur = cur.next;
            } else {
                cur.next = headB;
                headB = headB.next;
                cur = cur.next;
            }
        }
        if (headA == null) {
            cur.next = headB;
        } else {
            cur.next = headA;
        }
        return node.next;
    }

}