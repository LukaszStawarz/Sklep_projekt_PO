package org.example;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name= "kategoria_produktu")
public class Kategoria_produktu {

    public Kategoria_produktu(String nazwaKategorii) {
        this.nazwaKategorii = nazwaKategorii;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idKategoria_produktu;
    private String nazwaKategorii;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idKategoria_produktu")
    private List<Produkty> produkty;

    public Kategoria_produktu() {
    }

    public Kategoria_produktu(int idKategoria_produktu, String nazwaKategorii) {
        this.idKategoria_produktu = idKategoria_produktu;
        this.nazwaKategorii = nazwaKategorii;
    }

    public int getIdKategoria_produktu() {
        return idKategoria_produktu;
    }

    public void setIdKategoria_produktu(int idKategoria_produktu) {
        this.idKategoria_produktu = idKategoria_produktu;
    }

    public String getNazwaKategorii() {
        return nazwaKategorii;
    }

    public void setNazwaKategorii(String nazwaKategorii) {
        this.nazwaKategorii = nazwaKategorii;
    }

    @Override
    public String toString() {
        return "Kategoria_produktu{" +
                "idKategoria_produktu=" + idKategoria_produktu +
                ", nazwaKategorii='" + nazwaKategorii + '\'' +
                '}';
    }
}

