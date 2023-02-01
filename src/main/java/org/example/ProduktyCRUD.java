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

public class ProduktyCRUD {
    private SessionFactory sessionFactory;
    public ProduktyCRUD() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
    }
    public Integer saveProdukty(Produkty produkty){ // Dodawanie rekordów do tabeli
        Session session = null;
        Integer idProdukty=null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            idProdukty = (Integer) session.save(produkty);
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return idProdukty;
    }
    public Produkty getProdukty(Integer idProdukty) {  //odczytanie ostatnio dodanych danych
        Session session = null;
        Produkty produkty = null;
        try{
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Produkty WHERE idProdukty = " +idProdukty;
            Query query = session.createQuery(hql);
            List results = query.list();

            if(results.size() >0) produkty = (Produkty) results.get(0);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            session.close();
        }
        return produkty;
    }
    public List getProdukty() {
        Session session = null;
        List results = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            String hql = "FROM Produkty ";
            Query query = session.createQuery(hql);
            results = query.list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }
    public List getProduktybyIdKategoria(Integer idKategoria_produktu) {
        Session session = null;
        List results = null;
        try{
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Produkty WHERE idKategoria_produktu = " +idKategoria_produktu;
            Query query = session.createQuery(hql);
            results = query.list();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            session.close();
        }
        return results;
    }

    public void run(){
        Produkty produkty1 = new Produkty(0,1,1,"Banany",500,6.99);
        Produkty produkty2 = new Produkty(0,1,1,"Truskawki",500,8.99);
        Produkty produkty3 = new Produkty(0,1,1,"Pomidory",500,5.99);
        Produkty produkty4 = new Produkty(0,1,1,"Pomarańcza",500,6.99);
        Produkty produkty5 = new Produkty(0,1,1,"Ziemniaki",1000,5.00);
        Produkty produkty6 = new Produkty(0,2,2,"Chleb pszenny",1,5.55);
        Produkty produkty7 = new Produkty(0,2,2,"Chleb żytni",1,6.00);
        Produkty produkty8 = new Produkty(0,2,2,"Chleb razowy",1,5.00);
        Produkty produkty9 = new Produkty(0,2,2,"Chleb tostowy",1,5.12);
        Produkty produkty10 = new Produkty(0,3,3,"Szynka drobiowa",300,8.59);
        Produkty produkty11 = new Produkty(0,3,3,"Polędwica",300,9.00);
        Produkty produkty12 = new Produkty(0,3,3,"Szynka konserwowa",300,5.59);
        Produkty produkty13 = new Produkty(0,3,3,"Pierś z kurczaka",500,8.99);
        Produkty produkty14 = new Produkty(0,3,3,"Schab",500,8.99);
        Produkty produkty15 = new Produkty(0,4,4,"Jajka 12szt",1,6.00);
        Produkty produkty16 = new Produkty(0,4,4,"Mleko 1L",1,4.99);
        Produkty produkty17 = new Produkty(0,4,4,"Ser biały",300,5.12);
        Produkty produkty18 = new Produkty(0,4,4,"Ser żółty",500,10.00);
        Produkty produkty19 = new Produkty(0,5,5,"Płyn do mycia naczyń",1,11.99);
        Produkty produkty20 = new Produkty(0,5,5,"Płyn do szyb",1,7.99);
        Produkty produkty21 = new Produkty(0,5,5,"Płyn do mebli",1,5.99);
        Produkty produkty22 = new Produkty(0,5,5,"Kostki WC 2szt",1,8.99);
        Produkty produkty23 = new Produkty(0,5,5,"Kapsułki do prania",1,25.99);
        Produkty produkty24 = new Produkty(0,5,5,"Odświeżacz powietrza",1,13.99);


        Integer newIdProdukty = saveProdukty(produkty1);
        Integer newIdProdukty2 = saveProdukty(produkty2);
        Integer newIdProdukty3 = saveProdukty(produkty3);
        Integer newIdProdukty4 = saveProdukty(produkty4);
        Integer newIdProdukty5 = saveProdukty(produkty5);
        Integer newIdProdukty6 = saveProdukty(produkty6);
        Integer newIdProdukty7= saveProdukty(produkty7);
        Integer newIdProdukty8 = saveProdukty(produkty8);
        Integer newIdProdukty9 = saveProdukty(produkty9);
        Integer newIdProdukty10 = saveProdukty(produkty10);
        Integer newIdProdukty11 = saveProdukty(produkty11);
        Integer newIdProdukty12 = saveProdukty(produkty12);
        Integer newIdProdukty13 = saveProdukty(produkty13);
        Integer newIdProdukty14 = saveProdukty(produkty14);
        Integer newIdProdukty15 = saveProdukty(produkty15);
        Integer newIdProdukty16 = saveProdukty(produkty16);
        Integer newIdProdukty17 = saveProdukty(produkty17);
        Integer newIdProdukty18 = saveProdukty(produkty18);
        Integer newIdProdukty19 = saveProdukty(produkty19);
        Integer newIdProdukty20 = saveProdukty(produkty20);
        Integer newIdProdukty21 = saveProdukty(produkty21);
        Integer newIdProdukty22 = saveProdukty(produkty22);
        Integer newIdProdukty23 = saveProdukty(produkty23);
        Integer newIdProdukty24 = saveProdukty(produkty24);


        System.out.println("Zapisany rekord, id: " + newIdProdukty);

        List<Produkty> results = getProdukty();
        System.out.println("\nIlość rekordów w tabeli Produkty to: " +results.size());
        for(int i=0; i< results.size(); i++){
            System.out.println("Produkty " + i+ ": " + results.get(i));
        }
    }

    public static void main(String[] args) {
        ProduktyCRUD produktyCRUD = new ProduktyCRUD();
        produktyCRUD.run();
    }
}
