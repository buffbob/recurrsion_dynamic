package dynamic;

import java.util.HashMap;
import java.util.Map;

public class Fib {

    public int number;

    public Fib(int n) {
        this.number = n;
    }

    public int basicRec(int n){
        if(n == 0) return 1;
        if(n == 1) return 1;
        return basicRec(n-1) + basicRec(n-2);
    }
// top-down
    public int memoization(int n, Map<Integer, Integer> table) {
        if(!table.containsKey(n)) {
            table.put(n, memoization(n-1, table) + memoization(n-2, table));
        }
        return table.get(n);
    }
// bottom-up
    public int tabulation(int n, Map<Integer, Integer> table) {
        for (int i = 2; i <= n; i++) {
            table.put(i, table.get(i-1) + table.get(i-2));
        }
        // O(N)
        // wow!
        return table.get(n);
    }
    private static Map<Integer, Integer> makeFibMap() {
        Map<Integer, Integer> map = new HashMap<>();
        // initialize base cases
        map.put(0,1);
        map.put(1,1);
        return map;
    }

    public static void main(String[] args) {
        Fib f = new Fib(8);
        int t = f.basicRec(f.number);
        int tt = f.memoization(f.number, makeFibMap());
        int ttt = f.tabulation(f.number, makeFibMap());
        System.out.println(t);
        System.out.println(tt);
        System.out.println(ttt);
    }
}
