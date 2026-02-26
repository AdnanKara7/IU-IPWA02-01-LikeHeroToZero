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
    private EmissionRepository repository;

    // 1. ÖFFENTLICHE ANSICHT (Alphabetisch sortiert)
    @GetMapping("/")
    public String index(Model model) {
        List<Emission> emissions = repository.findByIsApprovedTrue();
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

    // 3. BACKEND (Alle Daten für Experten sichtbar)
    @GetMapping("/backend")
    public String backend(Model model, Authentication authentication) {
        List<Emission> emissions = repository.findAll();
        
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

    // 4. NEUANLAGE
 // Weg für NEU
    @GetMapping("/neu")
    public String neuForm(Model model) {
        model.addAttribute("emission", new Emission());
        return "form-emission"; // Name der neuen kombinierten Datei
    }

    // Weg für BEARBEITEN
    @GetMapping("/bearbeiten/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Emission e = repository.findById(id).orElseThrow();
        model.addAttribute("emission", e);
        return "form-emission"; // Gleiche Datei wie oben!
    }

    // 6. SPEICHERN
    @PostMapping("/speichern")
    public String saveEmission(@ModelAttribute Emission emission, Authentication authentication) {
        // Validierung: CO2-Werte sollten nicht negativ sein (Wachstum darf negativ sein!)
        if (emission.getWert() < 0 || emission.getPerCapita() < 0) {
            return "redirect:/backend?error=invalidData"; 
        }

        // Rollen prüfen
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        
        // Logik für die Freigabe
        if (isAdmin) {
            emission.setApproved(true); 
        } else {
            emission.setApproved(false);
        }

        repository.save(emission);
        return "redirect:/backend";
    }

    // 7. FREIGABE-METHODE
    @GetMapping("/freigeben/{id}")
    public String approve(@PathVariable("id") Long id) {
        Emission em = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Ungültige ID: " + id));
        
        em.setApproved(true);
        repository.save(em);
        
        return "redirect:/backend";
    }

    // 8. LÖSCHEN
    @GetMapping("/loeschen/{id}")
    public String loeschen(@PathVariable("id") Long id, Authentication authentication) {
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            repository.deleteById(id);
        }
        
        return "redirect:/backend";
    }
}