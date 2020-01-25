package spring.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.jnlp.ClipboardService;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class HibernateDao {

    public void saveEmployee(HibernateEntity hibernateEntity) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(hibernateEntity);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public void deleteEmployee(HibernateEntity hibernateEntity) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

//            Employees employee =
//                    (Employees) session.get(Employees.class, employees.getId());
            session.delete(hibernateEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public List<Employees> getEmployees() {
//        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
////            return session.createQuery("from Employees", Employees.class).list();
////        }
        List<Employees> list = get(Employees.class);
        return list;
    }

    public List<Cars> getCars() {
//        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
//
//
//            return session.createQuery("from Cars", Cars.class).list();
//        }
        List<Cars> list = get(Cars.class);
        return list;
    }

    //metoda tworzy listy z tabel z bazy danych
    public <T> List<T> get(Class<T> type) {
        Session session = HibernateConfig.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        List<T> data = session.createQuery(criteria).getResultList();
        tx.commit();
        return data;
    }


    public void updateEmployees(HibernateEntity hibernateEntity) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(hibernateEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


        public void saveDB(HibernateEntity hibernateEntity) {

            try {
                saveEmployee(hibernateEntity);
            } catch (NullPointerException e) {
                return;
            }
        }


}
