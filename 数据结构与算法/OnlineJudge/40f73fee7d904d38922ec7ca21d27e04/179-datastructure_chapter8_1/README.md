判别一个有向图中是否有环路,并把所有环路打印出来。以字符串的形式输出所有环所在的边，边的顺序按照升序排序

Tip：多个环也是按边的升序输出，例如"(1,3)(2,3)(3,1)(3,4)(4,2)"

例如,平台会执行如下代码,对函数进行测试:

```java
    public void test1(){
        int Vertexnum = 5;
        int Edgenum=5;
        int [][]Edge={{0,3},{1,2},{2,4},{3,2},{3,4}};
        DirectedGraph graph= new DirectedGraph(Vertexnum,Edge,Edgenum);
        assertTrue("".equals(DirectedGraph.FindCycle(graph).toString()));
    }
    ```

