package task2;

import task2.core.CheckArgument;
import task2.core.FilesReader;
import task2.core.InnerJoin.AlgorithmWithArrayList;
import task2.core.InnerJoin.AlgorithmWithHashMap;
import task2.core.InnerJoin.AlgorithmWithLinkedList;

public class Main {
    public static void main(String[] args) {
        CheckArgument.checkArg(args);

        FilesReader.read(args);

        AlgorithmWithArrayList algorithmWithArrayList = new AlgorithmWithArrayList(FilesReader.getList1(), FilesReader.getList2());
        AlgorithmWithLinkedList algorithmWithLinkedList = new AlgorithmWithLinkedList(FilesReader.getList1(), FilesReader.getList2());
        AlgorithmWithHashMap algorithmWithHashMap = new AlgorithmWithHashMap(FilesReader.getList1(), FilesReader.getList2());

        System.out.println(algorithmWithArrayList.getInnerJoin());
        System.out.println(algorithmWithLinkedList.getInnerJoin());
        System.out.println(algorithmWithHashMap.getInnerJoin());
    }
}
