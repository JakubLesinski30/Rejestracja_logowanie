package RejestracjaLogowanie.rejestracja_i_logowanie;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Uzytkownik")
@Getter
@Setter
public class Uzytkownik {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String nazwaUzytkownika;

    @Column(nullable = false)
    private String haslo;
}