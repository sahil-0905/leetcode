class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        HashMap<Integer, Set<Integer>> map = new HashMap<>();
        for(int[] seat : reservedSeats){
            int row = seat[0];
            int col = seat[1];

            map.putIfAbsent(row, new HashSet<>());
            map.get(row).add(col);

        }
        int ans = (n - map.size()) * 2;
        for(Set<Integer> seats : map.values()){
            boolean left = true;
            boolean right = true;
            boolean middle = true;

            // check all 2,3,4,5
            for(int i=2; i<=5; i++){
                if(seats.contains(i)){
                    left = false;
                }
            }
            // 4,5,6,7
            for(int i=4; i<=7; i++){
                if(seats.contains(i)){
                    middle = false;
                }
            }
            // 6,7,8,9
            for(int i=6; i<=9; i++){
                if(seats.contains(i)){
                    right = false;
                }
            }
            if(right && left){
                ans += 2;
            }
             else if(right || middle || left){
                ans += 1;
            }
        }
        return ans ;
    }
}