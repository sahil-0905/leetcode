class Solution {
    public int[] finalPrices(int[] prices) {

        ArrayList<Integer> ans = new ArrayList<>();

        for (int i = 0; i < prices.length; i++) {

            boolean found = false;

            for (int j = i + 1; j < prices.length; j++) {

                if (prices[j] <= prices[i]) {
                    ans.add(prices[i] - prices[j]);
                    found = true;
                    break;
                }
            }

            if (!found) {
                ans.add(prices[i]);
            }
        }

        int[] result = new int[prices.length];

        for (int i = 0; i < ans.size(); i++) {
            result[i] = ans.get(i);
        }

        return result;
    }
}