package antvictor.study.entity;

public class Test2 {
    private Integer id;

    private String name;

    private String fff;

    public String getFff() {
        return fff;
    }

    public void setFff(String fff) {
        this.fff = fff;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}