package antvictor.study.algorithm;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author Antvictor
 * @date 2023/5/23
 **/
public class MaxRich {
    public static void main(String[] args) {
        int[][] rich = new int[][]{{1,2,3},{2,3,1}};
        maxRich(rich);
    }

    private static int maxRich(@NotNull int[][] rich) {
        StringBuilder sb = new StringBuilder();
//        sb.append()
        System.out.println(Arrays.stream(rich).map(Arrays::stream).mapToInt(IntStream::sum).reduce(0, Math::max));
        System.out.println(Arrays.stream(rich).mapToInt(i-> Arrays.stream(i).sum()).max().orElse(0));
        return Arrays.stream(rich).mapToInt(i-> Arrays.stream(i).sum()).max().orElse(0);
    }
}
