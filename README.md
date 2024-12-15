# PproProjektExpenseTracker

## Expense tracker

Jednoduchý expense tracker vytvořen v rámci předmětu PPRO.

## Technologie

- Java 21
- Spring
- Thymeleaf
- Postgresql
- H2
- Bootstrap

## Popis aplikace

Aplikace umožňuje vytvářet uživatelské účty, pro každý účet je poté možné vytvořit projekt.
Projekt umožňuje práci s transakcemi, umožňuje přidat, upravit a zobrazit jednotlivé transakce.
Každá transakce také obsahuje _štítek_, ty je možné přidávat, upravovat a odebírat.
Uživatel má také možnost si upravit svůj profil.

Pokud má uživatel roli admin má více pravomocí. Admin může také nahlédnout na seznam uživatelů,
nebo na audit logy. Audit logy jsou tvořeny automaticky. A to vždy, když dojde k úpravě, vytvoření
nebo smázání nějakého prvku. Admin vidí všechny tyto logy a má možnost si tedy prohlédnout jednotlivé změny.

## Informace

- Aplikace běží na portu 8080
- Nastavení databáze
    - Základní nastavení funguje s databází H2
        - Toto nastavení využívá v liquibase _dev_ context
        - Údaje pro h2
            - JDBC Url: jdbc:h2:mem:db
            - UserName: sa
            - Password:
    - Skrze nastavení profilu _local-postgres_
        - Bude využita lokální databáze postgres
        - Tento profil využívá v liquibase _prod_ context
        - Údaje pro Postgres
            - Database: expensetracker
            - Port: 5432
            - user: tracker
            - password: password
- Pro oba kontexty, je nachystán uživatel administrator
    - Údaje:
        - Uživatelské jméno: administrator
        - Heslo: password
        - Role: admin, user
- Pro kontext _dev_, je nachystán ještě uživatel user
    - Údaje:
        - Uživatelské jméno: user
        - Heslo: password
        - Role: user'
- Docker
    - Součástí je konfigurace pro spuštění skrze Docker
    - Lze využít docker compose, pro spuštění společně s databází
    - Postup:
        - mvn clean package
        - docker-compose up --build