package antvictor.study.algorithm;

/**
 * 给出的数组必定能跳跃到最后一位，计算跳跃到最后一位的最少次数
 * @author Antvictor
 * @date 2023/9/1
 **/
public class Jump2 {
    public static void main(String[] args) {
        System.out.println(jump(new int[]{1,2,1,1,1}));
        System.out.println(jump(new int[]{2,3,1,3,2,2,6}));
    }

    public static int jump(int[] nums) {
        int n = nums.length; // 数组长度
        int step = 0; // 步数
        int maxRight = 0; // 目前能跳到的最大右边界
        int end = 0; // 上次能跳到的最大右边界

        for (int i = 0; i < n - 1; ++i) {
            // 计算本次可跳跃范围中，能跳的最远的
            maxRight = Math.max(maxRight, i + nums[i]);
            // 到达上次能跳跃的边界
            if (i == end) {
                // 更新为上次跳跃范围内能达到的最远距离
                end = maxRight;
                // 进行跳跃
                step++;
            }
        }

        return step;
    }
}
