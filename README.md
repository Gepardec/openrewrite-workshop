# Übungen des OpenRewrite-Workshops

**Anforderungen**:
- Min. Java 17
- Maven 3.9.x

Dieses Repository enthält 2 Maven-Projekte:
- uebungen: Tests und Ablageorte für die meisten Übungen
- ticket-monster: Leicht abgeänderte Demoapplikation, die als Ausgangsbasis für Übungen, 
  die wir auf echtem Code ausführen, dient.

## Modul uebungen
Dieses Modul enthält Unit-Tests für die meisten Übungen. Weiters existieren hier Skelette, die als 
Einstiegspunkt für die Aufgaben dienen.

Im Branch "main" funktionieren die Unit-Tests noch nicht. Diese sind im Laufe
des Workshops zu vervollständigen. Im Branch "loesungen" sind Musterlösungen implementiert.

Probiere doch gerne mal, das Modul "uebungen" zu bauen:

```shell
cd uebungen
mvn clean install -DskipTests
```

## Modul ticket-monster

Das Modul "ticket-monster" enthält eine JEE 7 Beispielapplikation, die im 
Laufe der Übungen auf Jakarta migriert werden kann.

```shell
cd ticket-monster
mvn clean install
```

## Slides

Die im Workshop genutzten Slides befinden sich unter "/slides.pdf"

Alle weiteren Infos folgen im Workshop. Have fun ;)
