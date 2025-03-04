//此oj需要手动引入工具类，直接用会报“编译或运行失败”，如需要用ArrayList，需要手动引入，
//如：import java.util.ArrayList;

import java.util.Arrays;

public class BinTree {
    private char element;
    BinTree left, right;

    public BinTree() {
    }

    BinTree(char element, BinTree left, BinTree right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

    /**
     * 使用递归方法，根据先序遍历和中序遍历结果，创建一棵二叉树
     * @param pres 先序遍历字符数组
     * @param ins 中序遍历字符数组
     * @Example
     *      BinTree root = BinTree.CreateBT("ABDCEGFHI", "DBAEGCHFI");
     */
    public static BinTree CreateBT(char[] pres, char[] ins){ //返回的是根节点带有的树
        int len = pres.length;
        if(len == 0){
            return null;
        }
        int index = 0;
        char rootElement = pres[0];
        BinTree lefTree = null;
        BinTree righTree = null;
        char[] nextPresLeft;
        char[] nextInsLeft;
        char[] nextPresRight;
        char[] nextInsRight;
        for( ; index < len; index++){
            if(ins[index] == rootElement){
                nextInsLeft = Arrays.copyOfRange(ins, 0, index);
                nextPresLeft = Arrays.copyOfRange(pres, 1, 1+index);
                lefTree = CreateBT(nextPresLeft, nextInsLeft);

                nextInsRight = Arrays.copyOfRange(ins, index+1, len);
                nextPresRight = Arrays.copyOfRange(pres, index+1, len);
                righTree = CreateBT(nextPresRight, nextInsRight);
                break;
            }
        }
        
        return new BinTree(rootElement, lefTree, righTree);
        // please enter your code here...
    }

    /**
     * 使用递归方法，先序遍历二叉树
     * @param root  当前树
     * @return  遍历后的序列
     * @Example
     *      BinTree.PreOrderTraversal(root)
     */
    public static StringBuilder PreOrderTraversal(BinTree root) {
        StringBuilder sb = new StringBuilder();
        if(root == null){
            return sb;
        }
        sb.append(root.element);
        sb.append(PreOrderTraversal(root.left));
        sb.append(PreOrderTraversal(root.right));
        return sb;
        // please enter your code here...
    }

    /**
     * 使用递归方法，中序遍历二叉树
     * @param root  当前树
     * @return  遍历后的序列
     * @Example
     *      BinTree.InOrderTraversal(root)
     */
    public static StringBuilder InOrderTraversal(BinTree root) {
        StringBuilder sb = new StringBuilder();
        if(root == null){
            return sb;
        }
        sb.append(InOrderTraversal(root.left));
        sb.append(root.element);
        sb.append(InOrderTraversal(root.right));
        return sb;
        // please enter your code here...
    }

    /**
     * 使用递归方法，后序遍历二叉树
     * @param root  当前树
     * @return  遍历后的序列
     * @Example
     *      BinTree.PostOrderTraversal(root)
     */
    public static StringBuilder PostOrderTraversal(BinTree root) {
        StringBuilder sb = new StringBuilder();
        if(root == null){
            return sb;
        }
        sb.append(PostOrderTraversal(root.left));
        sb.append(PostOrderTraversal(root.right));
        sb.append(root.element);
        return sb;
        // please enter your code here...
    }

}