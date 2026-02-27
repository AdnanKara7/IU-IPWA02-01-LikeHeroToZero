package de.iubh.likeherotozero;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@Controller
public class EmissionController {

    @Autowired
    private EmissionService emissionService;

    // 1. ÖFFENTLICHE ANSICHT
    @GetMapping("/")
    public String index(Model model) {
        List<Emission> emissions = emissionService.findApproved();
        if (emissions != null) {
            emissions.sort((a, b) -> {
                String lA = a.getLand() != null ? a.getLand() : "";
                String lB = b.getLand() != null ? b.getLand() : "";
                return lA.compareToIgnoreCase(lB);
            });
        }
        model.addAttribute("emissionsList", emissions);
        model.addAttribute("isExpert", false);
        model.addAttribute("username", "Gast");
        return "index";
    }

    // 2. LOGIN
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // 3. BACKEND (Experten-Ansicht)
    @GetMapping("/backend")
    public String backend(Model model, Authentication authentication) {
        List<Emission> emissions = emissionService.findAll();
        if (emissions != null) {
            emissions.sort((a, b) -> {
                String landA = (a.getLand() != null) ? a.getLand() : "";
                String landB = (b.getLand() != null) ? b.getLand() : "";
                return landA.compareToIgnoreCase(landB);
            });
        }
        model.addAttribute("emissionsList", emissions);
        model.addAttribute("isExpert", true);
        model.addAttribute("username", authentication != null ? authentication.getName() : "Experte");
        return "backend";
    }

    // 4. FORMULAR FÜR NEUANLAGE
    @GetMapping("/neu")
    public String neuForm(Model model) {
        model.addAttribute("emission", new Emission());
        return "form-emission";
    }

    // 5. SPEICHERN (Delegiert an Service)
    @PostMapping("/speichern")
    public String saveEmission(@ModelAttribute Emission emission, Authentication authentication) {
        if (emission.getWert() < 0 || emission.getPerCapita() < 0) {
            return "redirect:/backend?error=invalidData"; 
        }
        emissionService.saveWithApproval(emission, authentication);
        return "redirect:/backend";
    }

    // 6. FREIGABE (Delegiert an Service)
    @GetMapping("/freigeben/{id}")
    public String approve(@PathVariable("id") Long id) {
        emissionService.approve(id);
        return "redirect:/backend";
    }

    // 7. LÖSCHEN (Delegiert an Service)
    @GetMapping("/loeschen/{id}")
    public String loeschen(@PathVariable("id") Long id, Authentication authentication) {
        emissionService.deleteIfAdmin(id, authentication);
        return "redirect:/backend";
    }
}