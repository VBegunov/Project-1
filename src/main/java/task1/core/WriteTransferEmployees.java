package task1.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteTransferEmployees {

    private String fileName;

    public WriteTransferEmployees(String fileName) {
        this.fileName = fileName;
    }

    //записывает информацию
    public void write(StringBuilder transferEmployees) {
        //задаю имя файлу
        String name = "Варианты перевода.txt";
        //создаю файл и помещаю в него варианты перевода
        File file = new File(transferEmployees.toString());

        //произвожу запись
        try (FileWriter fileWriter = new FileWriter(fileName + "/" + name)) {
            fileWriter.write(file.toString());
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл");

        }
    }
}
