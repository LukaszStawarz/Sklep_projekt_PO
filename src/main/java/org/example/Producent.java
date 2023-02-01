package org.example;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "producent")
public class Producent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int idProducent;
    private String nazwaProducent;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name= "idProducent")
    private List<Produkty> produkty;

    public Producent() {
    }

    public Producent(int idProducent, String nazwaProducent) {
        this.idProducent = idProducent;
        this.nazwaProducent = nazwaProducent;
    }

    public int getIdProducent() {
        return idProducent;
    }

    public void setIdProducent(int idProducent) {
        this.idProducent = idProducent;
    }

    public String getNazwaProducent() {
        return nazwaProducent;
    }

    public void setNazwaProducent(String nazwaProducent) {
        this.nazwaProducent = nazwaProducent;
    }

    @Override
    public String toString() {
        return "Producent{" +
                "idProducent=" + idProducent +
                ", nazwaProducent='" + nazwaProducent + '\'' +
                '}';
    }
}
