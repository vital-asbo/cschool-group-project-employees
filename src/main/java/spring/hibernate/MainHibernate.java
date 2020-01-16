package spring.hibernate;

import java.util.Date;
import java.util.List;
import spring.hibernate.EmployeeDao;
import spring.hibernate.Employees;


public class MainHibernate {
    public static void main(String[] args) {
        EmployeeDao employeeDao = new EmployeeDao();
        Employees employee = new Employees("Jan","Kowal","Pogodna 12","Katowice",3500,25,new Date("24-06-2018"),5,"jan@ko.pl");
//        Employees employees1 = new Employees("Test","Test","Test","Test",1800,25,2,new Date("25-04-2009"),5,"test@test");
        employeeDao.saveEmployee(employee);
        List<Employees> employeesList = employeeDao.getEmployees();

        Employees employeeToUpdate = employeesList.get(0);
        employeeToUpdate.setSalary(9999);

//        employeeDao.updateEmployees(employeeToUpdate);
        employeeDao.updateEmployee(employeeToUpdate);
        employeesList.forEach(System.out::println);
    }
}
