package de.iubh.likeherotozero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import java.util.List;

@Service
public class EmissionService {

    @Autowired
    private EmissionRepository repository;

    // Gibt alle Emissionen zurück
    public List<Emission> findAll() {
        return repository.findAll();
    }

    // Gibt nur freigegebene Emissionen zurück
    public List<Emission> findApproved() {
        return repository.findByIsApprovedTrue();
    }

    // NEU: Methode zum Finden einer einzelnen Emission (für das Bearbeiten-Formular)
    public Emission findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ungültige Emission-ID: " + id));
    }

    // Logik für das Speichern und die automatische Freigabe
    public void saveWithApproval(Emission emission, Authentication authentication) {
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

        // Wenn ein Admin speichert, wird es direkt freigegeben
        if (isAdmin) {
            emission.setApproved(true); 
        } else {
            emission.setApproved(false);
        }
        repository.save(emission);
    }

    // Nachträgliche Freigabe durch den Admin
    public void approve(Long id) {
        Emission em = findById(id); // Hier nutzen wir die neue findById Methode
        em.setApproved(true);
        repository.save(em);
    }

    // Löschen nur für Admins
    public void deleteIfAdmin(Long id, Authentication authentication) {
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        if (isAdmin) {
            repository.deleteById(id);
        }
    }
}