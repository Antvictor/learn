package antvictor.study.design.builder;

import antvictor.study.design.builder.internal.BigInternalMemory;
import antvictor.study.design.builder.internal.MinInternalMemory;
import antvictor.study.design.builder.os.NewOS;
import antvictor.study.design.builder.os.OldOS;
import antvictor.study.design.builder.store.MaxStore;
import antvictor.study.design.builder.store.MinStore;

/**
 * 出厂时直接生产好的，用户只能选择固定的配置
 * @author Antvictor
 * @date 2024/1/2
 **/
public class ComputerBuilder {
    public Computer buildMinComputer() {
        Computer computer = new Computer();
        computer.setOs(new OldOS());
        computer.setInternalMemory(new MinInternalMemory());
        computer.setStore(new MinStore());
        return computer;
    }

    public Computer buildProComputer() {
        Computer computer = new Computer();
        computer.setOs(new NewOS());
        computer.setInternalMemory(new BigInternalMemory());
        computer.setStore(new MaxStore());
        return computer;
    }

}
