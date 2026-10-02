class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtrack(n, 0, 0, "", ans);
        return ans;
    }

    void backtrack(int n, int open, int close,
                   String curr, List<String> ans) {

        // n pairs complete
        if (curr.length() == 2 * n) {
            ans.add(curr);
            return;
        }

        // '(' add kar sakte hain
        if (open < n) {
            backtrack(n, open + 1, close,
                      curr + "(", ans);
        }

        if (close < open) {
            backtrack(n, open, close + 1,
                      curr + ")", ans);
        }
    }
}