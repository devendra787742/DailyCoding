import java.util.*;

class Solution {

    public List<String> maxNumOfSubstrings(String s) {

        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, -1);

        // Find first and last occurrence
        for (int i = 0; i < n; i++) {

            int c = s.charAt(i) - 'a';

            if (first[c] == -1) {
                first[c] = i;
            }

            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Create valid intervals
        for (int c = 0; c < 26; c++) {

            if (first[c] == -1) {
                continue;
            }

            int start = first[c];
            int end = last[c];

            boolean valid = true;

            for (int i = start; i <= end; i++) {

                int current = s.charAt(i) - 'a';

                // This character started before our interval
                if (first[current] < start) {
                    valid = false;
                    break;
                }

                // Need to include all occurrences
                end = Math.max(end, last[current]);
            }

            if (valid) {
                intervals.add(new int[]{start, end});
            }
        }

        // Sort by ending position
        intervals.sort((a, b) -> {
            if (a[1] == b[1]) {
                return (a[1] - a[0]) - (b[1] - b[0]);
            }
            return a[1] - b[1];
        });

        List<String> result = new ArrayList<>();

        int previousEnd = -1;

        // Greedily choose non-overlapping intervals
        for (int[] interval : intervals) {

            if (interval[0] > previousEnd) {

                result.add(s.substring(interval[0], interval[1] + 1));

                previousEnd = interval[1];
            }
        }

        return result;
    }
}