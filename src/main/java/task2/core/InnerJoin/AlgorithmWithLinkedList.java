package task2.core.InnerJoin;

import task2.model.InnerJoinLine;
import task2.model.Line;

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
        if (list1.isEmpty() || list2.isEmpty()) return result;
        ListIterator<Line> iterator1 = list1.listIterator();
        ListIterator<Line> iterator2 = list2.listIterator();
        Line line1 = iterator1.next();
        Line line2 = iterator2.next();

        if (!iterator1.hasNext() && !iterator2.hasNext()) {
            return compareLines(line1, line2);
        }
        if (!iterator1.hasNext()) {
            return getDupInnerJoinLines2(line1, line2, iterator2);
        }
        if (!iterator2.hasNext()) {
            return getDupInnerJoinLines1(line1, line2, iterator1);
        }

        while (iterator1.hasNext() && iterator2.hasNext()) {
            int compare = Integer.compare(line1.getId(), line2.getId());
            if (compare < 0) {
                line1 = iterator1.next();
                if (!iterator1.hasNext()) {
                    result.addAll(getDupInnerJoinLines2(line1, line2, iterator2));
                }
            } else if (compare > 0) {
                line2 = iterator2.next();
            } else {
                result.addAll(getDupInnerJoinLines2(line1, line2, iterator2));
                previousIterator(iterator2, line2);
                line1 = iterator1.next();
                if (!iterator1.hasNext()) {
                    result.addAll(getDupInnerJoinLines2(line1, line2, iterator2));
                }
            }
        }
        return result;
    }

    private List<InnerJoinLine> getDupInnerJoinLines1(Line line1, Line line2, ListIterator<Line> iterator1) {
        List<InnerJoinLine> result = new ArrayList<>(compareLines(line1, line2));
        while (iterator1.hasNext()) {
            line1 = iterator1.next();
            result.addAll(compareLines(line1, line2));
            if(line1.getId() > line2.getId()) break;
        }
        return result;
    }

    private List<InnerJoinLine> getDupInnerJoinLines2(Line line1, Line line2, ListIterator<Line> iterator2) {
        List<InnerJoinLine> result = new ArrayList<>(compareLines(line1, line2));
        while (iterator2.hasNext()) {
            line2 = iterator2.next();
            if(line2.getId() > line1.getId()) break;
            result.addAll(compareLines(line1, line2));
        }
        return result;
    }

    private void previousIterator(ListIterator<Line> list, Line line1) {
        Line line;
        while (list.hasPrevious()) {
            line = list.previous();
            if (line.equals(line1)) {
                list.next();
                break;
            }
        }
    }

    private List<InnerJoinLine> compareLines(Line line1, Line line2) {
        List<InnerJoinLine> innerJoinLines = new ArrayList<>();
        if (line1.getId() == line2.getId()) {
            innerJoinLines.add(new InnerJoinLine(line1.getId(), line1.getValue(), line2.getValue()));
        }
        return innerJoinLines;
    }

    @Override
    public int compare(Line a, Line b) {
        return Integer.compare(a.getId(), b.getId());
    }
}