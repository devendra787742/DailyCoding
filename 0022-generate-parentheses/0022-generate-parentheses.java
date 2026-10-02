import java.util.*;

class Solution {
    public List<String> generateParenthesis(int n) {

        List<String> result = new ArrayList<>();

        backtrack("", 0, 0, n, result);

        return result;
    }

    private void backtrack(String current, int open, int close,
                           int n, List<String> result) {

        // We used all parentheses
        if (current.length() == 2 * n) {
            result.add(current);
            return;
        }

        // We can add '(' if we still have some left
        if (open < n) {
            backtrack(current + "(", open + 1, close, n, result);
        }

        // We can add ')' only if it won't make the string invalid
        if (close < open) {
            backtrack(current + ")", open, close + 1, n, result);
        }
    }
}