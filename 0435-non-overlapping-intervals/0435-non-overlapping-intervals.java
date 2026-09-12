import java.util.*;

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {

        // Sort by ending time
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);

        int count = 0;

        // End of the last interval we kept
        int end = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {

            // Overlap
            if (intervals[i][0] < end) {
                count++;
            }

            // No overlap
            else {
                end = intervals[i][1];
            }
        }

        return count;
    }
}