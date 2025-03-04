//此oj需要手动引入工具类，直接用会报“编译或运行失败”，如需要用ArrayList，需要手动引入，
//如：import java.util.ArrayList;

//只测试了暴力求解和公式法，都是可以通过的，测试用例比较少，不用担心；
//常见的问题都给了注释，还遇到bug找群里的小伙伴交流一下。

public class Josephus {
    public static Node head;
    public static void main(String[] args){
        Node newNode = roundACircle(3);
        printACircle(newNode);
        lastRemaining(5, 3);
    }
    public static Node roundACircle(int n){
        Node rear = new Node();
        Node p = rear;
        for(int v = 0; v < n; v++){
            p.next = new Node(v);
            p = p.next;
        }
        p.next = rear.next;
        return rear;//返回头节点，不是第一个节点
    }
    public static Node killmthNode(int m, Node rear){
        for(int i = 0; i < m-1; i++){
            rear = rear.next;
            System.out.printf("now rear is on %d\n",(rear.value));
        }
        System.out.printf("kill %d\n",(rear.next.value));
        rear.next = rear.next.next;
        return rear;
    }
    public static int lastRemaining(int n, int m) {//不要更改这里的static修饰符
        // 具体代码写这里
		
		// 另外因为语言级别的问题，菱形操作符里的具体类型全部要写上，不然可能也会报错；
		// 如：ArrayList<Integer> list = new ArrayList<Integer>(n);
        head = roundACircle(n);//头结点
        Node rear = head;               //要杀的人的前一个节点
        for(int i = 0; i < n-1; i++){
            rear = killmthNode(m, rear);
        }
		
        return rear.value;//这里return自己的结果
    }
    public static void printACircle(Node head){//传入头节点
        Node p = head.next;
        do{
            System.out.println(p.value);
            p = p.next;
        }while(p != head.next);
    }

}

