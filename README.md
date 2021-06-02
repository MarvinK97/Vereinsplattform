# Vereinsplattform

Plattform für Vereine und Interessenverbände zur Veranschaulichung von aktuellen Best Practices.
Zum Einsatz gekommen sind dabei die Frameworks Angular und Spring sowie die Container-Lösung Docker.

## Vorraussetzung

Zum ausführen der Anwendung wird lediglich **Docker** als Laufzeitumgebung benötigt!

> Node.js
> Docker
> JDK 8+

## Infos und Links
[Docker Download](https://www.docker.com/products/docker-desktop)
[NodeJs Download](https://nodejs.org/en/)

[Docker Docs](https://docs.docker.com/)
[Spring Docs](https://spring.io/projects)
[Angular Docs](https://angular.io/docs)

## Starten der Anwendung

1. herunterladen der aktuellen Version
2. mittels Konsole in das Root-Verzeichnis wechseln
3. `docker-compose up` ausführen

Nun werden die Docker-Container anhand der docker-compose.yml erzeugt und anschließend gestartet.
Während diesem Prozess werden drei Container erzeugt:

     springbackendservice 
     mysqldatabase 
     nginx

Während dem Erzeugen des Backend-Container (springbackendservice), wird die Backend-Anwendung aus `Backend/target` in den Container kopiert, welcher eine Java-Laufzeitumgebung bereitstellt.

Wärhend dem Erzeugen des Frontend-Container (nginx), wird die Frontend-Anwendung aus `Frontend/dist` in den Container kopiert, welcher einen nginx Webserver bereitstellt.

Als Datenbank findet eine MySQL Datenbank verwendung.
