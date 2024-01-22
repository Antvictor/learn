package antvictor.study.design.copy.deep;

import java.io.*;
import java.util.List;
import java.util.Objects;

/**
 * @author Antvictor
 * @date 2023/12/25
 **/
public class Prototype implements Cloneable, Serializable {
    private String name;
    private Long age;
    private List<String> hobbies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public Prototype clone()  {
        return deepClone();
    }

    /**
     * 深度克隆
     * 使用ByteArrayOutputStream和ByteArrayInputStream 在内存中进行序列化和反序列化
     * @return
     */
    private Prototype deepClone() {
        /*ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            return (Prototype) ois.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }*/
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (
        ObjectOutputStream oos = new ObjectOutputStream(bos)){
            oos.writeObject(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try(ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);) {
            return (Prototype) ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
