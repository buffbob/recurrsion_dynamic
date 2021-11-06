package dynamic;

import java.util.Arrays;

public class Knapsack {
    //
    private int numOfItems;
    private int capacityOfKnapsack;
    private int[][] knapsackTable;
    private int totalBenefit;
    private int[] weights;
    private int[] values;

    public Knapsack(int numOfItems, int capacityOfKnapsack, int[] weights, int[] values) {
        this.numOfItems = numOfItems;
        this.capacityOfKnapsack = capacityOfKnapsack;
        this.weights = weights;
        this.values = values;
        this.knapsackTable = new int[numOfItems+1][capacityOfKnapsack+1];
    }

    public void solve() {

        // time complexity: O(N*W)
        for(int i=1;i<numOfItems+1;i++) {
            for(int w=1;w<capacityOfKnapsack+1;w++) {

                int notTakingItem = knapsackTable[i-1][w]; // not taking item i
                int takingItem = 0;

                if( weights[i] <= w ) {
                    takingItem = values[i] + knapsackTable[i-1][w-weights[i]];
                }

                knapsackTable[i][w] = Math.max(notTakingItem, takingItem);
            }
        }

        totalBenefit = knapsackTable[numOfItems][capacityOfKnapsack];
    }
    public int solveRecursion(int m, int[] w, int[] v, int n) {

        // base cases
        if(m==0 || n==0)
            return 0;

        // the given item can NOT fit into the knapsack
        if(w[n-1] > m) {
            return solveRecursion(m, w, v, n-1);
        } else {
            // given item can fit into the knapsack so we have 2 options (include, exclude)
            int included = v[n-1] + solveRecursion(m - w[n - 1], w, v, n-1);
            int excluded = solveRecursion(m, w, v, n-1);
            return Math.max(included, excluded);
        }
    }

    public void showResult() {

        System.out.println("Total benefit: " + totalBenefit);

        for(int n=numOfItems, w = capacityOfKnapsack;n>0;n--) {
            if( knapsackTable[n][w] != 0 && knapsackTable[n][w] != knapsackTable[n-1][w] ) {
                System.out.println("We take item: #"+n);
                w = w - weights[n];
            }
        }
    }
    public static void main(String[] args) {
        int numOfItems = 3;
        int capacityOfKnapsack = 5;

        // int[] weightOfItems = {4,2,3};
        // int[] profitOfItems = {10,4,7};

        int[] weightOfItems = new int[numOfItems + 1];
        weightOfItems[1] = 4;
        weightOfItems[2] = 2;
        weightOfItems[3] = 3;

        int[] profitOfItems = new int[numOfItems + 1];
        profitOfItems[1] = 10;
        profitOfItems[2] = 4;
        profitOfItems[3] = 7;

        Knapsack knapsack = new Knapsack(numOfItems, capacityOfKnapsack, weightOfItems, profitOfItems);
//        knapsack.solve();
//        knapsack.showResult();

        System.out.println("------------");
// here remove 0s
        int [] wts = Arrays.copyOfRange(weightOfItems, 1, 4);
        int [] values = Arrays.copyOfRange(profitOfItems, 1, 4);


         knapsack.solveRecursion(capacityOfKnapsack,wts,values, numOfItems);
         knapsack.showResult();

    }
}
