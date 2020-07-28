package tusk2.core.InnerJoin;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class AlgorithmWithLinkedList {
    private List<String> list1 = new LinkedList<>();
    private List<String> list2 = new LinkedList<>();

    public AlgorithmWithLinkedList(ArrayList<String> list1, ArrayList<String> list2) {
        this.list1.addAll(list1);
        this.list2.addAll(list2);
        this.list1.sort(Comparator.comparing(s0 -> s0.substring(0, s0.indexOf(",")).trim()));
        this.list2.sort(Comparator.comparing(s0 -> s0.substring(0, s0.indexOf(",")).trim()));
    }

    public StringBuilder getInnerJoin() {
        String firstLine = String.format("%-10.10s %-100.100s %-100.100s", "ID", "A.VALUE", "B.VALUE");
        StringBuilder result = new StringBuilder(firstLine + "\n");
        for (String line1 : list1) {
            String[] lineFirstFile = line1.split(",");
            String firstId = lineFirstFile[0].trim();
            String firstValue = lineFirstFile[1].trim();

            for (String line2 : list2) {
                String[] lineSecondFile = line2.split(",");
                String secondId = lineSecondFile[0].trim();
                String secondValue = lineSecondFile[1].trim();

                if (firstId.equals(secondId)) {
                    String lineResult = String.format("%-10.10s %-100.100s %-100.100s", firstId, firstValue, secondValue);
                    String ids = String.format("%-10.10s", firstId);

                    if (result.lastIndexOf(ids) > 0) {
                        result.insert(result.lastIndexOf(ids) + 212, "\n" + lineResult);
                    } else {
                        result.append(lineResult).append("\n");
                    }
                }
            }
        }
        return result;
    }
}
