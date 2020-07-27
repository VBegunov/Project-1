package tusk2.core.InnerJoin;

import java.util.ArrayList;

public class AlgorithmWithArrayList {
    private ArrayList<String> list1 = new ArrayList<>();
    private ArrayList<String> list2 = new ArrayList<>();

    public ArrayList<String> getList1() {
        return list1;
    }
    public ArrayList<String> getList2() {
        return list2;
    }

    public AlgorithmWithArrayList(ArrayList<String> list1,ArrayList<String> list2) {
        this.list1.addAll(list1);
        this.list2.addAll(list2);
    }

    public StringBuilder getInnerJoinArrayList(){
        String firstLine = String.format("%-10.10s %-100.100s %-100.100s", "ID", "A.VALUE", "B.VALUE");
        StringBuilder result = new StringBuilder(firstLine+"\n");
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
