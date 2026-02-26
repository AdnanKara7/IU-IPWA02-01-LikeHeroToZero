# Like Hero To Zero - CO2 Monitoring App

Wissenschaftliche Projektarbeit zur Erfassung und Verwaltung globaler CO2-Emissionsdaten. Diese Anwendung ermöglicht es Experten, Daten zu pflegen, während die Öffentlichkeit eine geprüfte Übersicht der aktuellen Werte erhält.

## 🌟 Funktionen
* **Öffentliches Dashboard:** Anzeige aller freigegebenen CO2-Daten (sortierbar und filterbar).
* **Experten-Login:** Gesicherter Zugang für Wissenschaftler und Administratoren (Spring Security).
* **CRUD-Verwaltung:** Vollständiges Erstellen, Lesen, Bearbeiten und Löschen von Datensätzen.
* **Freigabeprozess:** Datensätze müssen durch einen Administrator geprüft und freigeschaltet werden (`is_approved = true`).

## 🛠 Tech-Stack
* **Backend:** Java 21 (Spring Boot 3.x)
* **Sicherheit:** Spring Security (Rollenbasierte Zugriffskontrolle)
* **Persistenz:** Spring Data JPA / Hibernate
* **Frontend:** Thymeleaf, Bootstrap 5, Bootstrap Icons
* **Datenbank:** MySQL 8 (XAMPP)

---

## 🚀 Installation & Start

Um die Anwendung lokal zu starten, folgen Sie bitte diesen Schritten:

### 1. Datenbank vorbereiten & Daten importieren (Wichtig!)
Damit die Anwendung mit dem vollständigen Datenbestand (ca. 160 Länder sowie Test-Benutzer) startet, führen Sie bitte folgende Schritte aus:

* Starten Sie Ihren lokalen MySQL-Server (z. B. via **XAMPP**).
* Erstellen Sie eine neue, leere Datenbank mit dem Namen: `likeherotozero`.
* Importieren Sie die Datei `setup_database.sql` (im Hauptverzeichnis dieses Projekts) in diese Datenbank (z. B. über den Reiter **Importieren** in phpMyAdmin).
* **Hinweis:** Die Datei enthält sowohl die Tabellenstruktur als auch alle notwendigen Testdaten.

### 2. Projekt importieren & Konfiguration
* Klonen Sie das Repository oder laden Sie den Code herunter.
* Importieren Sie das Projekt als **Maven-Projekt** in Ihre IDE (Eclipse / IntelliJ).
* Prüfen Sie in `src/main/resources/application.properties`, ob die Zugangsdaten (Standard: `root` / kein Passwort) für Ihren lokalen MySQL-Server korrekt hinterlegt sind.

### 3. Anwendung starten
* Führen Sie die Datei `LikeHeroToZeroApplication.java` als Java-Anwendung aus.
* Die Anwendung ist anschließend im Browser unter `http://localhost:8080` erreichbar.

---

## 🔑 Test-Zugangsdaten
Für die Korrektur der Funktionalitäten (Admin/Wissenschaftler) können folgende Konten verwendet werden:

| Rolle | Benutzername | Passwort | Berechtigungen |
| :--- | :--- | :--- | :--- |
| **Administrator** | `admin` | `admin123` | Datensatz-Freigabe (Approve) & volle CRUD-Rechte |
| **Wissenschaftler** | `wissenschaftler` | `co2pass` | Erfassen und Bearbeiten von Datensätzen |

---
*Erstellt im Rahmen der Fallstudie für das Modul IU-IPWA02-01: Programmierung von industriellen Informationssystemen mit Java EE.*
