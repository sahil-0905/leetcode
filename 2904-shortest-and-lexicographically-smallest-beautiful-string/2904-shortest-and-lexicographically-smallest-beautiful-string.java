class Solution {

    public String shortestBeautifulSubstring(String s, int k) {

        int left = 0;
        int ones = 0;

        String ans = "";
        int bestLength = Integer.MAX_VALUE;

        for (int right = 0; right < s.length(); right++) {

            if (s.charAt(right) == '1') {
                ones++;
            }

            while (ones == k) {

                int currLength = right - left + 1;
                String current = s.substring(left, right + 1);

                if (currLength < bestLength ||
                    (currLength == bestLength && current.compareTo(ans) < 0)) {

                    bestLength = currLength;
                    ans = current;
                }

                if (s.charAt(left) == '1') {
                    ones--;
                }

                left++;
            }
        }

        return ans;
    }
}