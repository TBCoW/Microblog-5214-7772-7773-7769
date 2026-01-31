package WWSIS.Microblog.dao;

import WWSIS.Microblog.model.Uzytkownik;
import java.time.LocalDateTime;

public interface UzytkownikDao {
    Uzytkownik znajdzPoLoginie(String login);

    void dodajUzytkownika(String login, String email, String haslo, LocalDateTime dataUtworzenia, String bio);
}
