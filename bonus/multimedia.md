## Multimédia-tár

A feladat egy olyan alkalmazás létrehozása, amiben könyveket, filmeket és zenéket lehet eltárolni.

### Entitások

#### Könyv

Az alábbi adatokkal rendelkezik:
- id (Integer)
- szerző (String)
- cím (String)
- kiadás időpontja (LocalDate)

#### Film

Az alábbi adatokkal rendelkezik:
- id (Integer)
- cím (String)
- rendező (String)
- filmstúdió (String)

#### Zene

Az alábbi adatokkal rendelkezik:
- id (Integer)
- előadó (String)
- cím (String)
- műfaj (String)

#### Általános elvárás

A három entitásra külön-külön repository osztályokat használj, és az adatbázisban is külön táblában legyenek eltárolva.
Bár vannak közös pontjaik az entitásoknak, de NE használj öröklődést! A három osztály egymástól független legyen!  
Az id-kat az adatbázis ossza ki!

### Elérhető műveletek

- mentés
- listázás
- könyv keresése id alapján
- elmentett könyv módosítása
- elmentett könyv törlése

- film mentése
- filmek listázása
- film keresése id alapján
- elmentett film módosítása
- elmentett film törlése

- zene mentése
- zenék listázása
- zene keresése id alapján
- elmentett zene módosítása
- elmentett zene törlése

### Feladat

Hozd létre a szükséges entity és repository osztályokat!
Írj teszteseteket!