package antvictor.study.algorithm;

/**
 * @author Antvictor
 * @date 2023/8/31
 **/
public class CanJump {
    public static void main(String[] args) {
        int[] nums = new int[]{3,2,1,0,4};
        System.out.println(canJump(nums));
    }
    public static boolean canJump(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for(int i = 0; i < n; ++i){
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, nums[i] + i);
                if (rightmost >= n - 1){
                    return true;
                }
            }
        }
        return false;
    }
}
