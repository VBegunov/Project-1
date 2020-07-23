package tusk1.core;

import tusk1.exceptions.InvalidFileNameReadException;
import tusk1.model.Department;
import tusk1.model.Employee;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class ReadsEmployees {
    private String fileName;
    private static StringBuilder invalidEmployees = new StringBuilder();
    private static Map<String, Department> departments = new HashMap<>();

    public ReadsEmployees(String fileName) {
        this.fileName = fileName;
    }

    public StringBuilder getInvalidEmployees() {
        return invalidEmployees;
    }

    public Map<String, Department> getDepartments() {
        return departments;
    }

    // сформировать отделы
    public void createDepartments() {
        int countLine = 0; //счетчик строк
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                countLine++;
                //создаем сотрудника из данных в строке
                if (checkLine(countLine, line)) {
                    String[] employeeInfo = line.split(";");
                    Employee employee = new Employee(employeeInfo[0], employeeInfo[1], new BigDecimal(employeeInfo[2]));
                    //формируем отделы
                    Department dep = departments.getOrDefault(employeeInfo[1], new Department(employeeInfo[1]));
                    dep.getEmployees().add(employee);
                    departments.putIfAbsent(employeeInfo[1], dep);
                }
            }
            if(departments.isEmpty()) throw new InvalidFileNameReadException("В считываемом файле нет корректных полей с данными по сотрудникам");
        } catch (FileNotFoundException e) {
            System.out.println("Некорректное имя файла, переданное первым аргументом: " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла, переданного первым аргументом: " + fileName);
        }
    }

    //проверяет валидность строки, невалидные записывает в переменную invalidEmployee
    private boolean checkLine(int countLine, String line) {
        StringBuilder info = new StringBuilder("Строка № " + countLine + " : " + line + "   Причина: ");
        String[] employeeInfo = line.split(";");
        if (employeeInfo.length != 3) {
            invalidEmployees.append(info).append("Некорректно указаны данные о сотруднике, требуется три поля: \"ФИО\" , \"Название отдела\" , \"Зарплата сотрудника\" \n");
            return false;
        }
        boolean name = employeeInfo[0].matches("([А-Яа-я_a-zA-ZёЁ]*\\s?){5}");
        boolean department = employeeInfo[1].matches("([А-Яа-я_a-zA-ZёЁ]*\\s?\\d?){4}");
        boolean salary = employeeInfo[2].matches("\\d*(\\.?\\d{2})?");
        if (!name) {
            info.append(" Некорректное имя сотрудника - ").append(employeeInfo[0]).append(";");
        }
        if (!department) {
            info.append(" Некорректное имя отдела - ").append(employeeInfo[1]).append(";");
        }
        if (!salary) {
            info.append(" Некорректная зарплата сотрудника - ").append(employeeInfo[2]).append(";");
        }
        if (!name || !department || !salary) invalidEmployees.append(info).append("\n");
        return name && department && salary;
    }
}