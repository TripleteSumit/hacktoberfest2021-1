import java.util.Arrays;

// Name:- Maximum sum of non-adjacent elements

/*
 * problem statement:
 *
 * Given an array of 'N' positive integers, we need to return the miximum sum of the subsequence such that no two elemtns of the
 * subsequence are adjacent elemtns in the array
 */
public class DP_Lecture_5 {
    static int dp[];

    public static void main(String[] args) {
        int arr[] = { 1, 2, 3, 1, 3, 5, 8, 1, 9 };
        int N = arr.length;
        dp = new int[N];
        Arrays.fill(dp, -1);
        System.out.println(spaceOptimizationApproch(arr, N - 1));
    }

    public static int normalRecursion(int cost[], int indx) {
        if (indx == 0)
            return cost[indx];
        if (indx < 0)
            return 0;
        int pick = cost[indx] + normalRecursion(cost, indx - 2);
        int unPick = 0 + normalRecursion(cost, indx - 1);
        return Math.max(pick, unPick);
    }

    public static int usingMemoization(int cost[], int indx) {
        if (indx == 0)
            return cost[indx];
        if (indx < 0)
            return 0;
        if (dp[indx] != -1)
            return dp[indx];
        int pick = cost[indx] + usingMemoization(cost, indx - 2);
        int undPick = usingMemoization(cost, indx - 1);
        return dp[indx] = Math.max(pick, undPick);
    }

    public static int usingTabulation(int cost[], int indx) {
        dp = new int[indx + 1];
        dp[0] = cost[0];
        for (int i = 1; i <= indx; i++) {
            int pick = cost[i];
            if (i > 1)
                pick += dp[i - 2];
            int unPick = 0 + dp[i - 1];
            dp[i] = Math.max(pick, unPick);
        }
        return dp[indx];
    }

    public static int spaceOptimizationApproch(int cost[], int indx) {
        int prev2 = 0, prev = cost[0];
        for (int i = 1; i <= indx; i++) {
            int pick = cost[i];
            if (i > 1)
                pick += prev2;
            int unPick = prev;
            int cur = Math.max(pick, unPick);
            prev2 = prev;
            prev = cur;
        }
        return prev;
    }
}
