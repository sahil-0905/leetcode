class Solution {

    public boolean checkDivisibility(int n) {

        int original = n;

        int sumOfdigit = 0;
        int multiOfdigit = 1;

        while (n > 0) {

            int digit = n % 10;

            sumOfdigit += digit;
            multiOfdigit *= digit;

            n = n / 10;
        }

        int totalSum = sumOfdigit + multiOfdigit;

        if (original % totalSum == 0) {
            return true;
        }

        return false;
    }
}