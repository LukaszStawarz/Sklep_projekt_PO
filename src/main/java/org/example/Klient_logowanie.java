package org.example;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "klient_logowanie")
public class Klient_logowanie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int idKlient_logowanie;
    private String login;
    private String haslo;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idKlient")
    private List<Koszyk> koszyk;

    public Klient_logowanie() {
    }

    public Klient_logowanie(int idKlient_logowanie, String login, String haslo) {
        this.idKlient_logowanie = idKlient_logowanie;
        this.login = login;
        this.haslo = haslo;
    }

    public Klient_logowanie(String login, String haslo) {
        this.login = login;
        this.haslo = haslo;
    }

    public int getIdKlient_logowanie() {
        return idKlient_logowanie;
    }

    public void setIdKlient_logowanie(int idKlient_logowanie) {
        this.idKlient_logowanie = idKlient_logowanie;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    @Override
    public String toString() {
        return "Klient_logowanie{" +
                "idKlient_logowanie=" + idKlient_logowanie +
                ", login='" + login + '\'' +
                ", haslo='" + haslo + '\'' +
                '}';
    }
}
