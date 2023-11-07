package logowanie.rejestracja;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UzytkownikRepozytorium extends JpaRepository<Uzytkownik, Long> {
    Uzytkownik findByEmail(String email);
}