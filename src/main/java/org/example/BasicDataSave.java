package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class BasicDataSave {
    public static void main(String[] args) {

        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Kategoria_produktuCRUD kategoria_produktuCRUD = new Kategoria_produktuCRUD();
        kategoria_produktuCRUD.run();


        Klient_logowanieCRUD klient_logowanieCRUD = new Klient_logowanieCRUD();
        klient_logowanieCRUD.run();

        Koszyk_produktyCRUD koszyk_produktyCRUD = new Koszyk_produktyCRUD();
        koszyk_produktyCRUD.run();

        KoszykCRUD koszykCRUD = new KoszykCRUD();
        koszykCRUD.run();

        PracownicyCRUD pracownicyCRUD = new PracownicyCRUD();
        pracownicyCRUD.run();

        ProducentCRUD producentCRUD = new ProducentCRUD();
        producentCRUD.run();

        ProduktyCRUD produktyCRUD = new ProduktyCRUD();
        produktyCRUD.run();

        PromocjeCRUD promocjeCRUD = new PromocjeCRUD();
        promocjeCRUD.run();

        ZamowieniaCRUD zamowieniaCRUD = new ZamowieniaCRUD();
        zamowieniaCRUD.run();
    }
}
