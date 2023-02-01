package org.example;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name= "produkty")
public class Produkty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int idProdukty;
    private int idKategoria_produktu;
    private int idProducent;
    private String nazwa_produktu;
    private int waga;
    private double cena;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idProdukty")
    private List<Koszyk_produkty> koszyk_produkty;

    public Produkty() {
    }

    public Produkty(int idProdukty, int idKategoria_produktu, int idProducent, String nazwa_produktu, int waga, double cena) {
        this.idProdukty = idProdukty;
        this.idKategoria_produktu = idKategoria_produktu;
        this.idProducent = idProducent;
        this.nazwa_produktu = nazwa_produktu;
        this.waga = waga;
        this.cena = cena;
    }




    public int getIdProdukty() {
        return idProdukty;
    }

    public void setIdProdukty(int idProdukty) {
        this.idProdukty = idProdukty;
    }

    public int getIdKategoria_produktu() {
        return idKategoria_produktu;
    }

    public void setIdKategoria_produktu(int idKategoria_produktu) {
        this.idKategoria_produktu = idKategoria_produktu;
    }

    public int getIdProducent() {
        return idProducent;
    }

    public void setIdProducent(int idProducent) {
        this.idProducent = idProducent;
    }

    public String getNazwa_produktu() {
        return nazwa_produktu;
    }

    public void setNazwa_produktu(String nazwa_produktu) {
        this.nazwa_produktu = nazwa_produktu;
    }

    public int getWaga() {
        return waga;
    }

    public void setWaga(int waga) {
        this.waga = waga;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "Produkty{" +
                "idProdukty=" + idProdukty +
                ", idKategoria_produktu=" + idKategoria_produktu +
                ", idProducent=" + idProducent +
                ", nazwa_produktu='" + nazwa_produktu + '\'' +
                ", waga=" + waga +
                ", cena=" + cena +
                '}';
    }
}
