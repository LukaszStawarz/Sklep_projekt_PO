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

public class ProducentCRUD {
    private SessionFactory sessionFactory;
    public ProducentCRUD() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
    }

    public Integer saveProducent(Producent producent){ // Dodawanie rekordów do tabeli
        Session session = null;
        Integer idProducent=null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            idProducent = (Integer) session.save(producent);
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return idProducent;
    }

    public Producent getProducent(Integer idProducent) {  //odczytanie ostatnio dodanych danych
        Session session = null;
        Producent producent = null;
        try{
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Producent P WHERE P.idProducent = " +idProducent;
            Query query = session.createQuery(hql);
            List results = query.list();

            if(results.size() >0) producent = (Producent) results.get(0);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            session.close();
        }
        return producent;
    }

    public List getProducent() {  //odczytanie danych z tablicy
        Session session = null;
        List results = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            String hql = "FROM Producent ";
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
        Producent producent1 = new Producent(0,"FruitPOL");
        Producent producent2 = new Producent( 0, "PiekarniaFrania");
        Producent producent3 = new Producent( 0, "RzeźniaMichalik");
        Producent producent4 = new Producent( 0, "PolskaMleczarnia");
        Producent producent5 = new Producent( 0, "Domestos");


        Integer newIdProducent1 = saveProducent(producent1);
        Integer newIdProducent2 = saveProducent(producent2);
        Integer newIdProducent3 = saveProducent(producent3);
        Integer newIdProducent4 = saveProducent(producent4);
        Integer newIdProducent5 = saveProducent(producent5);

        System.out.println("Zapisany rekord, id: " + newIdProducent1+" , " +newIdProducent2+" , "+ newIdProducent3+" , "+newIdProducent4+ " , "+newIdProducent5);

        List<Producent> results = getProducent();
        System.out.println("\nIlość rekordów w tabeli Producent to: " +results.size());
        for(int i=0; i< results.size(); i++){
            System.out.println("Producent " + i+ ": " + results.get(i));
        }
    }

    public static void main(String[] args) {
        ProducentCRUD producentCRUD = new ProducentCRUD();
        producentCRUD.run();
    }

}
