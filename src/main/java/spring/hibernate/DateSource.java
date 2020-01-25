package spring.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateSource {


    public static boolean isDataBaseConnection = Boolean.FALSE;

    public static void main(String[] args) {
        supplyDB();
    }

    public static void supplyDB() {

        HibernateDao hibernateDao = null;


        try {
            hibernateDao = new HibernateDao();
            isDataBaseConnection = Boolean.TRUE;
        } catch (NullPointerException ex){

            System.out.println("Brak połaczenia z bazą danych");
            ex.getMessage();
        }

        Employees employee = new Employees("Jan", "Kowal", "Pogodna 12", "Katowice", 3500, 25, new Date(), 1, "jan@ko.pl");
        Employees employee1 = new Employees("Zbigniew", "Zamachowski", "Deszczowa 15", "Sosnowiec", 1500, 21, new Date(), 1, "Z@op");
        Employees employee2 = new Employees("Kazimierz", "Dejna", "Zabłotna 34", "Gdańsk", 3000, 35, new Date(), 1, "ss@ko.pl");
//        Employees employees1 = new Employees("Test","Test","Test","Test",1800,25,2,new Date("25-04-2009"),5,"test@test");


        Cars cars = new Cars(employee, "Seat", "Ibiza", new Date());
        Cars cars1 = new Cars(employee1, "Lexus", "x6", new Date());
        Cars cars2 = new Cars(employee2, "BMW", "E36", new Date());

        hibernateDao.saveEmployee(employee);
        hibernateDao.saveEmployee(employee1);
        hibernateDao.saveEmployee(employee2);
        hibernateDao.saveEmployee(cars);
        hibernateDao.saveEmployee(cars1);
        hibernateDao.saveEmployee(cars2);

    }


    public static List<Employees> getEmployeeList(){
        List<Employees> list = new ArrayList<>();
        Employees employee1 = new Employees("Test", "Test", "Test 12", "Katowice", 3500, 25, new Date(), 1, "jan@ko.pl");
        employee1.setId(0);
        Employees employee2 = new Employees("Test2", "Test", "Test 12", "Katowice", 3500, 25, new Date(), 1, "jan@ko.pl");
        employee2.setId(1);
        list.add(employee1);
        list.add(employee2);
        return list;
    }

    public static List<Cars> getCarList(){
        List<Cars> list = new ArrayList<>();
        Employees employees= DateSource.getEmployeeList().get(0);
        Employees employees1= DateSource.getEmployeeList().get(1);
        Cars cars = new Cars(employees, "Test", "Kamaz", new Date());
        Cars cars1 = new Cars(employees1, "Test", "Passat", new Date());

        list.add(cars);
        list.add(cars1);
        return list;
    }

//
//    public void saveDAO(List<HibernateEntity> hibernateEntities) {
//
//        Transaction transaction = null;
//        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.save(hibernateEntities);
//
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//    }

}
