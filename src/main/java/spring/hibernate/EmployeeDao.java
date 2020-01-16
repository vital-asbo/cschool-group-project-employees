package spring.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeDao {

    public void saveEmployee(Employees employee) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


//    * Method to UPDATE salary for an employee */
//    public void updateEmployee(Integer EmployeeID, int salary ){
//        Session session = factory.openSession();
//        Transaction tx = null;
//        try{
//            tx = session.beginTransaction();
//            Employee employee =
//                    (Employee)session.get(Employee.class, EmployeeID);
//            employee.setSalary( salary );
//            session.update(employee);
//            tx.commit();
//        }catch (HibernateException e) {
//            if (tx!=null) tx.rollback();
//            e.printStackTrace();
//        }finally {
//            session.close();
//        }
//    }
// public void updateEmployee(Integer EmployeeID, Employees employees ){

    public void updateEmployee( Employees employees ){
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Employees employee =
                    (Employees)session.get(Employees.class, employees.getId());
            employee.setFirstName( employees.getFirstName() );
            employee.setLastName( employees.getLastName() );
            employee.setAddress( employees.getAddress() );
            employee.setCity( employees.getCity() );
            employee.setSalary( employees.getSalary() );
            employee.setBenefit( employees.getBenefit() );
            employee.setStartJobDate( employees.getStartJobDate() );
            employee.setEmail( employees.getEmail() );
            employee.setAge(employees.getAge());
            session.update(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public List<Employees> getEmployees() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Employees", Employees.class).list();
        }
    }

//    public void updateEmployees(Employees employee) {
//        Transaction transaction = null;
//        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.update(employee);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//    }
}
