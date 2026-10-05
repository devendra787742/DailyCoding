import java.util.*;

class Solution {
    public int scoreOfParentheses(String s) {

        Stack<Integer> stack = new Stack<>();

       
        stack.push(0);

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
               
                stack.push(0);

            } else {
               
                int inside = stack.pop();

               
                int score;

                if (inside == 0) {
                    
                    score = 1;
                } else {
                   
                    score = 2 * inside;
                }

               
                int outer = stack.pop();
                stack.push(outer + score);
            }
        }

        return stack.pop();
    }
}