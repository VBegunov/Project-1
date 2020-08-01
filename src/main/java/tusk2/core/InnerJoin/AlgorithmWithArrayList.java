package tusk2.core.InnerJoin;

import tusk2.model.InnerJoinLine;
import tusk2.model.Line;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AlgorithmWithArrayList implements Comparator<InnerJoinLine> {
    private List<Line> list1 = new ArrayList<>();
    private List<Line> list2 = new ArrayList<>();

    public AlgorithmWithArrayList(List<Line> list1,List<Line> list2) {
        this.list1.addAll(list1);
        this.list2.addAll(list2);
    }

    public StringBuilder getInnerJoin() {
        StringBuilder result = new StringBuilder(String.format("%-10.10s %-100.100s %-100.100s\n", "ID", "A.VALUE", "B.VALUE"));
        for(InnerJoinLine line: innerJoin()){
            result.append(line.toString());
        }
        return result;
    }

    private List<InnerJoinLine> innerJoin() {
        List<InnerJoinLine> result = new ArrayList<>();
        for (Line lineLeft: list1) {
            for (Line lineRight: list2) {
                if (lineLeft.getId() == lineRight.getId()) {
                    result.add(new InnerJoinLine(lineLeft.getId(), lineLeft.getValue(), lineRight.getValue()));
                }
            }
        }
        result.sort(this);
        return result;
    }

    @Override
    public int compare(InnerJoinLine a, InnerJoinLine b) {
        return a.getId().compareTo(b.getId());
    }
}
