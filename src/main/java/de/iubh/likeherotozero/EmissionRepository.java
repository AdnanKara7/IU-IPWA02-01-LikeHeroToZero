package de.iubh.likeherotozero;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmissionRepository extends JpaRepository<Emission, Long> {
    
    // Holt alle Daten, die vom Experten geprüft wurden (für den öffentlichen Bereich)
    List<Emission> findByIsApprovedTrue();
    
    // Holt alle Daten, die noch NICHT geprüft wurden (für den Experten-Bereich)
    List<Emission> findByIsApprovedFalse();

    // Ermöglicht die Suche nach Ländern (ignoriert Groß-/Kleinschreibung)
    List<Emission> findByLandContainingIgnoreCase(String land);
}