import java.util.*;

class Solution {
    public int longestValidParentheses(String s) {

        Stack<Integer> stack = new Stack<>();

        // Index before the start of the current valid substring
        stack.push(-1);

        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {

                // Store index of '('
                stack.push(i);

            } else {

                // Match this ')' with the latest '('
                stack.pop();

                if (stack.isEmpty()) {

                    // This ')' cannot be matched
                    stack.push(i);

                } else {

                    // Length of current valid substring
                    int length = i - stack.peek();

                    maxLength = Math.max(maxLength, length);
                }
            }
        }

        return maxLength;
    }
}