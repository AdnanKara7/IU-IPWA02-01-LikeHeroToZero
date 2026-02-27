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

    // Logik für das Speichern und die automatische Freigabe
    public void saveWithApproval(Emission emission, Authentication authentication) {
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

        // Nur Admins (Wissenschaftler) setzen den Status direkt auf approved
        if (isAdmin) {
            emission.setApproved(true); 
        } else {
            // Falls es andere Rollen gäbe, müssten diese erst geprüft werden
            emission.setApproved(false);
        }
        repository.save(emission);
    }

    // Nachträgliche Freigabe durch den Admin
    public void approve(Long id) {
        Emission em = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ungültige ID: " + id));
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