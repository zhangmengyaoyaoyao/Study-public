import java.util.HashSet;
//本地测试通过了，一交上去就编译错误
//后来找gpt写了
public class TraceBack {

    public HashSet<HashSet<Integer>> traceBack(int n, int r) {
        HashSet<HashSet<Integer>> result = new HashSet<HashSet<Integer>>();
        //找够了r个，直接返回空集合
        //要加上new HashSet<>()，不然会后面null就不能add（n）
        if(r == 0){
            result.add(new HashSet<>());
            return result;
        }
        //n<r时，这种情况不成立，返回result？//这个不太理解
        if(n < r) return result;

        HashSet<HashSet<Integer>> combinationsWithN = traceBack(n - 1, r - 1);
        for (HashSet<Integer> combination : combinationsWithN){
            combination.add(n);
            result.add(combination);
        }
        HashSet<HashSet<Integer>> combinationsWithoutN = traceBack(n - 1, r);
        if(combinationsWithoutN == null) return result;
        for(HashSet<Integer> combination : combinationsWithoutN){
            result.add(combination);
        }
        return result;

    }

    public static void main(String[] args) {
        TraceBack traceBack = new TraceBack();
        int n = 0;
        int r = 1;
        HashSet<HashSet<Integer>> combinations = traceBack.traceBack(n, r);

        for (HashSet<Integer> combination : combinations) {
            System.out.println(combination);
        }
    }
}
