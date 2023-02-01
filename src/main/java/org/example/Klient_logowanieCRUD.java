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

public class Klient_logowanieCRUD {
    private SessionFactory sessionFactory;
    public Klient_logowanieCRUD() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
    }
    // Rejestracja
    public Integer saveKlient_logowanie(Klient_logowanie klient_logowanie){ // Dodawanie rekordów do tabeli
        Session session = null;
        Integer idKlient_logowanie=null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            idKlient_logowanie = (Integer) session.save(klient_logowanie);
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return idKlient_logowanie;
    }

    public Klient_logowanie checkIfKlientExists(String login, String password){
        Session session = null;
        Klient_logowanie klient_logowanie = null;
        try{
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Klient_logowanie WHERE login = '" + login +"' AND haslo = '" + password + "'";
            Query query = session.createQuery(hql);
            List results = query.list();
            if(results.size() >0) klient_logowanie = (Klient_logowanie) results.get(0);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            session.close();
        }
        return klient_logowanie;
    }

    public Klient_logowanie getKlient_logowanie(Integer idKlient_logowanie) {  //odczytanie ostatnio dodanych danych
        Session session = null;
        Klient_logowanie klient_logowanie = null;
        try{
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Klient_logowanie WHERE idKlient_logowanie = " +idKlient_logowanie;
            Query query = session.createQuery(hql);
            List results = query.list();

            if(results.size() >0) klient_logowanie = (Klient_logowanie) results.get(0);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            session.close();
        }
        return klient_logowanie;
    }
    public List getKlient_logowanie() {  //odczytanie danych z tablicy
        Session session = null;
        List results = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            String hql = "FROM Klient_logowanie ";
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
        Klient_logowanie klient_logowanie1 = new Klient_logowanie(0,"aadmin","aadmin");


        Integer newIdKlient_logowanie1 = saveKlient_logowanie(klient_logowanie1);


        System.out.println("Zapisany rekord, id: " + newIdKlient_logowanie1);

        List<Klient_logowanie> results = getKlient_logowanie();
        System.out.println("\nIlość rekordów w tabeli Klient_logowanie to: " +results.size());
        for(int i=0; i< results.size(); i++){
            System.out.println("Klient_logowanie " + i+ ": " + results.get(i));
        }
    }

    public static void main(String[] args) {
        Klient_logowanieCRUD klient_logowanieCRUD = new Klient_logowanieCRUD();
        klient_logowanieCRUD.run();
    }
}
