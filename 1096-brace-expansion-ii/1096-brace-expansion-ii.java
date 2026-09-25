

class Solution {

    int index = 0;

    public List<String> braceExpansionII(String expression) {

        Set<String> result = parse(expression);

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    private Set<String> parse(String s) {

        Set<String> result = new HashSet<>();
        result.add("");

        while (index < s.length() && s.charAt(index) != '}') {

            char ch = s.charAt(index);

            if (ch == '{') {

                index++; // skip {

                Set<String> inside = parse(s);

                index++; // skip }

                result = multiply(result, inside);
            }

            else if (ch == ',') {

                index++;

                Set<String> next = parse(s);

                result.addAll(next);

                break;
            }

            else {

                index++;

                Set<String> letter = new HashSet<>();
                letter.add(String.valueOf(ch));

                result = multiply(result, letter);
            }
        }

        return result;
    }

    private Set<String> multiply(Set<String> a, Set<String> b) {

        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}