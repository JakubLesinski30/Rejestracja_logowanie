package RejestracjaLogowanie.rejestracja_i_logowanie;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UzytkownikKontroller {

    private final UzytkownikSerwis uzytkownikService;

    @Autowired
    public UzytkownikKontroller(UzytkownikSerwis uzytkownikService) {
        this.uzytkownikService = uzytkownikService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/rejestracja")
    public String rejestracja(Model model) {
        model.addAttribute("uzytkownik", new Uzytkownik());
        return "rejestracja";
    }

@PostMapping("/rejestracja")
public String zarejestrujUzytkownika(@ModelAttribute Uzytkownik uzytkownik, RedirectAttributes redirectAttributes) {
    uzytkownikService.zarejestrujNowegoUzytkownika(uzytkownik);
    redirectAttributes.addFlashAttribute("rejestracjaKompletna", true);
    return "redirect:/pomyslna_rejestracja";
}

@GetMapping("/pomyslna_rejestracja")
public String pomyslnaRejestracja(@ModelAttribute("rejestracjaKompletna") Boolean rejestracjaKompletna) {
    if (Boolean.TRUE.equals(rejestracjaKompletna)) {
        return "pomyslna_rejestracja";
    } else {
        return "redirect:/"; 
    }
}

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/witaj")
    public String witaj(Model model, Principal principal) {
        if (principal instanceof Authentication) {
            Authentication auth = (Authentication) principal;
            SzczegolyUzytkownika userDetails = (SzczegolyUzytkownika) auth.getPrincipal();
            model.addAttribute("username", userDetails.getNazwaUzytkownika());
        }
        return "witaj";
    }

}