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

// roman to int
class Solution {
     public int romanToInt(String s) {
         int ans = 0, num = 0;
        for (int i = s.length()-1; i >= 0; i--) {
            switch(s.charAt(i)) {
                case 'I': num = 1; break;
                case 'V': num = 5; break;
                case 'X': num = 10; break;
                case 'L': num = 50; break;
                case 'C': num = 100; break;
                case 'D': num = 500; break;
                case 'M': num = 1000; break;
            }
            if (4 * num < ans) ans -= num;
            else ans += num;
        }
        return ans;
    }
}
