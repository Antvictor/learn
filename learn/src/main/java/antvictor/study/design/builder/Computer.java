package antvictor.study.design.builder;

import lombok.Data;

/**
 * @author Antvictor
 * @date 2024/1/2
 **/
@Data
public class Computer {
    private OS os;
    private InternalMemory internalMemory;
    private Store store;

    @Override
    public String toString() {
        return "Computer{" +
                "os=" + os.getVersion() +
                ", internalMemory=" + internalMemory.getSize()  +
                ", store=" + store.getSize() +
                '}';
    }
}
