package tusk2.core.InnerJoin;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AlgorithmWithHashMap {
    private Map<ArrayList<String>, ArrayList<String>> map = new HashMap<>();

    public AlgorithmWithHashMap(ArrayList<String> list1,ArrayList<String> list2){
        this.map.put(list1, list2);
    }

    public Map<ArrayList<String>, ArrayList<String>> getMap() {
        return map;
    }

    public StringBuilder getInnerJoinHashMap(){
        String firstLine = String.format("%-10.10s %-100.100s %-100.100s", "ID", "A.VALUE", "B.VALUE");
        StringBuilder result = new StringBuilder(firstLine+"\n");

        for (Map.Entry<ArrayList<String>, ArrayList<String>> map: map.entrySet()) {
            ArrayList<String> list1 = map.getKey();
            ArrayList<String> list2 = map.getValue();

            for (String line1: list1) {
                String[] lineFirstFile = line1.split(",");
                String firstId = lineFirstFile[0].trim();
                String firstValue = lineFirstFile[1].trim();

                for (String line2: list2){
                    String[] lineSecondFile = line2.split(",");
                    String secondId = lineSecondFile[0].trim();
                    String secondValue = lineSecondFile[1].trim();

                    String lineResult = String.format("%-10.10s %-100.100s %-100.100s", firstId, firstValue, secondValue);
                    if(firstId.equals(secondId)) {
                        String ids = String.format("%-10.10s", firstId);
                        if(result.lastIndexOf(ids) > 0){
                            result.insert(result.lastIndexOf(ids)+212, "\n" + lineResult);
                        } else{
                            result.append(lineResult).append("\n");
                        }
                    }
                }
            }
        }
        return result;
    }
}
