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
        ArrayList<String> ids1 = map1.keySet().iterator().next();
        ArrayList<String> ids2 = map2.keySet().iterator().next();
        ArrayList<String> values1 = map1.values().iterator().next();
        ArrayList<String> values2 = map2.values().iterator().next();

        for (int indexId1 = 0; indexId1 < ids1.size(); indexId1++) {
            String id1 = ids1.get(indexId1);
            if (ids2.contains(id1)) {
                for (int indexId2 = 0; indexId2 < ids2.size(); indexId2++) {
                    String id2 = ids2.get(indexId2);
                    if (id2.equals(id1)) {
                        String value1 = values1.get(indexId1);
                        String value2 = values2.get(indexId2);
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
