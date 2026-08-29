class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[][] arr = new int[n][2];
        // 2d array mai value and original index store hue hai
        for(int i=0; i<n; i++){
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }
        // value ke according sort hue hai 
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        int i=0;
        while(i < n){
            int j = i;
            // first gropu 
            while(j + 1< n && arr[j+1][0] - arr[j][0] <= limit ){
                j++;
            }
            // store original index and value
            List<Integer> index = new ArrayList<>();
            List<Integer> value = new ArrayList<>();

            for(int k = i; k<=j; k++){
                index.add(arr[k][1]);
                value.add(arr[k][0]);
            }
            Collections.sort(index);

            for (int k = 0; k < index.size(); k++) {
                nums[index.get(k)] = value.get(k);
            }
            i = j+1;
        }
        return nums;
    }
}