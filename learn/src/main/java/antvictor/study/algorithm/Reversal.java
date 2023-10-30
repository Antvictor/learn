package antvictor.study.algorithm;

import com.alibaba.fastjson.JSONArray;

/**
 * 将数组向后移动
 * @author Antvictor
 * @date 2023/8/22
 **/
public class Reversal {
    public static void main(String[] args) {
        Reversal reversal = new Reversal();
        int[] nums = new int[]{1,2,3,4,5,6,7};
        reversal.rotate(nums, 3);
        System.out.println(JSONArray.toJSONString(nums));
    }

    public void rotate(int[] nums, int k) {
        k %= nums.length;
        //  ------>-->  to <--<------
        reversal(nums, 0, nums.length -1);
        // <--<------ to --><------
        reversal(nums, 0, k - 1);
        // --><------ to -->------>
        reversal(nums, k, nums.length - 1);
    }

    /**
     * 反转数组
     * @param nums
     * @return
     */
    public void reversal(int[] nums, int start, int end) {
        while(start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start += 1;
            end -= 1;
        }
    }
}
