class Solution {

    public String lexPalindromicPermutation(String s, String target) {

        int n = s.length();

        int[] freq = new int[26];

        // Count characters
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        // More than one odd frequency => palindrome impossible
        int oddCount = 0;
        int middle = -1;

        for (int i = 0; i < 26; i++) {
            if ((freq[i] & 1) == 1) {
                oddCount++;
                middle = i;
            }
        }

        if (oddCount > 1) {
            return "";
        }

        // Keep only characters needed for left half
        for (int i = 0; i < 26; i++) {
            freq[i] /= 2;
        }

        int half = n / 2;

        char[] ans = new char[n];

        /*
         * Build left half equal to target
         * as long as possible.
         */
        int pos = 0;

        while (pos < half) {

            int c = target.charAt(pos) - 'a';

            if (freq[c] == 0) {
                break;
            }

            ans[pos] = target.charAt(pos);
            freq[c]--;

            pos++;
        }

        /*
         * If we matched the entire left half,
         * construct the palindrome and check it.
         */
        if (pos == half) {

            buildPalindrome(ans, half, middle);

            String result = new String(ans);

            if (result.compareTo(target) > 0) {
                return result;
            }
        }

        /*
         * Backtrack.
         *
         * At current position, try the smallest
         * character greater than target[pos].
         */
        while (true) {

            if (pos < half) {

                int start = target.charAt(pos) - 'a' + 1;

                // Find smallest available character
                // greater than target[pos]
                for (int c = start; c < 26; c++) {

                    if (freq[c] > 0) {

                        ans[pos] = (char) ('a' + c);
                        freq[c]--;

                        /*
                         * Fill remaining left-half positions
                         * with smallest possible characters.
                         */
                        int index = pos + 1;

                        for (int ch = 0; ch < 26; ch++) {

                            while (freq[ch] > 0) {
                                ans[index++] = (char) ('a' + ch);
                                freq[ch]--;
                            }
                        }

                        // Complete palindrome
                        buildPalindrome(ans, half, middle);

                        return new String(ans);
                    }
                }
            }

            /*
             * No greater character possible here.
             *
             * Move one position back and restore
             * the character we used from target.
             */
            if (pos == 0) {
                return "";
            }

            pos--;

            int c = target.charAt(pos) - 'a';
            freq[c]++;
        }
    }

    private void buildPalindrome(char[] ans, int half, int middle) {

        // Middle character
        if (middle != -1) {
            ans[half] = (char) ('a' + middle);
        }

        // Mirror left half
        for (int i = 0; i < half; i++) {
            ans[ans.length - 1 - i] = ans[i];
        }
    }
}