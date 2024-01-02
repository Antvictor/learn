package antvictor.study.design.copy;

import java.util.List;

/**
 * @author Antvictor
 * @date 2023/12/25
 **/
public class Prototype {
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


    protected Prototype toClone() {
        Prototype prototype = new Prototype();
        prototype.setAge(this.age);
        prototype.setHobbies(this.hobbies);
        prototype.setName(this.name);
        return prototype;

    }
}
