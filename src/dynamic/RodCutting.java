package dynamic;

public class RodCutting {
    /*
    * given a rod with length N
    * given the p(i) prices for rods of length i where 1 <= i <= N
    * each cut is integer
    * what is the optimal way of cutting rod to maximize profits
    * use dynamic table to store values of previous ie smaller problems
    * just like the knapsack problem/ the answer will be in the last cell
    * and can be followed from there to arrive at all the rod lengths
    * for the table S, S(i,j) is the total value of the first i rods and
    * the total length j of those i rods;
     */
    private int rodLength;
    private int [] prices;
    private int[][] table; // dynamic programming table

    public RodCutting(int n, int[] p) {
        rodLength = n;
        prices = p;
        table = new int[prices.length + 1][rodLength + 1];
    }

    public void solve() {
        for (int i = 1; i < prices.length; i++) {
            for (int j = 0; j < rodLength + 1; j++) {
                if(i <= j) {
                    table[i][j] = Math.max(table[i-1][j], prices[i] + table[i][j-i]);
                } else {
                    table[i][j] = table[i-1][j];
                }
            }
        }
    }

    public void show() {
        System.out.println("optimal profit: $" + table[prices.length -1][rodLength]);
        for(int rIdx = prices.length-1, cIdx=rodLength; rIdx>0;) {
            if(table[rIdx][cIdx] != 0 && table[rIdx][cIdx] != table[rIdx - 1][cIdx]) {
                System.out.println("use piece: " + rIdx + "m");
                cIdx = cIdx - rIdx;
            }else {
                rIdx--;
            }
        }
    }

    public static void main(String[] args) {

        int n = 5;
        int[] prices = {0, 2, 5, 7, 3, 9};


        RodCutting  a = new RodCutting(n, prices);
        a.solve();
        a.show();

    }

}
