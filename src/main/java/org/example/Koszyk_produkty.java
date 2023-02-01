package org.example;

import javax.persistence.*;

@Entity
@Table (name = "koszyk_produkty")
public class Koszyk_produkty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int idKoszyk_produkty;
    private int idProdukty;
    private int idKoszyk;
    private int ilosc;

    public Koszyk_produkty() {
    }

    public Koszyk_produkty(int idProdukty, int idKoszyk, int ilosc) {
        this.idProdukty = idProdukty;
        this.idKoszyk = idKoszyk;
        this.ilosc = ilosc;
    }

    public Koszyk_produkty(int idKoszyk_produkty, int idProdukty, int idKoszyk, int ilosc) {
        this.idKoszyk_produkty = idKoszyk_produkty;
        this.idProdukty = idProdukty;
        this.idKoszyk = idKoszyk;
        this.ilosc = ilosc;
    }

    public int getIdKoszyk_produkty() {
        return idKoszyk_produkty;
    }

    public void setIdKoszyk_produkty(int idKoszyk_produkty) {
        this.idKoszyk_produkty = idKoszyk_produkty;
    }

    public int getIdProdukty() {
        return idProdukty;
    }

    public void setIdProdukty(int idProdukty) {
        this.idProdukty = idProdukty;
    }

    public int getIdKoszyk() {
        return idKoszyk;
    }

    public void setIdKoszyk(int idKoszyk) {
        this.idKoszyk = idKoszyk;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    @Override
    public String toString() {
        return "Koszyk_produkty{" +
                "idKoszyk_produkty=" + idKoszyk_produkty +
                ", idProdukty=" + idProdukty +
                ", idKoszyk=" + idKoszyk +
                ", ilosc=" + ilosc +
                '}';
    }
}
