# Videók

Ide gyűjtjük a konzultációk rövid leírásait, hogy könnyebben visszakereshetők legyenek.

## 2021.06.01.

Elhangzottak a képzéssel kapcsolatos tudnivalók, ezek megtalálhatóak a
[https://e-learning.training360.com/courses/take/training360-str-hgk-vallalati-backend-kepzes-kozpont/texts/24684824-mi-ez-a-tananyag](https://e-learning.training360.com/courses/take/training360-str-hgk-vallalati-backend-kepzes-kozpont/texts/24684824-mi-ez-a-tananyag)
címen.

A gyakorlati részben elkészítettünk egy háromrétegű alkalmazást az _alkalmazott_ üzleti területen.
A controller réteg felelős a felhasználói felületért, a service az üzleti logika (jelenleg csak továbbhív),
és a repository tárolja az adatokat. Ebből kettőt is csináltunk, egy, ami memóriában tárolja, egy, ami
adatbázisban (MariaDB). Utóbbihoz Spring `JdbcTemplate`-et használtunk. Adatbázist Flyway inicializálja (táblalétrehozás).
Írtunk integrációs teszteket is. Volt egy `Repository` interfész, és ennek volt két implementációja.

Példa projekt: [https://github.com/Training360/strukt-val-java-public/tree/master/employees-solution](https://github.com/Training360/strukt-val-java-public/tree/master/employees-solution)

A következő projektet javasolt megcsinálni: [https://github.com/Training360/strukt-val-java-public/blob/master/meetingrooms/README.md](https://github.com/Training360/strukt-val-java-public/blob/master/meetingrooms/README.md)

## 2021.06.03.

- Közös gyakorló teszt, kipróbáltuk az AhaSlides alkalmazást
- István beszélt a Flyway használatáról: hogyan működik, hogyan használjuk éles alkalmazásban és hogyan teszteseteknél.
- Kristóf válaszolt a Slack-en elhangzott kérdésekre:
    
    * Megbeszéltük a _polimorfizmus_ és a _dependency inversion_ fogalmát. 
      (Emlékeztetőül: 
      **Polimorfizmus:** Többalakúság. Azt jelenti, hogy egy példányosított objektum több formában
      is megjelenhet, az objektumot többféle típusú változónak is értékül lehet adni. Az
      objektum _dinamikus típusa_ az, amellyel konkrétan példányosítjuk, a példányosított objektumot
      azonban értékül adhatjuk többféle _statikus típusú_ változónak is. A változó
      statikus típusa lehet az objektum dinamikus típusával megegyező, de lehet annak bármely
      ősosztálya, vagy bármely, a dinamikus típus által implementált interfész is. Az objektum ezek
      bármelyikének alakjában megjelenhet, tehát bármelyik, az osztályhierarchiában lévő ősosztálya
      (akár az `Object` osztály), vagy azok által implementált bármely interfész alakjában is.
      **Dependency inversion**-nek nevezzük azt, amikor egy osztály úgy használja egy másik osztály
      objektumát, hogy nem az osztályára hivatkozik, hanem az általa implementált interfészre.
      Azaz amikor nem egy osztályra mutat egy függőség, hanem annak egy interfészére. )
    * A hétfői konzultáción kiadott meetingrooms projektfeladathoz kapcsolódóan: hogyan kérjük vissza minden második 
      elemet egy listából stream használatával, illetve hogyan tegyük meg ugyanezt adatbázisból?
    * Szintén a projektfeladattal kapcsolatosan: hogyan működik az abc-sorrendbe rendezés MariaDB-ben? Magyar helyesírás szabályai szerint az `a` betű nincs előrébb, mint az `á` betű.
    * Hol kell beállítani a Java language levelt az IDEA-ban?
    * Megnéztük azt, hogy hogyan hozunk létre egy repo-n belül több projektet.

## 2021.06.07.

- A konzultáció elején technikai kérdéseket beszéltünk meg, az újonnan csatlakozott embereknek még nincsenek hozzáféréseik 
  a legfontosabb dolgokhoz sem.
- Többen kérték, hogy ha lehetséges, kerüljenek föl az egyes leckékhez a szöveges leírások.
- Ezután általános ismétlés következett arról, hogy mit kell tudnia egy jól megírt unit tesztesetnek.
- Arról is szó volt, hogy miért érdemes a fejlesztőnek unit teszteket írnia saját maga számára, miben 
  könnyíti meg ezzel a saját munkáját.
- Volt egy kérdés az anonim belső osztályokról.
- Majd Kristóf hozott egy feladatot: hogyan mentsünk le adatbázisba egy olyan `Employee`-t, akinek van egy `Address`-listája? 
  Opcionális házi feladat lesz ennek a megoldása a múlt héten kiadott `meetingrooms-solution`-projektben a következő módon: 
  legyen minden `MeetingRoom`-nak egy-egy `Meeting`-eket tartalmazó listája, ezeket le kell menteni adatbázisba 
  (JDBC-ben, tranzakciókezeléssel), majd ezeket az adatokat vissza is kell kérdezni az adatbázisból. Ez annak szól, akinek 
  van rá plusz ideje, de természetesen elsősorban a heti kötelező tananyaggal kell foglalkozni.
- A feladat pontos szövege:
  Egészítsd ki a `MeetingRooms` alkalmazást!
  Készíts egy `Meeting` nevű osztályt, melynek adattagjai tárolják a meetinget vezető ember nevét (String) és azt, 
  hogy mettől meddig tart a meeting. A `MeetingRoom` osztálynak legyen egy `Meeting`-eket tartalmazó listája, getter, 
  setter és egy `addMeeting()` metódusa. A `MariaDbMeetingRoomsRepository` osztályban, amikor elmentünk egy 
  `MeetingRoom`-ot, mentsük el a hozzá tartozó `Meeting`-eket is egy külön táblába, melyben külső kulccsal 
  hivatkozunk a `MeetingRoom`-ra. Próbáld meg visszaolvasni a táblák tartalmait egy listába!
- Kristóf által adott SQL-utasítás a `Meeting`-ek táblájának létrehozására (értelemszerűen át kell írni):

```sql  
create table addresses (id bigint not null auto_increment,
city varchar(255),
street varchar(255),
house_number int,
emp_id bigint,
primary key (id),
CONSTRAINT `fk_address_emp`
foreign key(emp_id)
references employees (id) ON DELETE CASCADE ON UPDATE RESTRICT)
```
  
## 2021.06.08.

- A konzultáció elején nagyon röviden technikai kérdéseket beszéltünk meg.
- Kristóf hozott egy feladatot, és utána ezzel telt el a konzultáció teljes ideje. Közös kódolás volt, 
  a streamek és a lambda kifejezések gyakorlására. 
  Először Kristóf megosztott a GitHubon egy előkészített projektet, amit forkolni kellett és klónozni a saját gépre, 
  majd ebben dolgozni. Több különböző metódust kellett implementálni streamek használatával. 
  Opcionális házi feladatként is kaptunk még további feladatokat.
- Kristóf gyakorló projektjének linkje: [https://github.com/bkris21/streams-demo-public](https://github.com/bkris21/streams-demo-public)
- A feladat szövege: 
    * Órán megoldott feladatok:
      Határozd meg a rendelések darabszámát egy paraméterként kapott státusz alapján!
      Gyűjtsd össze azokat a rendeléseket, amelyekben van egy paraméterként kapott kategóriájú termék!
      Gyűjtsd össze azokat a termékeket a rendelések közül, amelyeknek egy paraméterül kapott összegnél magasabb az áruk!
    * További gyakorló feladatok:
      Írj egy metódust, ami paraméterként vár két dátumot, és adjuk vissza a két dátum közötti árbevételt, 
      vagyis a két dátum közötti rendelések termékeinek az összértékét!
      Keressünk meg egy terméket a neve alapján, amit paraméterként lehet megadni!
      Adjuk vissza azt a rendelést, ami a legdrágább terméket tartalmazza! Ha több ilyen van, bármelyiket.

## 2021.06.09.

- A konzultáció elején Kristóf tisztázta a Maven Surefire Plugin-nel kapcsolatos kérdéseket.
- Utána röviden bemutatta, hogy mi annak a menete, ha többen dolgoznak egy projekten, amely a
  GitHubon van tárolva. Hogyan történik a közös fejlesztés, pontosan milyen lépésekből áll.
- Ezután a hallgatókat párba osztotta és adott egy feladatot, amin mindezt élesben is ki
  lehetett próbálni. A feladat maga direkt nem volt nehéz, mert a lényeg most nem ez volt,
  hanem az, hogy a közös munkát mindenki meg tudja tapasztalni.
- A feladat szövege: Készítsd el a `Movie` osztályt a kövtkező
  adattagokkal: + `name (String)` + `length (int)` + `releaseDate (LocalDate)`  
  Legyen az osztályban egy konstruktor ami beállítja az adattagokat illetve a gettereket,
  settereket. Készítsd el a `MovieService` osztályt melyben van egy filmeket tartalmazó
  lista és ehhez egy `save(Movie movie)` metódus. Legyen még benne két metódus: + Keressük
  meg a legújabb filmet! + Keressünk filmet névtöredék alapján!
- A konzultáció további része ezzel a feladattal és a közös fejlesztés során felmerült
  problémák megoldásával telt.

## 2021.06.11.

- A konzultáció elején közös interaktív tesztet csináltunk meg,
  utána közösen megbeszéltük a megoldásokat.
- Sajnos mindannyian elfelejtettük, hogy felvételt kellene indítani, ezért
  az első néhány kérdés részletesebb magyarázata lemaradt a videóról.
- Ezután a hétfőn feladott opcionális házi feladat került sorra
  (`MeetingRoom`-ok és a hozzájuk tartozó `Meeting`-ek listájának
  lementése adatbázisba és ugyanennek a visszakérdezése). Kristóf részletesen
  bemutatta ennek az egyszerűbb megoldási elvét az `employees` projektben
  (két külön lekérdezéssel), majd megnéztünk egy másik megoldást
  (`JOIN` parancs használatával, egy lekérdezéssel).
- Következőnek részletesen megbeszéltük a kedden feladott opcionális házi
  feladat (streamek az `OrderService` osztályban) megoldási lehetőségeit.

## 2021.06.14.

- A konzultáció elején megbeszéltük a másnapi csoportmunka technikai részleteit.
- Ezután Kristóf elkezdte sorra megválaszolni a JUnit-tananyaghoz a múlt héten, 
  Slack-en feltett kérdéseket. A következőkről esett szó részletesen:
  
    * Belső osztályok, statikus belső osztályok.
    * Mikor lehet hasznos, hogy egy tesztesetet explicit módon elbuktasson az ember 
      a `fail()` metódus használatával?
    * Mikor lehet hasznos az assume használata?
    * Metaannotáció létrehozása.
    * A "Tesztesetek ismétlése" leckéhez tartozó gyakorlati feladat megfogalmazása 
      nem szerencsés, hogyan értelmezhető jól?
    * Paraméterezett tesztesetek használata.
    * Mikor/miért ad vissza egy stream `Optional`-t?
    * Tesztesetek futtatása Mavenből, verziószámok, stb. - egyéni problémák megbeszélése.

## 2021.06.15.

- Ezen a napon csoportmunka volt. Kristóf már napközben kiírta a Slack-re, hogy milyen 
  módon kellene előkészíteni a feladatot, hogy ne a technikai részletekkel menjen az idő 
  a konzultáción.
- Ezután kiadta a feladatot és párokba osztotta a résztvevőket. A közös munkát GitHub-on 
  keresztül kellett megoldani.
- Majd körülbelül egy óra teljes csend és elmélyült munka következett, két-három rövid 
  kérdésfeltétellel és válasszal a feladat értelmezésére vonatkozóan.
- A konzultáció végén pedig megbeszéltük a tapasztalatokat, levontuk a tanulságokat. A megoldás
  közös megbeszélését áttettük a következő konzultációra.
- A feladat szövege egyben:

```
# Csapatmunka feladat

## Feladat
A mai feladatban az EB meccsek eredményeit kell egy alkalmazásban  
tárolnod, és különböző feladatokat elvégezned.

### Game
Legyen egy `Game` nevű osztályod a következő attribútumokkal
+ `firstCountry (String)`
+ `secondCountry (String)`
+ `firstCountryScore (int)`
+ `secondCountryScore (int)`

Legyen benne egy metódus ami visszaadja a győztes ország nevét!

### GameRepository
Legyen egy `GameRepository` nevű osztályod, melynek van egy meccseket
memóriában tároló listája.

A listához elemet kétféleképpen lehet hozzáadni. Vagy egy `addGame(Game game)` metódussal,
vagy fájlból beolvasva, ahol a fájl egy csv állomány.

### GameService
Legyen egy `GameService` nevű osztályod, ami különböző statisztikai adatokat jelenít meg.
Legyen egy `GameRepository` attribútuma amin keresztül eléri a benne lévő listát.

Megvalósítandó metódusok:

+ Határozd meg a legnagyobb gólkülönbséggel véget ért mérkőzést
+ Határozd meg hogy egy paraméterül kapott ország hány gólt rúgott eddig
+ Határozd meg az eddig legtöbb gólt rúgó országot

### Tesztelés
Mindegyik osztályhoz legyen külön tesztosztály. A nem generált metódusokhoz legyen teszteset, lehetőleg több.
A `GameService` osztály második metódusát paraméterezett teszttel végezd. Ez lehet akár dinamikus teszteset is.
```

## 2021.06.16.

- Kristóf a Slack-en feltett kérdéseket válaszolta meg:

    * Miért AssertJ-t használunk inkább és nem Hamcrestet?
    * A "tuple" fogalma és az AssertJ `extracting()` metódusának használata, jó részletesen.
    * AssertJ soft assert kétféle létrehozása közötti különbség.
    * Mockolás: színész - dublőr példával, szintén jó részletesen.
    * `ArgumentCaptor` használata, röviden.
 
- A konzultáció végén fakultatív beszélgetés folyt az előző napi csoportmunkában megoldott 
  feladattal kapcsolatos tapasztalatokról, valamint GitHub és statikus factory metódusok témákban.

## 2021.06.18. délelőtti konzultáció

- Kristóf a megjelent hallgatókkal végigment az előző heti elméleti tananyagon (tesztelés), 
  ismétlő kérdések formájában.

## 2021.06.18. délutáni konzultáció

- A konzultációt Medzihradszky Dénes vendég előadó tartotta. A bemutatkozás után a következő témákról 
  beszélt az előadása során:
    * Tesztelési alapelvek. 
    * A tesztelés folyamata (a tervezéstől a lezárásig).
    * Humán pszichológia és a tesztelés kapcsolata.
    * Tesztelői és fejleszői gondolkodás különbözősége.
    * A tesztelés szerepe és helye egy szoftver létrehozási folyamatában.
    * Teszttípusok.
    * A kód statikus elemzése, amelyet az IDE és a fordítóprogram végez.
    * Teszttechnikák.
    
- Az előadás PDF anyaga elérhető.
- Az oktató szívesen várja a kérdéseket a denes.medzihradszky@training360.com e-mail címen.

## 2021.06.21.

- A konzultáció első 20 percében technikai problémákat beszéltünk meg. Ami fontos, hogy itt van bemutatva 
  élőben az, hogy hogyan kellene létrehozni a tanfolyami gyakorlati feladatok megoldására szolgáló 
  projektet, mert ez többeknek nem volt világos. István írta még a Slack-en korábban, hogy az elnevezések 
  az utánkövethetőség és ellenőrizhetőség miatt a következők legyenek: "Kérem, hogy a teszt témakörhöz 
  tartozó projekteket a `senior-solutions` repository-ban, a `locations-solution` mappába helyezzétek el. 
  A Spring Bootos feladatokat pedig a `locations-spring-solution` mappában."
- Ezután Kristóf röviden beszélt arról, hogy mit is jelent az hogy Spring, mi az, hogy keretrendszer.
- Majd a _dependency injection_ fogalmát magyarázta meg, és elkezdte megmutatni az alapvető 
  Springes annotációk működését.
- Megbeszéltük, hogy a Spring egy **Inversion of Control (IoC)** keretrendszer, melynek lényege, 
  hogy a komponenskezelést (pl. létrehozást, példányosítást, paraméterezést, megszüntetést, 
  metódushívást) kiemeljük a programkódból, és egy külső keretrendszerre bízzuk (mint pl. a Spring), 
  ő pedig átveszi az irányítást és megcsinálja ezeket helyettünk.
- Valamint hogy a _dependency injection_ fogalma nem egyenlő a _dependency inversion_ fogalmával. Ez 
  utóbbihoz Kristóf belinkelt egy cikket: [https://letscode.hu/2016/04/26/tiszta-kod-5-resz-a-s-o-l-i-d-alapelvek/](https://letscode.hu/2016/04/26/tiszta-kod-5-resz-a-s-o-l-i-d-alapelvek/)
  Emlékeztetőül: 
  
    * **Dependency inversion**-nek nevezzük azt, amikor egy osztály úgy használja egy másik osztály
      objektumát, hogy nem az osztályára hivatkozik, hanem az általa implementált interfészre.
      Azaz amikor nem egy osztályra mutat a függőség, hanem annak egy interfészére. 
    * A **dependency injection** pedig a függőség befecskendezése. Azt jelenti, hogy egy objektum más 
      objektumok függőségeit elégíti ki, vagyis egy osztály referencia típusú attribútumának külső 
      forrásból adunk értéket. Háromféle létezik belőle: attribútum injection, konstruktor injection 
      és setter injection.