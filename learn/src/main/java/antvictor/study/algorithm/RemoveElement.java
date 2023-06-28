package antvictor.study.algorithm;

/**
 * @author Antvictor
 * @date 2023/6/1
 **/
public class RemoveElement {
    public static void main(String[] args) {
        int[] t = new int[]{2};
        int i = removeElement(t, 3);
        for (int j = 0; j < i; j++) {
            System.out.println(t[j]);
        }

    }
    public static int removeElement(int[] nums, int val) {
        int k = 0;
        for (int i = 0; i < nums.length ; i++) {
            if (nums[i] != val) {
                nums[k++] = nums[i];
            }
        }
        return k;
    }
}
