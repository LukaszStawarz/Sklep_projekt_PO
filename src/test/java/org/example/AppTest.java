package org.example;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest
    extends TestCase
{
    public void testDodajProdukt() {
        Produkty produkty24 = new Produkty(0,5,5,"Odświeżacz powietrza",1,13.99);
        ProduktyCRUD produktyCRUD = new ProduktyCRUD();
        int prodId = produktyCRUD.saveProdukty(produkty24);
        assertNotNull(prodId);
    }

    public void testGetProduktList() {
        ProduktyCRUD produktyCRUD = new ProduktyCRUD();
        List<Produkty> list =produktyCRUD.getProdukty();
        assertTrue(!list.isEmpty());
    }

    public void testLoginAndRegister() {
        Klient_logowanie klient_logowanie = new Klient_logowanie("test123", "test213");
        Klient_logowanieCRUD klient_logowanieCRUD = new Klient_logowanieCRUD();
        int id =  klient_logowanieCRUD.saveKlient_logowanie(klient_logowanie);
        assertNotNull(id);

        //check if user can login
        Klient_logowanie klient = klient_logowanieCRUD.checkIfKlientExists("test123", "test213");
        assertNotNull(klient);
    }

    public void testPromocje(){
        PromocjeCRUD promocjeCRUD = new PromocjeCRUD();
        List<Promocje> list = promocjeCRUD.getPromocje();
        assertNotNull(list.isEmpty());
    }
    public void testZamówienia(){
        ZamowieniaCRUD zamowieniaCRUD = new ZamowieniaCRUD();
        List<Zamowienia> list = zamowieniaCRUD.getZamowienia();
        assertNotNull(zamowieniaCRUD);
    }
    public void testProducent() {
        ProducentCRUD producentCRUD = new ProducentCRUD();
        List<Producent> list = producentCRUD.getProducent();
        assertNotNull(producentCRUD);
    }
    public void testPracownik(){
        PracownicyCRUD pracownicyCRUD = new PracownicyCRUD();
        List<Pracownicy> list = pracownicyCRUD.getPracownicy();
        assertNotNull(pracownicyCRUD);
    }
    public void testKategorie(){
        Kategoria_produktuCRUD kategoria_produktuCRUD = new Kategoria_produktuCRUD();
        List<Kategoria_produktu> list = kategoria_produktuCRUD.getKategoria_produktu();
        assertNotNull(kategoria_produktuCRUD);
    }

}


