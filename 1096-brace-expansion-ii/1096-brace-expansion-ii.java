import java.util.*;

class Solution {

    private int index = 0;

    public List<String> braceExpansionII(String expression) {

        Set<String> result = parseExpression(expression);

        List<String> answer = new ArrayList<>(result);

        Collections.sort(answer);

        return answer;
    }

    // Handles UNION: a,b,c
    private Set<String> parseExpression(String s) {

        Set<String> result = parseTerm(s);

        while (index < s.length() && s.charAt(index) == ',') {

            index++; // skip ','

            Set<String> next = parseTerm(s);

            result.addAll(next);
        }

        return result;
    }

    // Handles CONCATENATION: abc{d,e}f
    private Set<String> parseTerm(String s) {

        Set<String> result = new HashSet<>();
        result.add("");

        while (index < s.length()
                && s.charAt(index) != ','
                && s.charAt(index) != '}') {

            Set<String> next = parseFactor(s);

            Set<String> combined = new HashSet<>();

            for (String a : result) {
                for (String b : next) {
                    combined.add(a + b);
                }
            }

            result = combined;
        }

        return result;
    }

    // Handles either a letter or {...}
    private Set<String> parseFactor(String s) {

        Set<String> result = new HashSet<>();

        if (s.charAt(index) == '{') {

            index++; // skip '{'

            result = parseExpression(s);

            index++; // skip '}'

        } else {

            // Single lowercase letter
            result.add(String.valueOf(s.charAt(index)));

            index++;
        }

        return result;
    }
}