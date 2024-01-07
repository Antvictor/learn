package antvictor.study.design.builder.os;

import antvictor.study.design.builder.OS;

/**
 * @author Antvictor
 * @date 2024/1/2
 **/
public class OldOS implements OS {
    @Override
    public String getVersion() {
        return "16.1.0";
    }
}
