class Solution {
    public int distinctSubseqII(String s) {

        int MOD = 1000000007;

        long dp = 1; 

        HashMap<Character, Long> last = new HashMap<>();

        for (char ch : s.toCharArray()) {

            long oldDp = dp;

            dp = (2 * dp) % MOD;

            if (last.containsKey(ch)) {
                dp = (dp - last.get(ch) + MOD) % MOD;
            }

            last.put(ch, oldDp);
        }

      
        return (int) ((dp - 1 + MOD) % MOD);
    }
}