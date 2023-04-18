package antvictor.study.entity;

import java.io.Serializable;

/**
 * @author exccedy
 * @date 2022/1/25
 **/
public class Student implements Serializable {
    private String firstName;
    private String lastName;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
