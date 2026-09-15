class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int count = 0;
        int i = 0;

        while (i < n) {
            boolean found = false;

            // Check palindrome of length k
            if (i + k <= n && isPalindrome(s, i, i + k - 1)) {
                count++;
                i = i + k;
                found = true;
            }
            // Check palindrome of length k + 1
            else if (i + k + 1 <= n && isPalindrome(s, i, i + k)) {
                count++;
                i = i + k + 1;
                found = true;
            }

            if (!found) {
                i++;
            }
        }

        return count;
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}