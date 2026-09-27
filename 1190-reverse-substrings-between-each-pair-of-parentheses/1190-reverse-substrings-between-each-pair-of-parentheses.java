import java.util.*;

class Solution {
    public String reverseParentheses(String s) {
        Stack<String> stack = new Stack<>();
        StringBuilder current = new StringBuilder();

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                // Save current string
                stack.push(current.toString());

                // Start a new string
                current = new StringBuilder();

            } else if (ch == ')') {
                // Reverse current substring
                current.reverse();

                // Get the string before '('
                String previous = stack.pop();

                // Combine them
                current = new StringBuilder(previous + current);

            } else {
                // Normal character
                current.append(ch);
            }
        }

        return current.toString();
    }
}