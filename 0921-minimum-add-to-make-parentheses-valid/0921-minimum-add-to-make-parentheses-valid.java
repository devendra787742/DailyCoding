class Solution {
    public int minAddToMakeValid(String s) {
        int balance = 0;
        int additions = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                balance++;
            } else {
                balance--;

                // More ')' than '('
                if (balance < 0) {
                    additions++;
                    balance = 0;
                }
            }
        }

        // Remaining '(' need ')' 
        additions += balance;

        return additions;
    }
}