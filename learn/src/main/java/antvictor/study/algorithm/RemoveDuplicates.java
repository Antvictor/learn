package antvictor.study.algorithm;

/**
 * @author Antvictor
 * @date 2023/6/2
 **/
public class RemoveDuplicates {
    public static void main(String[] args) {
        int[] nums = new int[]{1,1,2}; // 输入数组
        int[] expectedNums = new int[]{1,2}; // 长度正确的期望答案

        int k = removeDuplicates(nums); // 调用

        assert k == expectedNums.length;
        for (int i = 0; i < k; i++) {
            assert nums[i] == expectedNums[i];
        }
    }
    public static int removeDuplicates(int[] nums) {
        int slow = 0;
        int fast = 1;
        while (fast < nums.length) {
            if (nums[slow] == nums[fast]) {
                fast++;
            } else {
                nums[++slow] = nums[fast++];
            }
        }
        return slow;
    }
}
