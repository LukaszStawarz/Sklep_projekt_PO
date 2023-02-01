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

public class ZamowieniaCRUD {

    private SessionFactory sessionFactory;
    public ZamowieniaCRUD() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
    }
    public Integer saveZamowienia(Zamowienia zamowienia){ // Dodawanie rekordów do tabeli
        Session session = null;
        Integer idZamowienia=null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            idZamowienia = (Integer) session.save(zamowienia);
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return idZamowienia;
    }
    public Zamowienia getZamowienia(Integer idZamowienia) {  //odczytanie ostatnio dodanych danych
        Session session = null;
        Zamowienia zamowienia = null;
        try{
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Zamowienia Z WHERE Z.idZamowienia = " +idZamowienia;
            Query query = session.createQuery(hql);
            List results = query.list();

            if(results.size() >0) zamowienia = (Zamowienia) results.get(0);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            session.close();
        }
        return zamowienia;
    }
    public List getZamowienia() {  //odczytanie danych z tablicy
        Session session = null;
        List results = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            String hql = "FROM Zamowienia ";
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
        KoszykCRUD koszykCRUD = new KoszykCRUD();
        Koszyk koszyk1 = koszykCRUD.getKoszyk(1);

        Zamowienia zamowienia1 = new Zamowienia(1,1,koszyk1);

        Integer newIdZamowienia1 = saveZamowienia(zamowienia1);

        System.out.println("Zapisany rekord, id: " + newIdZamowienia1);

        List<Zamowienia> results = getZamowienia();
        System.out.println("\nIlość rekordów w tabeli Zamowienia to: " +results.size());
        for(int i=0; i< results.size(); i++){
            System.out.println("Zamowienia " + i+ ": " + results.get(i));
        }
    }

    public static void main(String[] args) {
        ZamowieniaCRUD zamowieniaCRUD = new ZamowieniaCRUD();
        zamowieniaCRUD.run();
    }
}
