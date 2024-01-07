package antvictor.study.design.builder.internal;

import antvictor.study.design.builder.InternalMemory;

/**
 * @author Antvictor
 * @date 2024/1/2
 **/
public class BigInternalMemory implements InternalMemory {

    @Override
    public String getSize() {
        return "32GB";
    }
}
