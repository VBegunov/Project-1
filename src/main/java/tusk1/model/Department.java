package tusk1.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import java.util.stream.Collectors;

public class Department {
    private String name;
    private Set<Employee> employees;

    public Department(String name) {
        this.name = name;
        this.employees = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    //расчет средней зарплаты сотрудников по текущему отделу
    public BigDecimal calculateAverageSalary() {
        BigDecimal sum = employees.stream()
                .map(Employee::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal count = new BigDecimal(employees.size());
        return sum.divide(count, MathContext.DECIMAL32);
    }

    //получить информацию о сотрудниках в отделе
    private StringBuilder getInfoDepartment() {
        StringBuilder viewInfo = new StringBuilder("\n");
        for (Employee employee : employees) {
            viewInfo.append(employee.toString());
        }
        return viewInfo;
    }

    //возвращает комбинации для перемещения сотрудников
    public Set<HashSet<Employee>> getCombinationOfEmployeesTransfer() {
        //создаю лист с комбинациями
        Set<HashSet<Employee>> combinationOfEmployeesTransfer = new HashSet<>();
        //добавляю всех сотрудников в вариант комбинации
        combinationOfEmployeesTransfer.add((HashSet<Employee>) employees);
        //создаю комбинации
        transferEmployee(combinationOfEmployeesTransfer, employees.size());
        //убираю комбинацию со всеми сотрудниками
        combinationOfEmployeesTransfer.remove((HashSet<Employee>) employees);
        return combinationOfEmployeesTransfer;
    }
//    расчитывает все возможные комбинации для перевода в отделе
    private Set<HashSet<Employee>> combinations = new HashSet<>(); //промежуточный лист по комбинациям
    private void transferEmployee(Set<HashSet<Employee>> combinationOfEmployeesTransfer, int sizeDepartment) {
        if (sizeDepartment > 1) {
            for (HashSet<Employee> employeesTransfer : combinationOfEmployeesTransfer.stream().filter(s -> s.size() == sizeDepartment).collect(Collectors.toSet())) {
                    for (Employee employee : employees) {
                        HashSet<Employee> combination = new HashSet<>(employeesTransfer);

                        //если в комбинации нет такого сотрудника то возвращаюсь
                        if (!combination.contains(employee)) continue;

                        combination.remove(employee);
                        //проверяю что зп переводимых сотрудников меньше чем по отделу
                        if (calculateAverageSalaryEmployees(combination).compareTo(calculateAverageSalary()) < 0) {
                            combinations.add(combination);
                        }
                    }
            }
            combinationOfEmployeesTransfer.addAll(combinations);
            combinations.clear();
            transferEmployee(combinationOfEmployeesTransfer, sizeDepartment - 1);
        }
    }

    //для расчета средней зп сотрудников
    private static BigDecimal calculateAverageSalaryEmployees(HashSet<Employee> employees) {
        BigDecimal sum = employees.stream()
                .map(Employee::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal count = BigDecimal.valueOf(employees.size());
        return sum.divide(count, MathContext.DECIMAL32);
    }

    @Override
    public String toString() {
        return "Отдел " + name + ", Средняя зарплата по отделу " + calculateAverageSalary() +
                ", Количество сотрудников: " + employees.size() + " " + getInfoDepartment() + "\n";
    }
}
