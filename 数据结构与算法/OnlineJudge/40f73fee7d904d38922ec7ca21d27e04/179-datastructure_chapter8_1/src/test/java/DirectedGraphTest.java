import org.junit.Test;


import static org.junit.Assert.assertTrue;

public class DirectedGraphTest {
    @Test
    public void test1(){
        int Vertexnum = 5;
        int Edgenum=5;
        int [][]Edge={{0,3},{1,2},{2,4},{3,2},{3,4}};
        DirectedGraph graph= new DirectedGraph(Vertexnum,Edge,Edgenum);
        assertTrue("".equals(DirectedGraph.FindCycle(graph).toString()));
    }
    @Test
    public void test2(){
        int Vertexnum = 3;
        int Edgenum=3;
        int [][]Edge={{0,0},{0,2},{1,2}};
        DirectedGraph graph= new DirectedGraph(Vertexnum,Edge,Edgenum);
        assertTrue("(0,0)".equals(DirectedGraph.FindCycle(graph).toString()));
    }
    @Test
    public void test3(){
        int Vertexnum =7;
        int Edgenum=9;
        int [][]Edge={{0,1},{1,3},{2,3},{2,5},{3,1},{3,4},{4,2},{4,5},{6,1}};
        DirectedGraph graph= new DirectedGraph(Vertexnum,Edge,Edgenum);
        assertTrue("(1,3)(2,3)(3,1)(3,4)(4,2)".equals(DirectedGraph.FindCycle(graph).toString()));
    }
}
