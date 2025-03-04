public class PolynList {

    //多项式相加
    public Node add(Node link1, Node link2) {//分别为两条链表的头节点 (多项式从下一个节点开始，若为空多项式，则仅含头节点)
        // please enter your code here...
        //按exp升序排序
        Node p1 = new Node(), p2 = new Node(), head = new Node();
        p1 = link1.next;
        p2 = link2.next;
        Node p = head;
        while(true){
            //一条多项式链表为空时
            if(p1 == null){
                p.next = p2;
                break;
            }else if(p2 == null){
                p.next = p1;
                break;
            }

            if(p1.exp < p2.exp){
                p.next = p1;
                p = p1;
                p1 = p1.next;
            }else if(p1.exp > p2.exp){
                p.next = p2;
                p = p2;
                p2 = p2.next;
            }else{ //p1.exp == p2.exp
                p1.coef += p2.coef;
                if(p1.coef == 0){
                    p1 = p1.next;
                    p2 = p2.next;
                }else{
                    p.next = p1;
                    p = p1;
                    p1 = p1.next;
                    p2 = p2.next;
                }
               
            }
            
        }
        return head.next;
   }
}
