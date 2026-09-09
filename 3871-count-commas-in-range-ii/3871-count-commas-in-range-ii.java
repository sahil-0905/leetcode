class Solution {
    public long countCommas(long n) {
        long ans = 0;

        for (long x = 1000L; x <= n; ) {
            ans += n - x + 1;

            if (x > n / 1000) {
                break;
            }

            x *= 1000;
        }

        return ans;
    }
}