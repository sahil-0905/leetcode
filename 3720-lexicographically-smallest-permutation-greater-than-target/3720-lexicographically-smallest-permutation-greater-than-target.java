class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < target.length(); i++) {

            int current = target.charAt(i) - 'a';

            // Same character use karne ki koshish
            if (freq[current] > 0) {

                ans.append(target.charAt(i));
                freq[current]--;

            } else {

                // Current se bada smallest character find karo
                int bigger = -1;

                for (int c = current + 1; c < 26; c++) {
                    if (freq[c] > 0) {
                        bigger = c;
                        break;
                    }
                }

                if (bigger != -1) {

                    ans.append((char) ('a' + bigger));
                    freq[bigger]--;

                    // Remaining characters smallest order mein
                    for (int c = 0; c < 26; c++) {
                        while (freq[c] > 0) {
                            ans.append((char) ('a' + c));
                            freq[c]--;
                        }
                    }

                    return ans.toString();
                }

                // Yahan direct answer nahi ban sakta.
                break;
            }
        }

        // Earlier position ko change karke answer banane ki koshish
        for (int i = target.length() - 1; i >= 0; i--) {

            int[] remaining = new int[26];

            for (char ch : s.toCharArray()) {
                remaining[ch - 'a']++;
            }

            boolean possible = true;

            for (int j = 0; j < i; j++) {
                int x = target.charAt(j) - 'a';

                if (remaining[x] == 0) {
                    possible = false;
                    break;
                }

                remaining[x]--;
            }

            if (!possible) {
                continue;
            }

            int current = target.charAt(i) - 'a';

            for (int c = current + 1; c < 26; c++) {

                if (remaining[c] > 0) {

                    StringBuilder result = new StringBuilder();

                    // Same prefix
                    for (int j = 0; j < i; j++) {
                        result.append(target.charAt(j));
                    }

                    // Current character se bada character
                    result.append((char) ('a' + c));
                    remaining[c]--;

                    // Remaining smallest order mein
                    for (int x = 0; x < 26; x++) {
                        while (remaining[x] > 0) {
                            result.append((char) ('a' + x));
                            remaining[x]--;
                        }
                    }

                    return result.toString();
                }
            }
        }

        return "";
    }
}