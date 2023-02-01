package org.example;


import javax.persistence.*;

@Entity
@Table(name= "zamowienia")
public class Zamowienia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //KONSTUTKORY BEZ GŁOWNEGO ID KLASY
    private int idZamowienia;

    private int numerZamowienia;
    private int status;
    @OneToOne(cascade = CascadeType.ALL)
    private Koszyk koszyk;

    @OneToOne(cascade = CascadeType.ALL)
    private Pracownicy pracownicy;


    public Zamowienia( int numerZamowienia, int status, Koszyk koszyk) {
        this.koszyk = koszyk;
        this.numerZamowienia = numerZamowienia;
        this.status = status;
    }



    public Zamowienia(int idZamowienia, int numerZamowienia, int status, Koszyk koszyk) {
        this.idZamowienia = idZamowienia;
        this.numerZamowienia = numerZamowienia;
        this.status = status;
        this.koszyk = koszyk;
    }


    public Zamowienia() {
    }

    public Zamowienia(int idZamowienia, int numerZamowienia, int status) {
        this.idZamowienia = idZamowienia;
        this.numerZamowienia = numerZamowienia;
        this.status = status;
    }

    public int getIdZamowienia() {
        return idZamowienia;
    }

    public void setIdZamowienia(int idZamowienia) {
        this.idZamowienia = idZamowienia;
    }

    public int getNumerZamowienia() {
        return numerZamowienia;
    }

    public void setNumerZamowienia(int numerZamowienia) {
        this.numerZamowienia = numerZamowienia;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Zamowienia{" +
                "idZamowienia=" + idZamowienia +
                ", numerZamowienia=" + numerZamowienia +
                ", status=" + status +
                '}';
    }
}
