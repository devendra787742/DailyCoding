class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {

        int n = nums1.length;
        int[] diff = new int[n];

        long k = (long) k1 + k2;
        long total = 0;
        int maxDiff = 0;

        // Step 1: Calculate absolute differences
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            total += diff[i];
            maxDiff = Math.max(maxDiff, diff[i]);
        }

        // Step 2: If all differences can become zero
        if (k >= total) {
            return 0;
        }

        // Step 3: Binary search for the target maximum difference
        int low = 0;
        int high = maxDiff;

        while (low < high) {
            int mid = low + (high - low) / 2;
            long needed = 0;

            for (int d : diff) {
                if (d > mid) {
                    needed += d - mid;
                }
            }

            if (needed <= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        int level = low;
        long needed = 0;
        long answer = 0;

        // Step 4: Reduce every difference to at most level
        for (int d : diff) {
            if (d > level) {
                needed += d - level;
            }

            long finalDiff = Math.min(d, level);
            answer += finalDiff * finalDiff;
        }

        // Step 5: Apply leftover operations
        long remaining = k - needed;
        answer -= remaining * (2L * level - 1);

        return answer;
    }
}