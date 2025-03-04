//此oj需要手动引入工具类，直接用会报“编译或运行失败”，如需要用ArrayList，需要手动引入，
//如：import java.util.ArrayList;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class DirectedGraph {
    private int Vertexnum;
    private int Edgenum;
    private int[][] Edge;

    public DirectedGraph() {
    }
    /**
     * @param Vertexnum  表示顶点的数目，顶点编号为0，Vertexnum-1
     * @param Edge 二维数组，表示边，第一维是边的个数，第二维长度为2，表示一条有向边，分别为出度和入度。
     * @param Edgenum  表示有几条边
     */
    DirectedGraph(int Vertexnum, int[][] Edge, int Edgenum) {
        this.Vertexnum = Vertexnum;
        this.Edge = Edge;
        this.Edgenum = Edgenum;
    }

    private static HashSet<Integer> circleEdgeIndex ;
    
    public static void main(String[] args){
        int Vertexnum =6;
        int Edgenum=6;
        int [][]Edge={{0,1},{1,3},{2,3},{3,4},{4,0},{3,2}};
        DirectedGraph graph= new DirectedGraph(Vertexnum,Edge,Edgenum);
        System.out.println(FindCycle(graph));

    }

    /**
     * 如果包括环 返回一个包含环的字符串格式如下“”
     *
     * @param graph  输入的图对象
     * @return  若有环，按升序返回环所在的边的字符串，eg"(1,2)(2,3)(3,1)"，如果不包括则返回空字符串
     */
    public static StringBuilder FindCycle(DirectedGraph graph) {
        // please enter your code here...
        //查看所有边
        /*
        for (int i = 0; i < graph.Edgenum; i++) {
            System.out.println(Arrays.toString(graph.Edge[i]));
        }*/

        circleEdgeIndex = new HashSet<>();

       
        boolean[] visited = new boolean[graph.Vertexnum];
        for(int i = 0;i < graph.Vertexnum; i++){
            visited[i] = false;
        }
        for(int i = 0; i < graph.Vertexnum; i++){
            List<Integer> vertexInPath = new ArrayList<>();
            vertexInPath.add(i);
            List<Integer> edgeInPath = new ArrayList<>();
            dfs(graph, i, visited, vertexInPath, edgeInPath);
        }
        
        int num = circleEdgeIndex.size();
        int[][] edgeInCircle = new int[num][2];
        int count = 0;
        for(int element : circleEdgeIndex){
            edgeInCircle[count][0] = graph.Edge[element][0];
            edgeInCircle[count][1] = graph.Edge[element][1];
            count++;
        }
        Arrays.sort(edgeInCircle, Comparator.comparingInt((int[] arr) -> arr[0]).thenComparingInt(arr -> arr[1]));

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < num; i++){
            sb.append("(");
            sb.append(edgeInCircle[i][0]+"");
            sb.append(",");
            sb.append(edgeInCircle[i][1]+"");
            sb.append(")");

        }
        return sb;
    }

    private static void dfs(DirectedGraph graph, int startVertex, boolean[] visited, List<Integer> vertexInPath, List<Integer> edgeInPath){
        visited[startVertex] = true;
        int[] neighbourEdgeIndex = nextNeighbourEdge(graph, startVertex);
        for(int i = 0; i < graph.Vertexnum; i++){
            if(neighbourEdgeIndex[i] != -1){//顶点i是下一个顶点
                vertexInPath.add(i);
                edgeInPath.add(neighbourEdgeIndex[i]);//记录指向顶点i的边的下标
                if(visited[i]){//且被访问过
                    int startIndex = vertexInPath.indexOf(i);
                    for(int j = startIndex; j < edgeInPath.size(); j++){
                        circleEdgeIndex.add(edgeInPath.get(j));    
                    }

                    circleEdgeIndex.add(neighbourEdgeIndex[i]);//加入从start到i的index路径下标
                    
                    
                }else{
                    dfs(graph, i, visited, vertexInPath, edgeInPath);
                    //当你从一个节点回溯到上一层时，需要将当前节点从路径中移除，以便继续尝试其他可能的路径。
                    //而且必须是在这个else语句里（即当前节点没有被访问过，暂且不是环中一节的情况）
                    vertexInPath.remove(vertexInPath.size()-1);
                    edgeInPath.remove(edgeInPath.size()-1);
                }
            }
        }
        
        //当你从一个节点回溯到上一层时，需要将当前节点从路径中移除，以便继续尝试其他可能的路径。
        visited[startVertex] = false;
        
    }

    /*
     * 返回int数组，
     * 一数组下标代表顶点
     * 若是邻居，则记录边的下标；若不是，则记录-1
     */
    private static int[] nextNeighbourEdge(DirectedGraph graph, int vertex){
        int[] neighbourEdge = new int[graph.Vertexnum];
        for(int i = 0; i < graph.Vertexnum; i++){
            neighbourEdge[i] = -1;
        }
        
        for(int i = 0; i < graph.Edgenum; i++){
            if(graph.Edge[i][0] == vertex){
                int neighbourVertex = graph.Edge[i][1];
                neighbourEdge[neighbourVertex] = i;
            }
        }

        return neighbourEdge;
    }

}