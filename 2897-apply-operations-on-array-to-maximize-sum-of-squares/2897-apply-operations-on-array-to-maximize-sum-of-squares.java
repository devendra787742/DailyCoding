import java.util.*;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        final long MOD = 1_000_000_007L;
        int[] bitCount = new int[31];

        // Count how many numbers contain each set bit
        for (int num : nums) {
            for (int bit = 0; bit < 31; bit++) {
                if ((num & (1 << bit)) != 0) {
                    bitCount[bit]++;
                }
            }
        }

        long answer = 0;

        // Construct k maximum numbers greedily
        for (int i = 0; i < k; i++) {
            long value = 0;

            for (int bit = 30; bit >= 0; bit--) {
                if (bitCount[bit] > 0) {
                    value |= (1L << bit);
                    bitCount[bit]--;
                }
            }

            answer = (answer + value * value) % MOD;
        }

        return (int) answer;
    }
}