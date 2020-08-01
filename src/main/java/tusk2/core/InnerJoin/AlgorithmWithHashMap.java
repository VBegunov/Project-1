package tusk2.core.InnerJoin;


import tusk2.model.InnerJoinLine;
import tusk2.model.Line;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AlgorithmWithHashMap {
    private Map<Integer, ArrayList<String>> map1;
    private Map<Integer, ArrayList<String>> map2;

    public AlgorithmWithHashMap(List<Line> list1, List<Line> list2) {
        this.map1 = mapping(list1);
        this.map2 = mapping(list2);
    }

    private HashMap<Integer, ArrayList<String>> mapping(List<Line> list12) {
        Map<Integer, ArrayList<String>> map = list12.stream().collect(Collectors.toMap(
                Line::getId, item -> {
                    ArrayList<String> list = new ArrayList<>();
                    list.add(item.getValue());
                    return list;
                },
                (line1, line2) -> {
                    line1.addAll(line2);
                    return line1;
                }));
//        HashMap<Integer, ArrayList<String>> map = new HashMap<>();
//        for(Line line: list1){
//            ArrayList<String> values = map.getOrDefault(line.getId(), new ArrayList<>());
//            values.add(line.getValue());
//            map.putIfAbsent(line.getId(), values);
//        }
        return (HashMap<Integer, ArrayList<String>>) map;
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
        for (Map.Entry<Integer, ArrayList<String>> file1 : map1.entrySet()) {
            if (map2.containsKey(file1.getKey())) {
                for (String lineLeft : file1.getValue()) {
                    for (String lineRight : map2.get(file1.getKey())) {
                        result.add(new InnerJoinLine(file1.getKey(), lineLeft, lineRight));
                    }
                }
            }
        }
        return result;
    }
}
