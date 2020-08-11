package task1.core;

import task1.model.Department;
import task1.model.Employee;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashSet;
import java.util.Map;

public class TransferEmployees {

    private Map<String, Department> departments;

    public TransferEmployees(Map<String, Department> departments) {
        this.departments = departments;
    }

    public StringBuilder transferEmployees() {
        StringBuilder line = new StringBuilder();
        //беру отдел из которого буду переводить сотрудников
        for (Department department : departments.values()) {

            //беру сотрудников из отдела из которого буду переводить
            for (HashSet<Employee> employees : department.getCombinationOfEmployeesTransfer()) {

                //промежуточная переменная для подсчета средней зп переводимых сотрудников
                BigDecimal averageSalaryEmployees = calculateAverageSalaryEmployees(employees);

                //беру все отделы в которые буду переводить сотрудника
                for (Department department1 : departments.values()) {
                    //проверяю что не перевожу сотрудника в отдел где он числится
                    if (department1.getName().equals(department.getName())) continue;

                    //промежуточная переменная которая считает зп по отделу в который буду переводить сотрудников
                    BigDecimal averageSalaryDepartment = department1.calculateAverageSalary();

                    //проверяю что зп переводимых сотрудников больше чем в отделе в который их перевожу
                    if (averageSalaryEmployees.compareTo(averageSalaryDepartment) < 0) continue;

                    //промежуточная переменная которая считает сумму всех зп по отделу откуда перевожу сотрудников
                    BigDecimal salaryDepartment = department.getEmployees().stream().map(Employee::getSalary).reduce(BigDecimal.ZERO, BigDecimal::add);
                    //промежуточная переменная которая сумму всех зп по отделу куда перевожу
                    BigDecimal salaryDepartment1 = department1.getEmployees().stream().map(Employee::getSalary).reduce(BigDecimal.ZERO, BigDecimal::add);
                    //промежуточная переменная которая считает сумму всех зп переводимых сотрудников
                    BigDecimal salaryEmployees = employees.stream().map(Employee::getSalary).reduce(BigDecimal.ZERO, BigDecimal::add);

                    //промежуточная переменная которая считает зарплату в новом департаменте от куда переводим сотрудников
                    BigDecimal averageSalaryNewDepartment = (salaryDepartment.subtract(salaryEmployees, MathContext.DECIMAL32))
                            .divide(BigDecimal.valueOf(department.getEmployees().size() - employees.size()), MathContext.DECIMAL32);
                    //промежуточная переменная которая считает зарплату в новом департаменте куда переводим сотрудников
                    BigDecimal averageSalaryNewDepartment1 = salaryDepartment1.add(salaryEmployees)
                            .divide(BigDecimal.valueOf(department1.getEmployees().size() + employees.size()), MathContext.DECIMAL32); //делю на количество сотрудников

                    line.append("Перевод -  ").append(getInfoEmployees(employees))
                            .append("Средняя зп по отделу: ").append(department.calculateAverageSalary())
                            .append(" изменится на: ").append(averageSalaryNewDepartment).append(" ")
                            .append(" в отдел ").append(department1.getName())
                            .append(" изменит среднюю зп в отделе с ").append(averageSalaryDepartment)
                            .append(" на ").append(averageSalaryNewDepartment1)
                            .append("\n");
                }
            }
        }
        return line;
    }

    //для расчета средней зп сотрудников
    private static BigDecimal calculateAverageSalaryEmployees(HashSet<Employee> employees) {
        BigDecimal sum = employees.stream()
                .map(Employee::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal count = new BigDecimal(employees.size());
        return sum.divide(count, MathContext.DECIMAL32);
    }

    //для вывода информации по сотрудникам которых перемещаю
    private static StringBuilder getInfoEmployees(HashSet<Employee> employees) {
        StringBuilder lineInfo = new StringBuilder();
        for (Employee employee : employees) {
            lineInfo.append(employee.getName()).append(", ");
        }
        lineInfo.append(" из отдела ")
                .append(employees.iterator().next().getDepartment())
                .append(" ");
        return lineInfo;
    }
}