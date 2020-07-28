package tusk2.core.InnerJoin;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class AlgorithmWithHashMap {
    private Map<ArrayList<String>, ArrayList<String>> map1 = new HashMap<>();
    private Map<ArrayList<String>, ArrayList<String>> map2 = new HashMap<>();

    public AlgorithmWithHashMap(ArrayList<String> list1, ArrayList<String> list2) {
        this.map1.put((ArrayList<String>) list1.stream().map(s -> s.substring(0, s.indexOf(",")).trim()).collect(Collectors.toList()),
                (ArrayList<String>) list1.stream().map(s -> s.substring(s.indexOf(",") + 1).trim()).collect(Collectors.toList()));
        this.map2.put((ArrayList<String>) list2.stream().map(s -> s.substring(0, s.indexOf(",")).trim()).collect(Collectors.toList()),
                (ArrayList<String>) list2.stream().map(s -> s.substring(s.indexOf(",") + 1).trim()).collect(Collectors.toList()));
    }

    public StringBuilder getInnerJoinV1() {
        StringBuilder result = new StringBuilder(String.format("%-10.10s %-100.100s %-100.100s", "ID", "A.VALUE", "B.VALUE") + "\n");

        ArrayList<String> keys1 = map1.keySet().iterator().next();
        ArrayList<String> keys2 = map2.keySet().iterator().next();
        ArrayList<String> values1 = map1.values().iterator().next();
        ArrayList<String> values2 = map2.values().iterator().next();

        for (int key1 = 0; key1 < keys1.size(); key1++) {
            String id1 = keys1.get(key1);
            if (keys2.contains(id1)) {
                for (int key2 = 0; key2 < keys2.size(); key2++) {
                    String id2 = keys2.get(key2);
                    if (id2.equals(id1)) {
                        String value1 = values1.get(key1);
                        String value2 = values2.get(key2);
                        String line = String.format("%-10.10s %-100.100s %-100.100s", id1, value1, value2);

                        if(result.indexOf(id1) > 0){
                            result.insert(result.lastIndexOf(id1) + 212, "\n" + line);
                        } else {
                            result.append(line).append("\n");
                        }
                    }
                }
            }
        }
        return result;
    }
}
