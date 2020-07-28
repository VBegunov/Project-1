package tusk2.core.InnerJoin;

import tusk2.model.InnerJoinLine;
import tusk2.model.Line;

import java.util.*;

public class AlgorithmWithLinkedList implements Comparator<Line> {
    private List<Line> list1 = new LinkedList<>();
    private List<Line> list2 = new LinkedList<>();

    public AlgorithmWithLinkedList(List<Line> list1, List<Line> list2) {
        list1.sort(this);
        list2.sort(this);
        this.list1.addAll(list1);
        this.list2.addAll(list2);
    }

    public StringBuilder getInnerJoin() {
        StringBuilder result = new StringBuilder(String.format("%-10.10s %-100.100s %-100.100s\n", "ID", "A.VALUE", "B.VALUE"));
        for (InnerJoinLine line : innerJoin()) {
            result.append(line.toString());
        }
        return result;
    }

    private List<InnerJoinLine> innerJoin() {
        List<InnerJoinLine> result = new ArrayList<>();
        Iterator<Line> iterator1 = list1.listIterator();
        Iterator<Line> iterator2 = list2.listIterator();
        Line line1 = iterator1.next();
        Line line2 = iterator2.next();

        while (true) {
            int compare = line1.getId().compareTo(line2.getId());
            if (compare < 0) {
                if (iterator1.hasNext()) {
                    line1 = iterator1.next();
                } else {
                    break;
                }
            } else if (compare > 0) {
                if (iterator2.hasNext()) {
                    line2 = iterator2.next();
                } else {
                    break;
                }
            } else {
                result.add(new InnerJoinLine(line1.getId(), line1.getValue(), line2.getValue()));
                while (iterator2.hasNext()) {
                    line2 = iterator2.next();
                    if (line1.getId().equals(line2.getId())) {
                        result.add(new InnerJoinLine(line1.getId(), line1.getValue(), line2.getValue()));
                    } else {
                        iterator2 = list2.listIterator();
                        line2 = iterator2.next();
                        line1 = iterator1.next();
                        break;
                    }
                }
            }
        }
        return result;
    }


    @Override
    public int compare(Line a, Line b) {
        return a.getId().compareTo(b.getId());
    }

}
