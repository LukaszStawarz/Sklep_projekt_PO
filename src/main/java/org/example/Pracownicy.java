package org.example;

import javax.persistence.*;

@Entity
@Table(name= "pracownicy")
public class Pracownicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPracownicy;
    private String imie, nazwisko;
    private long PESEL;


    public Pracownicy() {
    }

    public Pracownicy(int idPracownicy, String imie, String nazwisko,
                      long PESEL) {
        this.idPracownicy = idPracownicy;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.PESEL = PESEL;
    }

    public int getIdPracownicy() {
        return idPracownicy;
    }

    public void setIdPracownicy(int idPracownicy) {
        this.idPracownicy = idPracownicy;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public long getPESEL() {
        return PESEL;
    }

    public void setPESEL(long PESEL) {
        this.PESEL = PESEL;
    }



    @Override
    public String toString() {
        return "Pracownicy{" +
                "idPracownicy=" + idPracownicy +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", PESEL=" + PESEL +
                '}';
    }
}
