# Like Hero To Zero - CO2 Monitoring App

Wissenschaftliche Projektarbeit zur Erfassung und Verwaltung globaler CO2-Emissionsdaten. Diese Anwendung ermöglicht es Experten, Daten zu pflegen, während die Öffentlichkeit eine geprüfte Übersicht der aktuellen Werte erhält.

## Funktionen
* **Öffentliches Dashboard:** Anzeige aller freigegebenen CO2-Daten (sortierbar und filterbar).
* **Experten-Login:** Gesicherter Zugang für Wissenschaftler und Administratoren.
* **CRUD-Verwaltung:** Vollständiges Erstellen, Lesen, Bearbeiten und Löschen von Datensätzen.
* **Freigabeprozess:** Von Wissenschaftlern neu angelegte Daten müssen durch einen Administrator geprüft und freigeschaltet werden (Vier-Augen-Prinzip).

## Tech-Stack
* **Backend:** Java 21 (Spring Boot 3.x)
* **Sicherheit:** Spring Security (Rollenbasierte Zugriffskontrolle)
* **Persistenz:** Spring Data JPA / Hibernate
* **Frontend:** Thymeleaf, Bootstrap 5, Bootstrap Icons
* **Datenbank:** MySQL

## Installation & Start (Anleitung für die Korrektur)

Um die Anwendung lokal zu starten, folgen Sie bitte diesen Schritten:

1. **Datenbank vorbereiten:**
   * Starten Sie Ihren lokalen MySQL-Server (z. B. via XAMPP).
   * Erstellen Sie eine neue, leere Datenbank mit dem Namen: `likeherotozero`
   * *(Hinweis: Dank Hibernate `ddl-auto: update` werden alle Tabellen beim ersten Start automatisch generiert.)*

2. **Projekt importieren:**
   * Laden Sie den Code herunter oder klonen Sie das Repository.
   * Importieren Sie das Projekt als **Maven-Projekt** in Ihre IDE (z. B. IntelliJ IDEA oder Eclipse).

3. **Konfiguration:**
   * Prüfen Sie in der Datei `src/main/resources/application.properties`, ob die Zugangsdaten für Ihren lokalen MySQL-Server (Standard: `root` ohne Passwort) übereinstimmen.

4. **Anwendung starten:**
   * Führen Sie die Datei `LikeHeroToZeroApplication.java` aus.
   * Die Anwendung ist anschließend im Browser unter `http://localhost:8080` erreichbar.

## Test-Zugangsdaten
Für die Korrektur können folgende Benutzerkonten verwendet werden:

| Rolle | Benutzername | Passwort |
| :--- | :--- | :--- |
| **Administrator** | `admin` | `admin123` |
| **Wissenschaftler** | `wissenschaftler` | `co2pass` |

---
*Erstellt im Rahmen der Projektarbeit 2026.*
