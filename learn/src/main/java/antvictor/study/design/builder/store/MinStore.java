package antvictor.study.design.builder.store;

import antvictor.study.design.builder.Store;

/**
 * @author Antvictor
 * @date 2024/1/2
 **/
public class MinStore implements Store {
    @Override
    public String getSize() {
        return "128G";
    }
}
