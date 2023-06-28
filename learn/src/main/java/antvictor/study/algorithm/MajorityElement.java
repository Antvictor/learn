package antvictor.study.algorithm;

/**
 * @author Antvictor
 * @date 2023/6/7
 **/
public class MajorityElement {
    public static void main(String[] args) {
//        System.out.println(majorityElement(new int[]{1, 1, 2, 2,2, 1,1,3}));
        System.out.println(12%6);
        System.out.println(2/6);
    }

    public static int majorityElement(int[] nums) {
        int ret = nums[0];

        int count = 1;
        for(int num : nums) {
            if(num != ret) {
                count--;
                if(count == 0) {
                    count = 1;
                    ret = num;
                }
            }
            else
                count++;
        }
        return ret;
    }

}
