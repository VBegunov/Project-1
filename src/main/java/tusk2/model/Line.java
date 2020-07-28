package tusk2.model;

public class Line {
    private Integer id;
    private String value;

    public Line(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return id + " " +value;
    }
}
