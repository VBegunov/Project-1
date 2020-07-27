package tusk2.core.InnerJoin;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AlgorithmWithLinkedList {
    private List<String> list1 = new LinkedList<>();
    private List<String> list2 = new LinkedList<>();

    public List<String> getList1() {
        return list1;
    }
    public List<String> getList2() {
        return list2;
    }

    public AlgorithmWithLinkedList(ArrayList<String> list1, ArrayList<String> list2) {
        this.list1.addAll(list1);
        this.list2.addAll(list2);
    }

    public StringBuilder getInnerJoinLinkedList(){
        String firstLine = String.format("%-10.10s %-100.100s %-100.100s", "ID", "A.VALUE", "B.VALUE");
        StringBuilder result = new StringBuilder(firstLine+"\n");
        list1.sort(String::compareTo);
        list2.sort(String::compareTo);
        for (String line1: list1) {
            String[] lineFirstFile = line1.split(",");
            String FirstId = lineFirstFile[0].trim();
            String FirstValue = lineFirstFile[1].trim();

            for (String line2: list2){
                String[] lineSecondFile = line2.split(",");
                String SecondId = lineSecondFile[0].trim();
                String SecondValue = lineSecondFile[1].trim();
                String lineResult = String.format("%-10.10s %-100.100s %-100.100s", FirstId, FirstValue, SecondValue);

                if(FirstId.equals(SecondId)) {
                    String ids = String.format("%-10.10s", FirstId);
                    if(result.lastIndexOf(ids) > 0){
                        result.insert(result.lastIndexOf(ids)+212, "\n" + lineResult);
                    } else{
                        result.append(lineResult).append("\n");
                    }
                }
            }
        }
        return result;
    }
}
