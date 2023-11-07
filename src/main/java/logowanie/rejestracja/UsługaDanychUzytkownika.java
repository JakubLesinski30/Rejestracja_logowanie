package logowanie.rejestracja;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsługaDanychUzytkownika implements UserDetailsService {

    private final UzytkownikRepozytorium uzytkownikRepository;

    @Autowired
    public UsługaDanychUzytkownika(UzytkownikRepozytorium uzytkownikRepository) {
        this.uzytkownikRepository = uzytkownikRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Uzytkownik uzytkownik = uzytkownikRepository.findByEmail(email);
        if (uzytkownik == null) {
            throw new UsernameNotFoundException("Nie można znaleźć użytkownika z emailem: " + email);
        }

        return new SzczegolyUzytkownika(uzytkownik, Collections.emptyList());
    }
}