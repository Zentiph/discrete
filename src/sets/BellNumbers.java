package sets;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Compute Bell numbers.
 *
 * @author Gavin Borne
 */
public class BellNumbers {
    private static final Map<Integer, BigInteger> memo = new HashMap<>();

    /**
     * Calculate the nth Bell number.
     * This function is faster and works better for moderate
     * n values (up to around 100) compared to {@link #bellNumberRecursive}.
     *
     * @param n - Bell number to compute
     * @return nth Bell number
     */
    public static BigInteger bellNumber(int n) {
        BigInteger[][] bell = new BigInteger[n + 1][n + 1];

        // Initialize the first bell number
        bell[0][0] = BigInteger.ONE;

        for (int i = 1; i <= n; i++) {
            // The first element in each row is
            // the last element of the previous row
            bell[i][0] = bell[i - 1][i - 1];

            for (int j = 1; j <= i; j++) {
                bell[i][j] = bell[i - 1][j - 1].add(bell[i][j - 1]);
            }
        }

        // B(n) is the first element in row n
        return bell[n][0];
    }

    /**
     * Calculate the nth Bell number using recursion and memoization.
     * This function is slower but more accurate for
     * bigger n values compared to {@link #bellNumber}.
     *
     * @param n - Bell number to compute
     * @return nth Bell number
     */
    public static BigInteger bellNumberRecursive(int n) {
        if (n == 0) {
            return BigInteger.ONE;
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        BigInteger sum = BigInteger.ZERO;
        for (int k = 0; k < n; k++) {
            sum = sum.add(binomial(n - 1, k).multiply(bellNumber(k)));
        }

        memo.put(n, sum);
        return sum;
    }

    /**
     * Calculate the binomial coefficient C(n, k).
     * Used in the recursive formula for Bell numbers.
     *
     * @param n - THe total number of items
     * @param k - The number of selected items
     * @return The binomial coefficient C(n, k)
     */
    private static BigInteger binomial(int n, int k) {
        BigInteger result = BigInteger.ONE;
        for (int i = 0; i < k; i++) {
            result = result.multiply(BigInteger.valueOf(n - i)
                .divide(BigInteger.valueOf(i + 1)));
        }
        return result;
    }
}
