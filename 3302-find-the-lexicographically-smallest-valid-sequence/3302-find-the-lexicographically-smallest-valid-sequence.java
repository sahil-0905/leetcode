class Solution {
    public int[] validSequence(String w1, String w2) {

        int n = w1.length();
        int m = w2.length();

        // suffix[i] = w1[i...] me
        // w2 ke kitne characters subsequence ke form me match ho sakte hain
        int[] suffix = new int[n + 1];

        int j = m - 1;

        // Right se suffix calculate
        for (int i = n - 1; i >= 0; i--) {

            if (j >= 0 && w1.charAt(i) == w2.charAt(j)) {

                suffix[i] = suffix[i + 1] + 1;
                j--;

            } else {

                suffix[i] = suffix[i + 1];
            }
        }

        // Answer me w2 ke m characters ke indices honge
        int[] ans = new int[m];

        int i = 0;
        j = 0;

        // Left se greedy matching
        while (i < n && j < m) {

            // Normal match
            if (w1.charAt(i) == w2.charAt(j)) {

                ans[j] = i;
                j++;
                i++;

            } else {

                // Current character ko change kar sakte hain
                int remaining = m - 1 - j;

                // Check karo ki current index ke baad
                // remaining characters mil jayenge ya nahi
                if (suffix[i + 1] >= remaining) {

                    ans[j] = i;
                    j++;

                    // One change use ho gaya
                    i++;

                    break;
                }

                // Current character skip
                i++;
            }
        }

        // Change use hone ke baad
        // remaining characters normal match karo
        while (i < n && j < m) {

            if (w1.charAt(i) == w2.charAt(j)) {

                ans[j] = i;
                j++;
            }

            i++;
        }

        // Agar pura w2 match nahi hua
        if (j < m) {
            return new int[0];
        }

        return ans;
    }
}