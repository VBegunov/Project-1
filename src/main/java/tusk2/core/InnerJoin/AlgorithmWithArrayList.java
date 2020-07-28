package tusk2.core.InnerJoin;

import java.util.ArrayList;
import java.util.Comparator;

public class AlgorithmWithArrayList {
    private ArrayList<String> list1 = new ArrayList<>();
    private ArrayList<String> list2 = new ArrayList<>();
    private static StringBuilder getInnerJoin = new StringBuilder(String.format("%-10.10s %-100.100s %-100.100s\n", "ID", "A.VALUE", "B.VALUE"));

    public AlgorithmWithArrayList(ArrayList<String> list1,ArrayList<String> list2) {
        this.list1.addAll(list1);
        this.list2.addAll(list2);
    }

    public ArrayList<String> getList1() {
        return list1;
    }

    public ArrayList<String> getList2() {
        return list2;
    }

    public StringBuilder getInnerJoin() {
        this.list1.sort(Comparator.comparing(s0 -> s0.substring(0, s0.indexOf(",")).trim()));
        this.innerJoinLinkedList(0);
        return getInnerJoin;
    }

    private void innerJoinLinkedList(int index) {
        if (index == list1.size()) return;
        String id1 = list1.get(index).substring(0, list1.get(index).indexOf(",")).trim();
        for (String line2 : list2) {
            String id2 = line2.substring(0, line2.indexOf(",")).trim();
            if (id1.equals(id2)) {
                String value1 = list1.get(index).substring(list1.get(index).indexOf(",")+1);
                String value2 = line2.substring(line2.indexOf(",")+1);
                getInnerJoin.append(String.format("%-10.10s %-100.100s %-100.100s\n", id1, value1, value2));
            }
        }
        innerJoinLinkedList(index+1);
    }
}
