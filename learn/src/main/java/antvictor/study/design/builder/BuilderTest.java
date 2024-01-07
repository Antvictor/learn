package antvictor.study.design.builder;

/**
 * @author Antvictor
 * @date 2024/1/2
 **/
public class BuilderTest {
    public static void main(String[] args) {
        // 固定生产
        ComputerBuilder computerBuilder = new ComputerBuilder();
        System.out.println(computerBuilder.buildMinComputer());
        System.out.println(computerBuilder.buildProComputer());
        // output:
        //Computer{os=16.1.0, internalMemory=8GB, store=128G}
        //Computer{os=18.1.0, internalMemory=32GB, store=1T}

        // 灵活装配
        Computer computer = new FlexibleComputerBuilder().maxStore()
                .newOs()
                .bigInternalMemory()
                .build();
        // Computer{os=19.0.1, internalMemory=32GB, store=1T}
        System.out.println(computer);
    }
}
