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

public class Koszyk_produktyCRUD {
    private SessionFactory sessionFactory;
    public Koszyk_produktyCRUD() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
    }
    public Integer saveKoszyk_produkty(Koszyk_produkty koszyk_produkty){ // Dodawanie rekordów do tabeli
        Session session = null;
        Integer idKoszyk_produkty=null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            idKoszyk_produkty = (Integer) session.save(koszyk_produkty);
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return idKoszyk_produkty;
    }
    public Koszyk_produkty getKoszyk_produkty(Integer idKoszyk_produkty) {  //odczytanie ostatnio dodanych danych
        Session session = null;
        Koszyk_produkty koszyk_produkty = null;
        try{
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Koszyk_produkty WHERE idKoszyk_produkty = " +idKoszyk_produkty;
            Query query = session.createQuery(hql);
            List results = query.list();

            if(results.size() >0) koszyk_produkty = (Koszyk_produkty) results.get(0);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            session.close();
        }
        return koszyk_produkty;
    }

    public List getKoszyk_produktyByKoszykId(Integer koszykId) {
        Session session = null;
        List results = null;
        try{
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Koszyk_produkty WHERE idKoszyk = " +koszykId;
            Query query = session.createQuery(hql);
            results = query.list();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            session.close();
        }
        return results;
    }
    public List getKoszyk_produkty() {  //odczytanie danych z tablicy
        Session session = null;
        List results = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            String hql = "FROM Koszyk_produkty ";
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
        Koszyk_produkty koszyk_produkty1 = new Koszyk_produkty(0,1,1,12);


        Integer newIdKoszyk_produkty = saveKoszyk_produkty(koszyk_produkty1);


        System.out.println("Zapisany rekord, id: " + newIdKoszyk_produkty);

        List<Koszyk_produkty> results = getKoszyk_produkty();
        System.out.println("\nIlość rekordów w tabeli Koszyk_produkty to: " +results.size());
        for(int i=0; i< results.size(); i++){
            System.out.println("Koszyk_produkty " + i+ ": " + results.get(i));
        }
    }

    public static void main(String[] args) {
        Koszyk_produktyCRUD koszyk_produktyCRUD = new Koszyk_produktyCRUD();
        koszyk_produktyCRUD.run();
    }
}
