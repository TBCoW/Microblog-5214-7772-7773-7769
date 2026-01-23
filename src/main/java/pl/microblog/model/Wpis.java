package main.java.pl.microblog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Wpisy")
public class Wpis {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "user_id")
    private int userID;
    @Column(name = "tresc")
    private String tresc;
    @Column(name = "data_dodania")
    private double dataDodania;
    @Column(name = "liczba_lajkow")
    private int liczbaLajkow;

    public Wpis(int id, int userID, String tresc, double dataDodania, int liczbaLajkow) {
        this.id = id;
        this.userID = userID;
        this.tresc = tresc;
        this.dataDodania = dataDodania;
        this.liczbaLajkow = liczbaLajkow;
    }

    public Wpis() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public double getDataDodania() {
        return dataDodania;
    }

    public void setDataDodania(double dataDodania) {
        this.dataDodania = dataDodania;
    }

    public int getLiczbaLajkow() {
        return liczbaLajkow;
    }

    public void setLiczbaLajkow(int liczbaLajkow) {
        this.liczbaLajkow = liczbaLajkow;
    }
}
