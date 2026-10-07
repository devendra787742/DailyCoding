import java.util.*;

class Solution {

    List<String> result = new ArrayList<>();

    public List<String> removeInvalidParentheses(String s) {

        int leftRemove = 0;
        int rightRemove = 0;

        // Count minimum removals needed
        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                leftRemove++;
            }
            else if (ch == ')') {

                if (leftRemove > 0) {
                    leftRemove--;
                }
                else {
                    rightRemove++;
                }
            }
        }

        backtrack(s, 0, leftRemove, rightRemove);

        return result;
    }

    private void backtrack(String s, int start,
                            int leftRemove,
                            int rightRemove) {

        // Remove '('
        for (int i = start; i < s.length(); i++) {

            // Avoid generating duplicate strings
            if (i > start && s.charAt(i) == s.charAt(i - 1)) {
                continue;
            }

            // Try removing '('
            if (leftRemove > 0 && s.charAt(i) == '(') {

                String newString =
                    s.substring(0, i) + s.substring(i + 1);

                backtrack(
                    newString,
                    i,
                    leftRemove - 1,
                    rightRemove
                );
            }

            // Try removing ')'
            if (rightRemove > 0 && s.charAt(i) == ')') {

                String newString =
                    s.substring(0, i) + s.substring(i + 1);

                backtrack(
                    newString,
                    i,
                    leftRemove,
                    rightRemove - 1
                );
            }
        }

        // No more removals required
        if (leftRemove == 0 && rightRemove == 0) {

            if (isValid(s)) {
                result.add(s);
            }
        }
    }

    private boolean isValid(String s) {

        int balance = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                balance++;
            }
            else if (ch == ')') {
                balance--;

                if (balance < 0) {
                    return false;
                }
            }
        }

        return balance == 0;
    }
}