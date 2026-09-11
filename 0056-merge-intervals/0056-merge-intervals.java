class Solution {
    public int[][] merge(int[][] intervals) {

        // Sort according to starting point
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> result = new ArrayList<>();

        int start = intervals[0][0];
        int end = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {

            // Overlap
            if (intervals[i][0] <= end) {
                end = Math.max(end, intervals[i][1]);
            }

            // No overlap
            else {
                result.add(new int[]{start, end});

                start = intervals[i][0];
                end = intervals[i][1];
            }
        }

        // Add the last interval
        result.add(new int[]{start, end});

        return result.toArray(new int[result.size()][]);
    }
}