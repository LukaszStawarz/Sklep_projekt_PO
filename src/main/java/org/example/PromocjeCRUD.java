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

public class PromocjeCRUD {
    private SessionFactory sessionFactory;
    public PromocjeCRUD() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
    }

    public Integer savePromocje(Promocje promocje){ // Dodawanie rekordów do tabeli
        Session session = null;
        Integer idPromocje=null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            idPromocje = (Integer) session.save(promocje);
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return idPromocje;
    }

    public Promocje getPromocje(Integer idPromocje) {  //odczytanie ostatnio dodanych danych
        Session session = null;
        Promocje promocje = null;
        try{
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Promocje P WHERE P.idPromocje = " +idPromocje;
            Query query = session.createQuery(hql);
            List results = query.list();

            if(results.size() >0) promocje = (Promocje) results.get(0);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            session.close();
        }
        return promocje;
    }

    public List getPromocje() {  //odczytanie danych z tablicy
        Session session = null;
        List results = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            String hql = "FROM Promocje ";
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
        Promocje promocje1 = new Promocje(0,"10% zniżki",10,50);
        Promocje promocje2 = new Promocje(0,"20% zniżki",20,100);
        Promocje promocje3 = new Promocje(0,"30% zniżki",30,200);


        Integer newIdPromocje1 = savePromocje(promocje1);
        Integer newIdPromocje2 = savePromocje(promocje2);
        Integer newIdPromocje3 = savePromocje(promocje3);


        System.out.println("Zapisany rekord, id: " + newIdPromocje1);

        List<Promocje> results = getPromocje();
        System.out.println("\nIlość rekordów w tabeli Promocje to: " +results.size());
        for(int i=0; i< results.size(); i++){
            System.out.println("Promocje " + i+ ": " + results.get(i));
        }
    }

    public static void main(String[] args) {
        PromocjeCRUD promocjeCRUD = new PromocjeCRUD();
        promocjeCRUD.run();
    }

}
