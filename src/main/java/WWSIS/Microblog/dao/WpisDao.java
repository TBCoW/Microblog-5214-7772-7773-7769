package WWSIS.Microblog.dao;

import WWSIS.Microblog.model.Wpis;
import java.time.LocalDateTime;
import java.util.List;

public interface WpisDao {
    List<Wpis> wezTimelineUzytkownika(int userId);

    List<Wpis> wezFullTimelineUzytkownika(int userId);

    List<Wpis> wezFullPublicTimeline();

    public void dodajWpis(int userId, String tresc, LocalDateTime dataDodania, int liczbaLajkow);
}
