package antvictor.study.design.builder.os;

import antvictor.study.design.builder.OS;

/**
 * @author Antvictor
 * @date 2024/1/2
 **/
public class NewOS implements OS {
    @Override
    public String getVersion() {
        return "18.1.0";
    }
}
