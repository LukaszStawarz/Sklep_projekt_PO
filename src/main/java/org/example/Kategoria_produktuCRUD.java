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

public class Kategoria_produktuCRUD {

    private SessionFactory sessionFactory;
    public Kategoria_produktuCRUD() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
    }

    public Integer saveKategoria_produktu(Kategoria_produktu kategoria_produktu){ // Dodawanie rekordów do tabeli
        Session session = null;
        Integer idKategoria_produktu=null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            idKategoria_produktu = (Integer) session.save(kategoria_produktu);
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return idKategoria_produktu;
    }

    public Kategoria_produktu getKategoria_produktu(Integer idKategoria_produktu) {  //odczytanie ostatnio dodanych danych
        Session session = null;
        Kategoria_produktu kategoria_produktu = null;
        try{
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Kategoria_produktu K WHERE K.idKategoria_produktu = " +idKategoria_produktu;
            Query query = session.createQuery(hql);
            List results = query.list();

            if(results.size() >0) kategoria_produktu = (Kategoria_produktu) results.get(0);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            session.close();
        }
        return kategoria_produktu;
    }

    public List getKategoria_produktu() {  //odczytanie danych z tablicy
        Session session = null;
        List results = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            String hql = "FROM Kategoria_produktu ";
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
        Kategoria_produktu kategoria_produktu1 = new Kategoria_produktu(1,"Owoce/Warzywa");
        Kategoria_produktu kategoria_produktu2 = new Kategoria_produktu(2,"Pieczywo");
        Kategoria_produktu kategoria_produktu3 = new Kategoria_produktu(3,"Mięsa");
        Kategoria_produktu kategoria_produktu4 = new Kategoria_produktu(4,"Nabiały");
        Kategoria_produktu kategoria_produktu5 = new Kategoria_produktu(5,"Artykuły chemiczne");


        Integer newIdKategoria_produktu1 = saveKategoria_produktu(kategoria_produktu1);
        Integer newIdKategoria_produktu2 = saveKategoria_produktu(kategoria_produktu2);
        Integer newIdKategoria_produktu3 = saveKategoria_produktu(kategoria_produktu3);
        Integer newIdKategoria_produktu4 = saveKategoria_produktu(kategoria_produktu4);
        Integer newIdKategoria_produktu5 = saveKategoria_produktu(kategoria_produktu5);


        System.out.println("Zapisany rekord, id: " + newIdKategoria_produktu1);

        List<Kategoria_produktu> results = getKategoria_produktu();
        System.out.println("\nIlość rekordów w tabeli Kategoria_produktu to: " +results.size());
        for(int i=0; i< results.size(); i++){
            System.out.println("Kategorie_produktu " + i+ ": " + results.get(i));
        }
    }

    public static void main(String[] args) {
        Kategoria_produktuCRUD kategoria_produktuCRUD = new Kategoria_produktuCRUD();
        kategoria_produktuCRUD.run();
    }

}

