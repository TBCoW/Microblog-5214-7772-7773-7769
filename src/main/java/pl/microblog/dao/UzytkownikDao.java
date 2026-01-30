package main.java.pl.microblog.dao;

import main.java.pl.microblog.model.Uzytkownik;
import java.time.LocalDateTime;

public interface UzytkownikDao {
    Uzytkownik znajdzPoLoginie(String login);

    void dodajUzytkownika(String login, String email, String haslo, LocalDateTime dataUtworzenia, String bio);
}
