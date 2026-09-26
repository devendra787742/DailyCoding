import java.util.*;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {

        // Store key -> value
        HashMap<String, String> map = new HashMap<>();

        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder result = new StringBuilder();

        int i = 0;

        while (i < s.length()) {

            // Normal character
            if (s.charAt(i) != '(') {
                result.append(s.charAt(i));
                i++;
            }

            // Bracket pair
            else {

                int j = i + 1;

                // Find closing bracket
                while (s.charAt(j) != ')') {
                    j++;
                }

                // Extract key
                String key = s.substring(i + 1, j);

                // Get value
                if (map.containsKey(key)) {
                    result.append(map.get(key));
                } else {
                    result.append('?');
                }

                // Move after ')'
                i = j + 1;
            }
        }

        return result.toString();
    }
}