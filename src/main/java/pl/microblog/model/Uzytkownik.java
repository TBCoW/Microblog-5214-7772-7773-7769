package main.java.pl.microblog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "Uzytkownicy")
public class Uzytkownik {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "login")
    private String login;
    @Column(name = "email")
    private String email;
    @Column(name = "haslo")
    private String haslo;
    @Column(name = "data_utworzenia")
    private LocalDateTime dataUtworzenia;
    @Column(name = "bio")
    private String bio;

    public Uzytkownik(int id, String login, String email, String haslo, LocalDateTime dataUtworzenia, String bio) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.haslo = haslo;
        this.dataUtworzenia = dataUtworzenia;
        this.bio = bio;
    }

    public Uzytkownik() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public LocalDateTime getDataUtworzenia() {
        return dataUtworzenia;
    }

    public void setDataUtworzenia(LocalDateTime dataUtworzenia) {
        this.dataUtworzenia = dataUtworzenia;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
