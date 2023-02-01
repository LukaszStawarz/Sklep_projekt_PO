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

public class PracownicyCRUD {

    private SessionFactory sessionFactory;
    public PracownicyCRUD() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
    }

    public Integer savePracownicy(Pracownicy pracownicy){ // Dodawanie rekordów do tabeli
        Session session = null;
        Integer idPracownicy=null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            idPracownicy = (Integer) session.save(pracownicy);
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return idPracownicy;
    }

    public Pracownicy getPracownicy(Integer idPracownicy) {  //odczytanie ostatnio dodanych danych
        Session session = null;
        Pracownicy pracownicy = null;
        try{
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            String hql = "FROM Pracownicy P WHERE P.idPracownicy = " +idPracownicy;
            Query query = session.createQuery(hql);
            List results = query.list();

            if(results.size() >0) pracownicy = (Pracownicy) results.get(0);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            session.close();
        }
        return pracownicy;
    }

    public List getPracownikow() {  //odczytanie danych z tablicy
        Session session = null;
        List results = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            String hql = "FROM Pracownicy ";
            Query query = session.createQuery(hql);
            results = query.list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }
    public List getPracownicy() {  //odczytanie danych z tablicy
        Session session = null;
        List results = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            String hql = "FROM Pracownicy ";
            Query query = session.createQuery(hql);
            results = query.list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }
    /*public void updatePracownicy(Integer idPracownicy,String Imie) {  //update danych do tabeli
        Session session = null;
        Transaction transaction = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Pracownicy pracownicy = (Pracownicy) session.get(Pracownicy.class, idPracownicy);
            pracownicy.setImie(Imie);

            session.update(pracownicy);
            transaction.commit();

        }catch(Exception e){
            e.printStackTrace();
            if(transaction !=null)transaction.rollback();
        }finally{
            if(session !=null) session.close();
        }
    }*/

  /*  public void deletePracownicy(Integer idPracownicy) {  //usuwanie danych z tabeli
        Session session = null;
        Transaction transaction = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Pracownicy pracownicy = (Pracownicy) session.get(Pracownicy.class, idPracownicy);

            session.delete(pracownicy);
            transaction.commit();

        }catch(Exception e){
            e.printStackTrace();
            if(transaction !=null)transaction.rollback();
        }finally{
            if(session !=null) session.close();
        }
    }*/
    public void run(){
        Pracownicy pracownicy = new Pracownicy(0,"Adam",
                "Kowalski",901225565);
                Pracownicy pracownicy2 = new Pracownicy(0,"Kamila",
                "Milik",940406256);
                Pracownicy pracownicy3 = new Pracownicy(0,"Kamil",
                "Wawrzyn",980916998);
                Pracownicy pracownicy4 = new Pracownicy(0,"Monika",
                "Ksztoń",930227947);

        Integer newIdPracownicy = savePracownicy(pracownicy);
        Integer newIdPracownicy2 = savePracownicy(pracownicy2);
        Integer newIdPracownicy3 = savePracownicy(pracownicy3);
        Integer newIdPracownicy4 = savePracownicy(pracownicy4);
        System.out.println("Zapisany rekord, id: " + newIdPracownicy+" , " +newIdPracownicy2+" , "+ newIdPracownicy3+" , "+newIdPracownicy4);

        /*Pracownicy savedPracownicy = getPracownicy(newIdPracownicy);
        System.out.println("\nOstatni zapisany pracownik: "+savedPracownicy);*/

        //updatePracownicy(newIdPracownicy,"Tomek");

        List<Pracownicy> results = getPracownikow();
        System.out.println("\nIlość rekordów w tabeli pracownicy to: " +results.size());
        for(int i=0; i< results.size(); i++){
            System.out.println("Pracownik " + i+ ": " + results.get(i));
        }
        /*System.out.println("\nSkasowanie pracownika o id: "+results.get(0).getIdPracownicy());  //Usuwanie pierwszego rekordu w tabeli
        deletePracownicy(results.get(0).getIdPracownicy());
        System.out.println("\nStan po skasowaniu: ");
        results = getPracownikow();
        System.out.println("\nIlość rekordów w tabeli pracownicy to: "+results.size());
        for (int i=0; i< results.size();i++) {
            System.out.println("Pracownik " + i + ": " + results.get(i));
        }*/
    }


    public static void main(String[] args) {

        PracownicyCRUD pracownicyCRUD = new PracownicyCRUD();
        pracownicyCRUD.run();

    }
}
