// sum two
class Solution {
    public int[] twoSum(int[] nums, int target) {

        int out[] = new int[2];
        int j=1;
        while(j<nums.length) {
            for(int i = 0 ; i+j<nums.length ; i++) {
                if(nums[i] + nums[i+j]==target ) {
                    out[0] = i;
                    out[1] = i+j;
                    return out;
                }
            }
            j++;
        }
        return out;
    }
}

// reverse int
class Solution {
    public boolean isPalindrome(int x) {
   int temp = x; 
		   long sum = 0;
		   while(x>0) {
			   sum = (sum*10) +  x%10; 
			   x = x /10;  
		   }  
		   return temp==sum; 
	    }
}
