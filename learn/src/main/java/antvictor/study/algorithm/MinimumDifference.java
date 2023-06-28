package antvictor.study.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Antvictor
 * @date 2023/5/11
 **/
public class MinimumDifference {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> numbers = new ArrayList<>();

        while (scanner.hasNextInt()) {
            numbers.add(scanner.nextInt());
        }

        Integer[] array = numbers.toArray(new Integer[]{});
        /*Integer[] array = new Integer[]{1,3,4,5,6,1,2,3,6,10,20};
        sort(array);
        System.out.println(Arrays.toString(array));*/
        sort(array);
        Integer[] copys = new Integer[array.length  - n];
        System.arraycopy(array, 0, copys, 0, array.length - n);
        System.out.println(copys[copys.length - 1] - copys[0]);
    }

    private static void sort(Integer[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
}
