package antvictor.study;

/**
 * @author Antvictor
 * @date 2024/2/6
 **/
public class CompoundInterest {
    public static void main(String[] args) {
        int principal = 12000;
        double rate = 0.0248;
        int years = 10;
        compoundInterest(principal, rate, years);

        System.out.println("--------------------------------");
        simpleInterest(principal, rate, years);
    }

    private static void simpleInterest(int principal, double rate, int years) {
        // 本金
        double total = principal;
        // 总利息
        double interest = 0;

        for (int i = 1; i <= years; i++) {
            interest += total * rate;
            total = total + principal;
            System.out.println("第 " + i + " 年利息：" + interest);
            System.out.println("第 " + i + " 年结算金额：" + (total + interest));
        }
        interest += total * rate;
        System.out.println("第 " + 11 + " 年利息：" + interest);
        System.out.println("第 " + 11 + " 年结算金额：" + (total + interest));



    }

    /**
     * 复利
     * @param principal
     * @param rate
     * @param years
     */
    private static void compoundInterest(int principal, double rate, int years) {
        double total = 0;
        total = principal * (1 + rate);
        System.out.println("第 1 年开始金额：" + principal);
        System.out.println("第 1 年利息：" + (principal * rate));
        System.out.println("第 1 年结算金额：" + total);
        for (int i = 1; i < years; i++) {
            System.out.println("第 " + (i + 1) + " 年开始金额：" + (total  + principal));
            System.out.println("第 " + (i + 1) + " 年利息：" + ((total  + principal) * rate));
            total = (total  + principal) * (1 + rate);
            System.out.println("第 " + (i + 1) + " 年结算金额：" + total  );
        }
        System.out.println("最终总金额为：" + total);
        System.out.println("最终总金额为：" + (total * (1 + rate)));

        // 不存钱，只算利息
        for (int i = 0; i < years; i++) {
            System.out.println("第 " + (i + 11) + " 年利息：" + ((total  + principal) * rate));
            total *=  (1 + rate);
            System.out.println("第 " + (i + 11) + " 年结算金额：" + total  );
        }
    }
}
