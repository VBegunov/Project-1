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
        ListIterator<Line> iterator1 = list1.listIterator();
        ListIterator<Line> iterator2 = list2.listIterator();
        Line line1 = iterator1.next();
        Line line2 = iterator2.next();

        while (iterator1.hasNext()) {
            int compare = Integer.compare(line1.getId(), line2.getId());
            if (compare < 0) {
                if (iterator1.hasNext()) {
                    line1 = iterator1.next();
                }
            } else if (compare > 0) {
                if (iterator2.hasNext()) {
                    line2 = iterator2.next();
                } else break;
            } else {
                result.add(new InnerJoinLine(line1.getId(),line1.getValue(),line2.getValue()));

                for(Line line: getLines(iterator2, line1.getId())){
                    result.add(new InnerJoinLine(line.getId(), line1.getValue(), line.getValue()));
                }
                previousIterator(iterator2, line2);

                if(iterator1.hasNext()){
                    line1 = iterator1.next();

                    if(!iterator1.hasNext()){
                        if(line1.getId() == (line2.getId())){
                            result.add(new InnerJoinLine(line1.getId(),line1.getValue(),line2.getValue()));
                        }
                        for(Line line: getLines(iterator2, line1.getId())){
                            result.add(new InnerJoinLine(line.getId(), line1.getValue(), line.getValue()));
                        }
                    }
                }

            }
        }
        return result;
    }

    private void previousIterator(ListIterator<Line> list, Line line1){
        Line line;
        while(list.hasPrevious()){
            line = list.previous();
            if(line.equals(line1)) {
                list.next();
                break;
            }
        }
    }

    private List<Line> getLines(ListIterator<Line> list, int id){
        List<Line> result = new ArrayList<>();
        Line line;
        while(list.hasNext()){
            line = list.next();
            if(line.getId() == id){
                result.add(line);
            } else break;
        }
        return result;
    }


    @Override
    public int compare(Line a, Line b) {
        return Integer.compare(a.getId(), b.getId());
    }

}
