package org.example;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.util.logging.Level;

public class Main {
    public static void wypiszPrzygotowanieZamowienia() {
        PracownicyCRUD pracownicyCRUD = new PracownicyCRUD();
        List<Pracownicy> pracownicy1 = pracownicyCRUD.getPracownicy();
        Random random = new Random();
        int index = random.nextInt(pracownicy1.size());
        Pracownicy prac = pracownicy1.get(index);
        System.out.println("Dziękujemy za zakupy, nasz pracownik " + prac.getImie() + " już przygotowuje zamówienie. Szacowany czas oczekiwania 15 min. ");
    }

    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE); // Schowanie warningow z hibernate
        Scanner scanner = new Scanner(System.in);

        // Do przetrzymywania danych klienta po zalogowaniu
        Klient_logowanie daneKlienta = null;

        // Stworzenie zmiennej która obsługuje CRUD do logowania
        Klient_logowanieCRUD klient_logowanieCRUD = new Klient_logowanieCRUD();

        // Petla obsługująca logowanie i rejestracje
        while (true) {
            System.out.println("Jeśli chcesz sie zalogować wcisnij 1 jeśli zarejestrować wciśnij 2");
            int wyborklienta = scanner.nextInt();
            if (wyborklienta == 1) {
                System.out.println("Podaj login");
                String login = scanner.next(); // Pobieranie wartosciu loginu z terminala
                // Sprawdzenie czy hasło jest dluzsze niz 5
                if (login.length() < 6) {
                    System.out.println("Podałeś za krótki login");
                    continue; // Skip i od nowa bo za krotki login
                }

                System.out.println("Podaj hasło");
                String haslo = scanner.next();
                if (haslo.length() < 6) {
                    System.out.println("Podałeś za krótkie hasło");
                    continue;
                } // To samo co wyzej

                // Sprawdzenie czy podane dane zgadzają się z kimkolwiek w bazie.
                Klient_logowanie klient_logowanie = klient_logowanieCRUD.checkIfKlientExists(login, haslo);
                if (klient_logowanie != null) {
                    daneKlienta = klient_logowanie; // Przypisanie danych do zmiennej wyzej aby miec dostep
                    System.out.println("Zalogowałeś się pomyślnie. Życzymy udanych zakupów. ");
                    break;
                } else {
                    continue;
                }
            } else if (wyborklienta == 2) {
                System.out.println("Podaj login (co najmniej 6 znaków)");
                String login = scanner.next();
                if (login.length() < 6) {
                    System.out.println("Podałeś za krótki login");
                    continue;
                }
                System.out.println("Podaj hasło (co najmniej 6 znaków)");
                String haslo = scanner.next();
                if (haslo.length() < 6) {
                    System.out.println("Podałeś za krótkie hasło");
                    continue;
                }
                int x = klient_logowanieCRUD.saveKlient_logowanie(new Klient_logowanie(login, haslo));
                if (x > 0) {
                    System.out.println("Pomyślnie się zarejestrowałeś");
                    continue;
                }
            } else {
                System.out.println("Podałeś złą wartość");
                continue;
            }
        }

        // Pobieramy produkty z bazy i przypisujemy do zmiennej produkty
        ProduktyCRUD produktyCRUD = new ProduktyCRUD();
        List<Produkty> produkty1 = produktyCRUD.getProdukty();

        // STWORZENIE KOSZYKA, ZMIENNA ID KOSZYK
        KoszykCRUD koszykCRUD1 = new KoszykCRUD();
        int idKoszyk = koszykCRUD1.saveKoszyk(new Koszyk(daneKlienta.getIdKlient_logowanie()));

        System.out.println("Numer zamówienia : " + idKoszyk);
        Kategoria_produktuCRUD kategoria_produktuCRUD = new Kategoria_produktuCRUD();
        List<Kategoria_produktu> kategoria_produktu1 = kategoria_produktuCRUD.getKategoria_produktu();
        Kategoria_produktu kategoria_produktu = null;
        while (true) {

            System.out.println("Podaj numer kategorii którą chcesz wybrać");
            for (int i = 0; i < kategoria_produktu1.size(); i++) {
                System.out.println(i + " . " + kategoria_produktu1.get(i).getNazwaKategorii());
            }
            int wybor1 = scanner.nextInt();
            kategoria_produktu = kategoria_produktu1.get(wybor1);
            produkty1 = produktyCRUD.getProduktybyIdKategoria(kategoria_produktu.getIdKategoria_produktu());

            // Wypisywanie listy produktow
            System.out.println("Wybierz produkt który chcesz dodać do koszyka");
            for (int i = 0; i < produkty1.size(); i++) {
                System.out.println(i + ". " + produkty1.get(i).getNazwa_produktu() + " " + produkty1.get(i).getCena() + " Zl");
            }
            int wybor = scanner.nextInt();
            if (wybor >= produkty1.size()) {
                System.out.println("nie ma takiego produktu");
                continue;
            }
            Produkty produkt = produkty1.get(wybor);


            System.out.print("Podaj ilość produktu " + wybor + ". " + produkt.getNazwa_produktu() + " jaką chcesz dodać do koszyka: ");
            int ilosc = scanner.nextInt();
            if (ilosc <= 0) {
                System.out.println("podałeś ilość mniejszą bądź równą zero");
                continue;
            } else {
                System.out.println("Wybrałeś " + wybor + ". " + produkt.getNazwa_produktu() + " w ilości " + ilosc);
            }

            System.out.println("Wciśnij 1 by kontunuować zakupy, wciśnij 2 by przejść do podsumowania. ");
            int decyzja = scanner.nextInt();

            // ZAPISANIE PRODUKTU DO KOSZYKA W BAZIE
            Koszyk_produkty koszyk_produkty = new Koszyk_produkty(produkt.getIdProdukty(), idKoszyk, ilosc);
            Koszyk_produktyCRUD koszyk_produktyCRUD = new Koszyk_produktyCRUD();
            koszyk_produktyCRUD.saveKoszyk_produkty(koszyk_produkty);

            if (decyzja == 1) {
                continue;
            } else if (decyzja == 2) {
                break;
            }
        }
        ProduktyCRUD produktyCRUD2 = new ProduktyCRUD();

        Koszyk_produktyCRUD koszyk_produktyCRUD1 = new Koszyk_produktyCRUD();
        List<Koszyk_produkty> koszyk_produkties = koszyk_produktyCRUD1.getKoszyk_produktyByKoszykId(idKoszyk);

        //System.out.println(koszyk_produkties);
        while (true) {
            System.out.println("Twoje zamówienie");
            for (int i = 0; i < koszyk_produkties.size(); i++) {
                int idProduktu = koszyk_produkties.get(i).getIdProdukty();
                Produkty produkty2 = produktyCRUD2.getProdukty(idProduktu);
                double cenaProduktu = produkty2.getCena();
                int wagaProduktu = produkty2.getWaga();
                String nazwy1 = produkty2.getNazwa_produktu();
                System.out.println(nazwy1 + " " + wagaProduktu + " g\t-\t " + koszyk_produkties.get(i).getIlosc() + " szt\t-\t " + cenaProduktu + " zł/szt");
            }
            break;
        }

        PromocjeCRUD promocjeCRUD = new PromocjeCRUD();
        List<Promocje> promocje1 = promocjeCRUD.getPromocje();// sortowanie po progu żeby element 0 mial najwiekszy prog

        // Sortowanie listy żeby była od najwiekszego progu promocji
        Collections.sort(promocje1, (s1, s2) -> {
            if (s1.getProgPromocji() < s2.getProgPromocji()) {
                return 1;
            } else {
                return -1;
            }
        });

        double suma = 0;
        for (Koszyk_produkty koszyk_produkt : koszyk_produkties) {
            int productId = koszyk_produkt.getIdProdukty();
            Produkty produkt = produktyCRUD.getProdukty(productId);
            double cenaProduktu = produkt.getCena();
            int iloscProduktow = koszyk_produkt.getIlosc();
            suma += cenaProduktu * iloscProduktow;
        }
        System.out.println("Łączny koszt zamówienia wynosi: " + suma);
        // Iteracja po liście promocji i sprawdzenie czy suma spełnia warunek progu promocji od największej

        for (Promocje promocja : promocje1) {
            if (promocja.getProgPromocji() < suma) {
                suma = suma - (suma * promocja.getValue() / 100);
                break;
            }
        }
        System.out.println("Łączny koszt zamówienia po naliczeniu promocji wynosi:" + suma);

        //płatność,podsumowanie,losowy pracownik
        while (true) {
            System.out.println("Wybierz sposób płatności(gotówką - 1, kartą - 2)");     //płatność gotówką będzie przyjmować kwotę i wyliczać resztę a płatność kartą przyjmuje dokładną kwotę zamówienia
            int wybor1 = scanner.nextInt();
            if (wybor1 == 1) {
                System.out.println("Wprowadź kwotę jaką chcesz zapłacić. ");
                double reszta = 0;
                double platnosc = scanner.nextDouble();
                if (platnosc < suma) {
                    System.out.println("Wprowadzona kwota jest za mała. ");
                    continue;
                } else if (platnosc >= suma) {
                    reszta = platnosc - suma;
                    System.out.println("Reszta z płatności wynosi: " + Math.round(reszta * 100.0) / 100.0 + " zł.");
                    wypiszPrzygotowanieZamowienia();
                    break;
                }
            } else if (wybor1 == 2) {
                wypiszPrzygotowanieZamowienia();
                break;
            }
        }
    }
}




