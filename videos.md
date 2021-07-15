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
    
- Az előadás PDF anyaga elérhető a [Tesztelésről fejlesztőknek](https://e-learning.training360.com/courses/take/training360-str-hgk-vallalati-backend-kepzes-kozpont/pdfs/25392021-tesztelesrol-fejlesztoknek) 
  linken.
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
- Valamint megbeszéltük azt is, hogy a _dependency injection_ fogalma nem egyenlő a _dependency inversion_ 
  fogalmával. Ez utóbbihoz Kristóf belinkelt egy cikket: [A **S-O-L-I-D** alapelvek](https://letscode.hu/2016/04/26/tiszta-kod-5-resz-a-s-o-l-i-d-alapelvek/)
  címmel.
  Emlékeztetőül: 
  
    * **Dependency inversion**-nek nevezzük azt, amikor egy osztály úgy használja egy másik osztály
      objektumát, hogy nem az osztályára hivatkozik, hanem az általa implementált interfészre.
      Azaz amikor nem egy osztályra mutat a függőség, hanem annak egy interfészére. 
    * A **dependency injection** pedig a függőség befecskendezése. Az osztály nem maga példányosítja 
      a függőségeit (amik referencia típusú attribútumok), hanem csak definiálja azokat. Az attribútum 
      külső forrásból kap értéket. Háromféle létezik belőle: attribútum injection, konstruktor injection 
      és setter injection.
      
- A konzultáció végén Kristóf érdekességként megmutatta még azt, hogyan lehet hozzáférni a Spring által 
  példányosított Spring Bean objektumokhoz.

## 2021.06.22.

- Ezen a konzultáción önálló feladatmegoldás volt, a végén hallgatói munka nyomán 
  közösen végignéztük a megoldás menetét.
- A feladat szövege a következő:

```
## Feladat

Ebben a feladatban egy használtautó weboldal szolgáltatásait modellezzük.

Legyen egy KmState osztály melyben egy dátum és egy aktuális kilométer van.  

Készíts egy `Car` osztály, melynek attribútumai a márkája, a belül a típusa,
a kora, illetve legyen még egy Enum az állapotáról, mely lehet
kiváló, normális vagy rossz, ezen felül legyen egy KmState Lista.

Legyen egy `CarService` osztály mely legyen már Spring komponens. Ebben legyen a 
kocsik listája néhány beégetett adattal.

Legyen egy `CarController` osztály, mely szintén Spring komponens.

Legyen még egy `HelloController` és `HelloService` osztályod is, mely a kezdő oldal feladatait oldja meg. 

Ezen struktúra segítségével oldd meg a következő feladatokat:

+ Alkalmazás indításakor a böngészőben jelenjen meg az "Üdvözlünk az oldalon!" szöveg!
+ /cars url-n keresztül jelenjen meg az összes autó.
+ /types végponton keresztül jelenjenek meg a listában található márkák. 

## Tesztelés

+ Készíts unit tesztet a CarService osztály tesztelésére!
+ A CarController osztályt kétféleképpen teszteld:

    1. Unit teszt ahol a CarService osztályt mockolod.
    2. Integrációs teszt ahol beindítod a Springet és úgy teszteled a működést.
```

## 2021.06.23.

A konzultációt István tartotta a Dockerről. A következő témákról és kérdésekről 
esett szó részletesen:

* Miért számított nagy dolognak a szállítmányozásban a konténerek létrehozása? 
* A konténeres szállítás előnyei.
* Ennek analógiájára megalkották a szoftverek konténerekben való üzemeltetésének
  koncepcióját. Mik ennek az előnyei?
* Mi dolga mindezzel a szoftverfejlesztőnek?
* Honnan lehet tudni, hogy konténer indításakor milyen környezeti változókat lehet átadni?
* Honnan lehet tudni, hányas porton indul el a konténerben egy alkalmazás?
* A docker használatához szükséges parancsok.
* Egy docker image létrehozása, majd az alkalmazás elindítása konténerben.
* Docker layerek.
* A `Dockerfile`-ba írható parancsok.
* Egyéb témák röviden (Twelve-Factor App, Maven wrapper)

## 2021.06.25. délelőtti konzultáció

- Kristóf a megjelent hallgatókkal végigment az előző heti elméleti tananyagon (Spring, Docker),
  ismétlő kérdések formájában.

## 2021.06.25. délutáni konzultáció

- A konzultáció elején közös interaktív gyakorló teszt volt.
- Ezután Kristóf kiadott egy gyakorló feladatot, amelynek szövege a következő:

 ```
# Feladat

A mai feladatban bicikli sharing alkalmazást készítünk.

Adott a bikes.csv állomány, melyben egy-egy bicikli adatai találhatók:
* A bicikli azonosítója
* Az utolsó felhasználó egyedi azonosítója 
* Az utolsó leadás pontos ideje 
* Az utolsó úton megtett távolság kilométerben

Legyen egy `BikeService` nevű osztályod ami beolvassa a fájlt és eltárolja egy listában.
A beolvasás ne a program indulásakor, hanem az első kérés alkalmával valósuljon meg. 
Azaz a listát visszaadó hívás esetén ellenőrizzük, hogyha a lista üres akkor beolvasunk, 
ha nem akkor visszaadjuk a listát.

A BikeController osztály a `/history` végponton kersztül érje el a lista minden elemét 
minden adattal együtt.

A `/users` végponton keresztül kapjuk meg a userek azonosítóit

Ne felejts el teszteket írni, unit és integrációs tesztet egyaránt!
```

- A feladathoz tartozó `bikes.csv` fájl tartalma a következő:

```
FH675;US3434;2021-06-24 17:12:50;0.8
FH676;US3a34;2021-06-25 11:20:42;1.2
FH676;US3334;2021-06-25 12:40:37;0.7
FH636;US336;2021-06-23 09:36:12;1.9
FH631;US346;2021-06-24 08:53:21;2.9
```

- A konzultáció további részében ezt a feladatot oldotta meg mindenki egyénileg, 
  közben elhangzott néhány kérdés. Az utolsó fél órában egyéni problémák 
  megoldása folyt, képernyőmegosztással.

## 2021.06.28.

- A konzultáció elején Kristóf tartott egy rövid bevezetést a RESTful webszolgáltatások 
  témakörébe.
- Ezután a nap folyamán a Slack-en feltett kérdéseket beszéltük végig. A következő témák 
  kerültek részletesen megbeszélésre:
  
    * `ResponseEntity` szerepe és használata.
    * `@RequestParam`, `@PathVariable` és `@RequestBody` használata, hol melyikre van szükség?
    * Pontosan mit csinál az `@Autowired` annotáció?
    * A `@NoArgsConstructor`, az `@AllArgsConstructor` és a `@RequiredArgsConstructor` annotációk 
      használata? Ezek közül melyik kell feltétlenül egy olyan osztályra, amelynek a Lombokkal 
      generáltatjuk a getter és setter metódusait? (Ugyanehhez a témához a konzultáció végén újra 
      visszatértünk.)
    * Mikor célszerű az `AtomicLong` típust használni?
    * Hogyan használható a Lombok abban az esetben, ha van egy `final` módosítószóval ellátott 
      adattag az osztályban?
    * Hány `@RequestParam` annotációval ellátott paramétert adhatunk meg egy metódusnak, illetve 
      össze lehet-e ezeket fogni egybe valahogyan, ha nagyon sok paraméter lenne?
    * Egy controller és egy service osztályhoz szokásosan hány repository tartozik?
    * POST és PUT műveletek közötti különbségek, illetve a CRUD műveletek és a HTTP metódusok 
      közötti megfeleltethetőség konvenció szerint.
    * A HTTP kérések felépítése.
    
- A konzultáció végén Kristóf végigcsinálta a múlt heti bicikli sharing feladatot, hogy meg tudjuk 
  beszélni a megoldást közösen.

## 2021.06.29.

- Ezen a konzultáción önálló feladatmegoldás volt, a végén Kristóf végigvezette a megoldás menetét.
- A feladat szövege a következő:

```
## Feladat

A mai feladatban ismét egy filmekkel foglalkozó alkalmazást kell összeraknod.

A `Movie` entitásnak legyen egy azonosítója, egy címe, egy hossza, egy, az eddigi értékeléseket 
tartalmazó listája és egy értékelésátlaga.

Minden egyes alkalommal amikor egy értékelést kap a film, akkor az értékelésátlag ennek megfelelően változik!

Legyen egy `MovieService` osztályod, ami listában tárolja a filmeket. Kezdetben a lista üres, később 
tudunk filmet hozzáadni. 

Legyen egy `MovieController` ami alapértelmezetten az `api/movies` URL-en várja a kéréseket. 

A következő funkciókat kell megvalósítani:

* Lehessen lekérni a filmeket, de csak a cím, a hossz és az átlagértékelés jelenjen meg, opcionálisan 
query-ként lehessen szűrni filmcímre.
* A `/{id}` URL-en keresztül lehessen egy aktuális filmet lekérdezni. 
* Lehessen felvenni új filmet a listába, ilyenkor csak a címet és a hosszt adjuk meg.
* A `/{id}/rating` URL-en keresztül lehessen egy filmre értékelést adni és lekérdezni. 
Ekkor csak egy számot kapunk egész számként, és ezt adjuk hozzá a film értékelés listájához, 
majd térjünk vissza a már frissített értékekkel. Figyeljünk arra, hogy ugyanazt az értékelést 
többször is megkaphatja a film. 
* Lehessen filmet törölni az `id` alapján. 

Ne felejts el unit és integrációs tesztet írni!
```

- Kristóf azt fűzte még hozzá a feladathoz, hogy mivel maga a feladat elég kiadós egy konzultáció idejére, ezért a
  tesztelést csak HTTP-requestek formájában végezzük, és a teszteseteket a konzultáció idején kívül, otthon érdemes
  megírni.
- A konzultáció során elhangzott végig önálló munka folyt, valamint elhangzott pár kisebb egyéni kérdés a feladattal
  kapcsolatosan.

## 2021.06.30.

- Ezen a konzultáción a Slack-en feltett és a konzultáció közben felmerült kérdéseket beszéltünk meg.
  A következő témák kerültek sorra:

    * A tegnapi konzultáció végén, a videófelvétel leállítása után került sor egy hallgatónál felmerült
      hiba közös debugolására, amit most Kristóf a hasznossága miatt röviden újra bemutatott.
    * Röviden visszatértünk a két nappal ezelőtti `@RequestParam`, `@PathVariable`, `@RequestBody` közötti
      különbség témájához.
    * Szó esett röviden a jövő heti vizsga technikai részleteiről.
    * Felmerült egy kérdés, hogy a `ModelMapper`-t vajon mockolni kell-e, ha unit tesztet szeretnénk írni a
      service osztályunkra. Illetve ha nem mockoljuk, akkor az még unit tesztnek minősül-e?
    * Kristóf részletesen összefoglalta, hogy milyen típusú tesztelési lehetőségek vannak, milyen típusú
      tesztosztályokat lehet írni, melyik hogy működik, melyiket mire használjuk.
    * Majd a validáció és az egyszerű hibakezelés került szóba.
    * Ezután további technikai részletek következtek a vizsgáról.
    * És még egy kérdés arról, hogy mi mindenre tehetünk annotációt.

## 2021.07.02. délelőtti konzultáció

- Kristóf a megjelent hallgatókkal végigment az előző heti elméleti tananyagon (REST webszolgáltatások),
  ismétlő kérdések formájában.

## 2021.07.02. délutáni konzultáció

- A konzultáció elején közös interaktív gyakorló teszt volt.
- Ezután Kristóf gyorsan végigszaladt az előző napra kiadott gyakorló teszt kérdésein, közösen 
  megbeszéltük a helyes megoldásokat.
- A konzultáció további részében Kristóf részletes magyarázattal ellátva és a felmerülő hallgatói kérdéseket 
  megválaszolva bemutatta a hét közbeni gyakorló vizsgafeladat megoldását.

## 2021.07.05.

- Könyvtárnév, project, repository elnevezések. Monolit alkalmazás kontra microservice.
- Hogyan ellenőrizzük a feladatmegoldásokat. Hogyan commitoljunk. Hány repo hozható létre? Repo pinelése GitHubon.
- Mi a különbség a `findFirst()` és a `findAny()` között
- Lehet-e stream bejárás közben az eredeti kollekciót módosítani?
- RFC 7807-ben mi az a `type` URI?
- ModelMappert mockoljunk-e? Szerintem ne.
- Injektálás típus alapján.
- Miért válasszuk szét a unit és integrációs teszteket?
- Mi az a Tomcat és Servlet API?
- HTTP protokoll tulajdonságai.
- Hogy néz ki a hívási lánc egy request esetén?

## 2021.07.12.

- IntelliJ IDEA JDK verziójának beállítása `maven-compiler-plugin`-nel.
- Validáció beépített annotációval
- Hiba, validációs hiba jelzése Zalando `problem-spring-web-starter` használatával
- Saját validáció implementálása
- Bejövő http request feldolgozása, validáció
- Unit és integrációs tesztek, integrációs tesztek típusai
- Validáció tesztelése RestTemplate használatával
- ApplicationContext, ComponentScan, `@Bean`, `@Autowired`, Dependency Injection működése
- Mit jelent, hogy injektáljunk, ahelyett, hogy static factory metódust használjunk?
- Hogy injektáljunk, ha egy attribútumnak nem akarunk értéket adni?

## 2021.07.13.

- A konzultációt István tartotta. Tegnap óta és az órán felmerült, valamint korábbról
maradt kérdéseket válaszolt meg:

* `Violation`: Véletlen, hogy a videóban is így lett elnevezve és
  a `problem-spring-web-starter` is ezzel a névvel generálja a
  validációs hibát reprezentáló osztályt. Valószínűleg közös
  megegyezésen alapul.
* Validációs hibák üzenetei egy valós alkalmazásnál nem beégetett
  üzenetek, hanem nyelvi kulcsok, és a hozzájuk tartozó értéket
  egy külön fájlból olvassa ki a program. (Igazi alkalmazásban a
  forráskódban nem lehet olyan szöveg, amit a felhasználónak írunk
  ki, hanem ezek mind külön fájlokban vannak, a forráskódban pedig
  nyelvi kulcsok találhatóak.)
* Különbség a HTTP POST és PUT metódusa és a használatuk között.
* Adatbázisból való törlésnél konvenció szerint nem adjuk vissza
  a letörölt entitást.
* Mi a különbség az entitás lista DTO listává alakításának két
  módja között? (Az egyik mód az, ha van egy entitás listám és
  azt a `ModelMapper` mappeli át DTO listává, a másik mód pedig
  az, hogy egy streamben először az entitásokat mappelem át DTO-vá,
  majd azokat gyűjtöm ossze egy listába.)
* Adatbázis adatok módosításánál azt a legegyszerűbb kezelni, ha
  nem csak a módosuló adatokat küldjük be, hanem mindent újra.
* Egy open API lekérdezése.
* Mi jelent az, hogy "legacy-application"?
* Lehetséges-e Docker Desktop és VirtualBox együttes használata a gépen.
* Docker használata esetén az adatok konténertől külön történő tárolása.
* Hogyan történik a kódduplikátumok ellenőrzése? (Vizsga esetén egymásról
  való másolás felderítésére. :) )
* Mi a Docker konténer valódi használati módja az életben?
* Miért éri meg opensource projektet fejleszteni?

- Ezután az erre a hétre betervezett JPA tananyaggal kapcsolatosan 
felmerült néhány apróbb kérdést beszéltünk meg? (Mikor generáltatjuk a JPA-val 
az SQL-utasításokat, és mikor használjuk a Flyway-t? Vizsgán, projekt feladatban 
melyiket kell használni? Néhány olyan is felmerült, amelyre az volt a válasz, hogy 
később lesz róla bővebben szó.)
- Végül István kiosztott egy házi feladatot:

Írjatok a NAV-hoz egy időpontfoglaló rendszert! A `/api/types` címen
le lehessen kérdezni az ügytípusokat, melyek kódok, és hozzá tartozó értékek!
Pl.: 001 - Adóbevallás, 002 - Befizetés, stb. Ezeket egy `NavService`
listájában tárold el (két attribútummal rendelkező objektumok)!

Írjátok meg a controller részét az időpontfoglalásnak! Az `/api/appointments/` címen legyen elérhető. 
A következő adatokat várja (command, ellenőrzésekkel):

* adóazonosító jel (CDV ellenőrzés: pontosan tíz számjegyet tartalmaz. Fogni kell az első kilenc számjegyet,
és megszorozni rendre 1, 2, ..., 9 számmal. Az eredményt kell összegezni,
majd maradékos osztani 11-el. A 10. számjegynek meg kell egyeznie
ezzel a számmal (maradékkal).)
* időpont kezdete: jövőbe kell mutatnia
* időpont vége: jövőbe kell mutatnia, az időpont végének a kezdete után kell lennie
* ügytípus azonosítója (service-ben lévő lista alapján kell ellenőrizni)

Trükkök:

* Az időpontoknál két attribútum összefüggését kell vizsgálni. Vagy csinálj belől egy osztályt,
két attribútummal, és arra tegyél annotációt! Vagy az egész commandra tegyél annotációt!
(Valós alkalmazásban az első jobb, ez két össztartozó adat, amit jó egyben kezelni, de a többitől
elválasztva.)
* A ügytípusnál a validátorból kell elérni a service-t. Próbáld az `@Autowired` annotációval field injektálni!

## 2021.07.14.

- István ezen a konzultáción megmutatta, hogyan lehet egy alkalmazáshoz felhasználói felületet 
  fejleszteni egy webes alkalmazáshoz. Ez a tanmenetnek nem része, nem lesz vizsgán sem, de érdemes 
  tudni róla, hogyan is működik ez. És nem mellékesen meglehetősen izgalmas is volt látni ennek a menetét.
  A projekt megtalálható a `employees-fe` könyvtárban.
- A konzultáció második felében az erre a hétre kötelező JPA tananyaghoz kapcsolódó kérdéseket 
  beszéltünk meg közösen:
  
  * Mitől függ, hogy mikor kell az `@ElementCollection` és mikor a kapcsolatokhoz tartozó annotációkat használni 
    egy `Collection` típusú attribútumon?
  * A többi kérdés mind a kapcsolatok témakörhöz tartozott (owner és inverse, cascade, annotációk, illetve hogyan 
    kezeli a JPA, ha a kapcsolat egyik vagy másik oldalán szeretnék lementeni egy új elemet).