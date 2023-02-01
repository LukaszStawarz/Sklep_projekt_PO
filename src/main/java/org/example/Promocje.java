package org.example;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "promocje")

public class Promocje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPromocje;
    private String nazwaPromocji;
    private int value , progPromocji;


    public Promocje() {
    }

    public Promocje(int idPromocje, String nazwaPromocji, int value, int progPromocji) {
        this.idPromocje = idPromocje;
        this.nazwaPromocji = nazwaPromocji;
        this.value = value;
        this.progPromocji = progPromocji;
    }

    public int getIdPromocje() {
        return idPromocje;
    }

    public void setIdPromocje(int idPromocje) {
        this.idPromocje = idPromocje;
    }

    public String getNazwaPromocji() {
        return nazwaPromocji;
    }

    public void setNazwaPromocji(String nazwaPromocji) {
        this.nazwaPromocji = nazwaPromocji;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getProgPromocji() {
        return progPromocji;
    }

    public void setProgPromocji(int progPromocji) {
        this.progPromocji = progPromocji;
    }

    @Override
    public String toString() {
        return "Promocje{" +
                "idPromocje=" + idPromocje +
                ", nazwaPromocji='" + nazwaPromocji + '\'' +
                ", value=" + value +
                ", progPromocji=" + progPromocji +
                '}';
    }
}
