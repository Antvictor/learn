package antvictor.study.map;

import lombok.Data;

import java.util.Objects;

/**
 * @author exccedy
 * @date 2023/4/3
 **/
@Data
public class Test3 {
    private String name;
    private int age;
    private int clazz;

    public Test3() {

    }

    public Test3(String name, int age, int clazz) {
        this.name = name;
        this.age = age;
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", clazz=" + clazz +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getClazz() {
        return clazz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test3 test3 = (Test3) o;
        return clazz == test3.clazz;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clazz);
    }
}
