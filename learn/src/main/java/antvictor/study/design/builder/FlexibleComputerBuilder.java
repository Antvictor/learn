package antvictor.study.design.builder;

import antvictor.study.design.builder.internal.BigInternalMemory;
import antvictor.study.design.builder.internal.MinInternalMemory;
import antvictor.study.design.builder.os.OldOS;
import antvictor.study.design.builder.store.MaxStore;
import antvictor.study.design.builder.store.MinStore;

/**
 * 用户可以选配，灵活的建造
 * @author Antvictor
 * @date 2024/1/2
 **/
public class FlexibleComputerBuilder {
    private Computer computer;

    public FlexibleComputerBuilder(){
        computer = new Computer();
    }

    public FlexibleComputerBuilder minInternalMemory() {
        computer.setInternalMemory(new MinInternalMemory());
        return this;
    }

    public FlexibleComputerBuilder bigInternalMemory() {
        computer.setInternalMemory(new BigInternalMemory());
        return this;
    }

    public FlexibleComputerBuilder minStore() {
        computer.setStore(new MinStore());
        return this;
    }

    public FlexibleComputerBuilder maxStore() {
        computer.setStore(new MaxStore());
        return this;
    }

    public FlexibleComputerBuilder oldOS() {
        computer.setOs(new OldOS());
        return this;
    }

    public FlexibleComputerBuilder newOs() {
        computer.setOs(() -> "19.0.1");
        return this;
    }

    public Computer build(){
        return computer;
    }
}
