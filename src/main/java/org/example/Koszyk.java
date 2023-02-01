package org.example;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "koszyk")
public class Koszyk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int idKoszyk;
    private int idKlient;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idKoszyk")
    private List<Koszyk_produkty> koszyk_produkty;

    @OneToOne(cascade = CascadeType.ALL)
    private Promocje idPromocje;

    public Koszyk() {
    }

    public Koszyk( int idKlient) {
        this.idKlient = idKlient;
    }

    public int getIdKoszyk() {
        return idKoszyk;
    }

    public void setIdKoszyk(int idKoszyk) {
        this.idKoszyk = idKoszyk;
    }

    public int getIdKlient() {
        return idKlient;
    }

    public void setIdKlient(int idKlient) {
        this.idKlient = idKlient;
    }




    @Override
    public String toString() {
        return "Koszyk{" +
                "idKoszyk=" + idKoszyk +
                ", idKlient=" + idKlient +
                '}';
    }
}
