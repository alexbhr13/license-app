
# StudentScene

Aplicație pentru studenți, care oferă acces la evenimente de interes și un magazin online.

Link de GitHub: https://github.com/alexbhr13/license-app


## Prerechizite

Înainte de a începe, asigurați-vă că ați îndeplinit următoarele cerințe:

- Node.js: descărcați și instalați Node.js de [aici](https://nodejs.org/en)
- Angular CLI: instalați interfața de linie de comandă Angular folosing managerul de pachete npm, și rulând următoarea comandă: 

```bash
npm i @angular/cli
```

- Java Development Kit (JDK): descărcați și instalați JDK de pe site-ul [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html).
- Apache Maven: pentru a descărca și instala Maven, accesați acest [link](https://maven.apache.org/download.cgi).
- MySQL: descărcați și apoi instalați MySQL de pe site-ul oficial: [link](https://dev.mysql.com/downloads/installer/).

## Instalarea proiectului

Urmăriți acești pași pentru a configura proiectul pe calculatorul dvs.


1. Clonarea repo-ului 

    Deschideți un terminal, și rulați următoarele comenzi: 

```bash
git clone https://github.com/alexbhr13/license-app.git
cd license-app
```

2. Configurarea backend-ului

    Creați o bază de date MySQL rulând comanda:

```sql
CREATE DATABASE studentscene
```

Actualizați conținutul fișierului `application.properties`, din folder-ul `src/main/resources` al proiectului Spring Boot, pentru a introduce credențialele bazei de date MySQL:

```application.properties
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/studentscene
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
```

Deschideți un terminal, navigați la folder-ul de baza al proiectului de backend, apoi rulați: 

```bash
mvn clean install
mvn spring-boot:run
```

Astfel, aplicația Spring Boot va rula pe `http://localhost:8080`


3. Configurarea frontend-ului


Deschideți un terminal, navigați la folder-ul de baza al proiectului de frontend, apoi rulați: 

```bash
npm install
```

Rulați aplicația Angular folosind următoarea comandă:

```bash
ng serve
```

Astfel, aplicația Angular va rula pe `http://localhost:4200`


## Utilizare

Deschideți browser-ul preferat și navigați către `http://localhost:4200` pentru a accesa aplicația web.

API-ul generat de backend este disponibil la adresa `http://localhost:8080`.
