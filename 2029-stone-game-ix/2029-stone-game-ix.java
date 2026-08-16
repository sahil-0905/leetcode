class Solution {
    public boolean stoneGameIX(int[] stones) {
        int rem0 = 0;
        int rem1 = 0;
        int rem2 = 0;
        for(int stone : stones){
            if(stone % 3 == 0){
              rem0++;
            } else if(stone % 3 == 1){
                rem1++;
            } else {
                rem2++;
            }
        }
        if(rem0 % 2 == 0){
            if(rem1 > 0 && rem2 > 0){
                return true;
            } else {
                return false;
            }
        } else {
            if(Math.abs(rem1 - rem2) > 2){
                return true;
            } else {
                return false;
            }
        }
        
    }
}