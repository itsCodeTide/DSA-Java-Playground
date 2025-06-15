import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeOverlapping {
    
    public static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) return intervals;
        
        // Step 1: Sort intervals by start time.
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        List<int[]> merged = new ArrayList<>();
        // Add the first interval.
        merged.add(intervals[0]);
        
        // Step 2: Loop through intervals and merge if necessary.
        for (int i = 1; i < intervals.length; i++) {
            int[] lastMerged = merged.get(merged.size() - 1);
            int[] current = intervals[i];
            
            // Check for overlap.
            if (current[0] <= lastMerged[1]) {
                // Merge the intervals.
                lastMerged[1] = Math.max(lastMerged[1], current[1]);
            } else {
                merged.add(current);
            }
        }
        
        return merged.toArray(new int[merged.size()][]);
    }
    
    public static void main(String[] args) {
        int[][] intervals = { {1, 3}, {2, 6}, {8, 10}, {15, 18} };
        int[][] result = merge(intervals);
        System.out.println("Merged intervals:");
        for (int[] interval : result) {
            System.out.println(Arrays.toString(interval));
        }
    }
}


