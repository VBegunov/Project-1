package task2.model;

public class InnerJoinLine {
    private Integer id;
    private String value1;
    private String value2;

    public InnerJoinLine(Integer id, String value1, String value2) {
        this.id = id;
        this.value1 = value1;
        this.value2 = value2;
    }

    public Integer getId() {
        return id;
    }

    public String getValue1() {
        return value1;
    }

    public String getValue2() {
        return value2;
    }

    @Override
    public String toString() {
        return String.format("%-10.10s %-100.100s %-100.100s\n", id, value1, value2);
    }
}
