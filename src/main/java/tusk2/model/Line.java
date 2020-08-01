package tusk2.model;

public class Line {
    private int id;
    private String value;

    public Line(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
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
