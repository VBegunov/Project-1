package tusk2.core;

import tusk2.exceptions.InvalidFileReadException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FilesReader {
    private static ArrayList<String> list1 = new ArrayList<>();
    private static ArrayList<String> list2 = new ArrayList<>();

    public static ArrayList<String> getList1() {
        return list1;
    }

    public static ArrayList<String> getList2() {
        return list2;
    }

    public static void read(String[] args){
        readFirstFile(args[0]);
        readSecondFile(args[1]);
    }

    private static void readFirstFile(String fileName){
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            while(reader.ready()){
                String line = reader.readLine();
                list1.add(line);
            }
        } catch (FileNotFoundException s){
            throw new InvalidFileReadException("Некорректное имя файла " + fileName);
        } catch (IOException f){
            System.out.println("Ошибка чтения файла " + fileName);
        }

    }

    private static void readSecondFile(String fileName){
        try(BufferedReader reader1 = new BufferedReader(new FileReader(fileName))){
            while (reader1.ready()){
                String line = reader1.readLine();
                list2.add(line);
            }
        } catch (FileNotFoundException e){
            throw new InvalidFileReadException("Некорректное имя файла " + fileName);
        } catch (IOException f){
            System.out.println("Ошибка чтения файла " + fileName);
        }
    }
}
