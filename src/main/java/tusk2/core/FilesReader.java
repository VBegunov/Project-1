package tusk2.core;

import tusk2.exceptions.InvalidFileReadException;
import tusk2.model.Line;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilesReader {
    private static List<Line> list1 = new ArrayList<>();
    private static List<Line> list2 = new ArrayList<>();

    public static List<Line> getList1() {
        return list1;
    }

    public static List<Line> getList2() {
        return list2;
    }

    public static void read(String[] args) {
        list1 = read(args[0]);
        list2 = read(args[1]);
    }

    private static List<Line> read(String fileName) {
        ArrayList<Line> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready()) {
                String[] line = reader.readLine().split(",");
                Line line1 = new Line(Integer.parseInt(line[0].trim()), line[1].trim());
                list.add(line1);
            }
        } catch (FileNotFoundException s) {
            throw new InvalidFileReadException("Некорректное имя файла " + fileName);
        } catch (IOException f) {
            System.out.println("Ошибка чтения файла " + fileName);
        }
        return list;
    }

}
