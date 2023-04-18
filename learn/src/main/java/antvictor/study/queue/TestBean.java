package antvictor.study.queue;

import java.util.ArrayList;
import java.util.List;

public class TestBean {
    List<String> keys = new ArrayList<>();

    public List<String> getKey() {
        return keys;
    }

    public void setKey(List<String> keys) {
        this.keys = keys;
    }

    public void addKey(String key) {
       keys.add(key);
    }
}
