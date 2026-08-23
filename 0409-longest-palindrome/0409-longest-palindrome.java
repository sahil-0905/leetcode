class Solution {
    public int longestPalindrome(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
       for(int i = 0; i < s.length(); i++) {

            map.put(
                s.charAt(i),
                map.getOrDefault(s.charAt(i), 0) + 1
            );
        }
        int ans = 0;
        boolean odd = false;

        for(int freq : map.values()) {

            if(freq % 2 == 0) {
                ans += freq;
            } else {
                ans += freq - 1;
                odd = true;
            }
        }

        if(odd) {
            ans++;
        }

        return ans;
    }
}