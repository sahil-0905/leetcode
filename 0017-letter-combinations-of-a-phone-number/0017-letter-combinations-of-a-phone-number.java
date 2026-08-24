class Solution {

    String[] keypad = {
        "", "", "abc", "def", "ghi",
        "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    List<String> result = new ArrayList<>();

    public List<String> letterCombinations(String digits) {

        if (digits.length() == 0) {
            return result;
        }

        backtrack(digits, 0, "");

        return result;
    }

    public void backtrack(String digits, int index, String current) {

        if (index == digits.length()) {
            result.add(current);
            return;
        }

        int digit = digits.charAt(index) - '0';

        String letters = keypad[digit];

        for (int i = 0; i < letters.length(); i++) {

            backtrack(
                digits,
                index + 1,
                current + letters.charAt(i)
            );
        }
    }
}