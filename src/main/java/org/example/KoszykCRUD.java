package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class KoszykCRUD {
    private SessionFactory sessionFactory;
    public KoszykCRUD() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
    }
    public Integer saveKoszyk(Koszyk koszyk){ // Dodawanie rekordów do tabeli
        Session session = null;
        Integer idKoszyk=null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            idKoszyk = (Integer) session.save(koszyk);
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return idKoszyk;
    }
    public Koszyk getKoszyk(Integer idKoszyk) {  //odczytanie ostatnio dodanych danych
        Session session = null;
        Koszyk koszyk = null;
        try{
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Koszyk WHERE idKoszyk = " +idKoszyk;
            Query query = session.createQuery(hql);
            List results = query.list();

            if(results.size() >0) koszyk = (Koszyk) results.get(0);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            session.close();
        }
        return koszyk;
    }
    public List getKoszyk() {  //odczytanie danych z tablicy
        Session session = null;
        List results = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            String hql = "FROM Koszyk ";
            Query query = session.createQuery(hql);
            results = query.list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }
    public void run(){
        Koszyk koszyk1 = new Koszyk(1);


        Integer newIdKoszyk = saveKoszyk(koszyk1);


        System.out.println("Zapisany rekord, id: " + newIdKoszyk);

        List<Koszyk> results = getKoszyk();
        System.out.println("\nIlość rekordów w tabeli Koszyk to: " +results.size());
        for(int i=0; i< results.size(); i++){
            System.out.println("Koszyk " + i+ ": " + results.get(i));
        }
    }

    public static void main(String[] args) {
        KoszykCRUD koszykCRUD = new KoszykCRUD();
        koszykCRUD.run();
    }
}
