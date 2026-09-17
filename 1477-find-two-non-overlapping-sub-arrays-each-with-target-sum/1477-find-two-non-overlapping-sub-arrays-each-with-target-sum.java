class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int INF = 1_000_000_000;

        // best[i] = minimum length of a valid subarray
        // completely inside arr[0 ... i-1]
        int[] best = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            best[i] = INF;
        }

        int ans = INF;
        int left = 0;
        int sum = 0;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            while (sum > target && left <= right) {
                sum -= arr[left++];
            }

            // Carry previous best
            best[right + 1] = best[right];

            if (sum == target) {
                int len = right - left + 1;

                // Previous non-overlapping subarray
                if (best[left] != INF) {
                    ans = Math.min(ans, len + best[left]);
                }

                // Store current subarray as best
                best[right + 1] = Math.min(best[right + 1], len);
            }
        }

        return ans == INF ? -1 : ans;
    }
}