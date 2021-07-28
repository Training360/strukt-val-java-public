# Struktúraváltó résztvevõk haladását nyomonkövetõ alkalmazás

## Vízió

A projektmunkát egy valós igény keltette életre. A Struktúraváltó
projekt során a résztvevõk elõrehaladásának ellenõrzése, tárolása,
lekérdezése, áttekintése elég körülményes, különbözõ rendszerekben
történik.

A projektmunka eredményét fel fogjuk használni a következõ
évfolyamoknál, tehát egy valós szoftvert fogunk
megvalósítani. Az egyéni ötletek megvalósítására is van lehetõség
a megadottakon felül. A legjobbakat összeválogatjuk, és egy
projektbe másoljuk.

Amennyiben a kötelezõ részt megcsináltad, a továbbiakról egyeztessünk,
mert még több részlet ki van dolgozva!

Projekt neve legyen `mentor-tools`!

## Sprint 1

### Funkcionális követelmények

A következõket kell karbantartani:

* Évfolyamokat (`TrainingClass`)
* Résztvevõket (`Student`), egy résztvevõ akár több évfolyamon is szerepelhet
* Tanmeneteket (`Syllabus`)
* A tanmenetekhez modulok (`Module`), ahhoz leckék (`Lesson`) tartoznak
* A tanmenetet évfolyamhoz lehet rendelni
* Be lehessen jelölni, hogy melyik résztvevõ melyik leckét dolgozta fel: megnézte a videót, és elkészítette a
  gyakorlati feladatot

A különbözõ adatokat kell nyilvántartani:

#### Évfolyam

* Elnevezés (nem üres, max. 255 karakter)
* Kezdés dátuma (tetszõleges)
* Befejezés dátuma (tetszõleges, késõbb legyen, mint a kezdés)

Lehet listázni, lekérdezni, létrehozni, mindhárom attribútumot módosítani, törölni.

#### Résztvevõk

* Név (nem üres, max. 255 karakter)
* E-mail cím (nem üres, max. 255 karakter)
* GitHub felhasználónév (nem üres, max. 255 karakter)
* Megjegyzés

#### Évfolyam - résztvevõ kapcsolat

Egy résztvevõ be tud iratkozni egy tanfolyamra. Ezt a beiratkozás
osztály/tábla tartalmazza (`registration`).

A beiratkozásnak vannak státuszai: aktív (`ACTIVE`), kilépés alatt (`EXIT_IN_PROGRESS`), kilépett (`EXITED`).
Hiszen egy résztvevõ egy évfolyamon lehet aktív, míg egy másikon kilépett.

Beiratkozás történhet a `/trainingclasses/{id}/registrations` címen. Meg kell adni a résztvevõ azonosítóját.
Itt le lehet kérdezni az évfolyamra beiratkozottakat (a résztvevõkrõl csak az id-ját, nevét és státuszát adja vissza).

Egy résztvevõ beiratkozásait is le lehet kérdezni a `/students/{id}/registrations` címen. 
Csak az évfolyamok id-ját és nevét adja vissza.

#### Tanmenet

* Név (nem üres, max. 255 karakter)

Egy tanmenet több évfolyamhoz is tartozhat, egy évfolyamhoz egy tanmenet!

Lehet listázni, lekérdezni, létrehozni, minden adatot módosítani, törölni.

#### Modul

* Cím (nem üres, max. 255 karakter)
* URL (nem üres, max. 255 karakter)

Egy tanmenethez több modul is tartozhat.

Lehet listázni, lekérdezni, létrehozni, minden adatot módosítani, törölni.

#### Lecke

* Cím (nem üres, max. 255 karakter)
* URL (nem üres, max. 255 karakter)

Egy modulhoz több lecke is tartozhat.

Lehet listázni, lekérdezni, létrehozni, minden adatot módosítani, törölni.

#### Lecke elvégzése

* Melyik résztvevõ
* Melyik leckét
* Videót, gyakorlati feladatot, vagy mindkettõt

Szintén egy kapcsoló entitásra van szükség, ami hivatkozik egy résztvevõre és egy leckére (`LessonCompletion`). 
Valamint az adatai: 

* Videót megnézte-e (nem `boolean`, hanem enum: `COMPLETED`, `NOT_COMPLETED`)
* Gyakorlati feladatot elvégezte-e (nem `boolean`, hanem enum: `COMPLETED`, `NOT_COMPLETED`)
* Elvégzésének dátuma
* Commit URL-je (lehet üres, max. 255 karakter)

Elérhetõ a `/students/{id}/lessioncompletition` címen.

Lehet listázni, lekérdezni, létrehozni, minden adatot módosítani (kivéve a résztvevõt), törölni.

### Nem-funkcionális követelmények

Klasszikus háromrétegû alkalmazás, MariaDB adatbázissal,
Java Spring backenddel, REST webszolgáltatásokkal.

Követelmények tételesen:

* SQL adatbázis kezelõ réteg megvalósítása Spring Data JPA-val (`Repository`)
* Flyway - a scriptek a funkciókkal együtt készüljenek, szóval ahogy bekerül az entitás, úgy kerüljön be egy
  plusz script is, ami a táblát létrehozza
* Üzleti logika réteg megvalósítása `@Service` osztályokkal
* Integrációs tesztek megléte (elég TestRestTemplate tesztek), legalább 80%-os tesztlefedettség
* Controller réteg megvalósítása, RESTful API implementálására. Az API végpontoknak a `/api` címen kell elérhetõeknek lenniük.
* Hibakezelés, validáció
* Swagger felület
* HTTP fájl a teszteléshez
* Dockerfile
* GitHub Classroomban kell a megoldást beadni
* Commitolni legalább entitásonként, és hozzá tartozó REST végpontonként

Cheat sheet: https://github.com/Training360/strukt-val-java-public/blob/master/annotations%20-%20cheat%20sheet.md