import org.junit.Test;
import static org.junit.Assert.*;

public class BinTreeTest {
    @Test
    public void test1(){

        BinTree root = BinTree.CreateBT("ABDCEGFHI".toCharArray(), "DBAEGCHFI".toCharArray());
        assertTrue("ABDCEGFHI".equals(BinTree.PreOrderTraversal(root).toString()));
        assertTrue("DBAEGCHFI".equals(BinTree.InOrderTraversal(root).toString()));
        assertTrue("DBGEHIFCA".equals(BinTree.PostOrderTraversal(root).toString()));
    }

    @Test
    public void test2(){
        BinTree root = BinTree.CreateBT("ABEFCGDKIHJ".toCharArray(), "EFBGCDAIJHK".toCharArray());
        assertTrue("ABEFCGDKIHJ".equals(BinTree.PreOrderTraversal(root).toString()));
        assertTrue("EFBGCDAIJHK".equals(BinTree.InOrderTraversal(root).toString()));
        assertTrue("FEGDCBJHIKA".equals(BinTree.PostOrderTraversal(root).toString()));
    }

}
