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

## 2021.06.08.

- A konzultáció elején nagyon röviden technikai kérdéseket beszéltünk meg.
- Kristóf hozott egy feladatot, és utána ezzel telt el a konzultáció teljes ideje. Közös kódolás volt, 
  a streamek és a lambda kifejezések gyakorlására. 
  Először Kristóf megosztott a GitHubon egy előkészített projektet, amit forkolni kellett és klónozni a saját gépre, 
  majd ebben dolgozni. Több különböző metódust kellett implementálni streamek használatával. 
  Opcionális házi feladatként is kaptunk még további feladatokat.
- Kristóf gyakorló projektjének linkje: https://github.com/bkris21/streams-demo-public
- A feladat szövege: 
    * Órán megoldott feladatok:
      Határozd meg a rendelések darabszámát egy paraméterként kapott státusz alapján!
      Gyűjtsd össze azokat a rendeléseket, amelyekben van egy paraméterként kapott kategóriájú termék!
      Gyűjtsd össze azokat a termékeket a rendelések közül, amelyeknek egy paraméteról kapott összegnél magasabb az áruk!
    * További gyakorló feladatok:
      Írj egy metódust ami paraméterként vár két dátumot, és adjuk vissza a két dátum közötti árbevételt, 
      vagyis a két dátum közötti rendelések termékeinek az összértékét!
      Keressünk meg egy terméket a neve alapján, amit paraméterként lehet megadni!
      Adjuk vissza azt a rendelést, ami a legdrágább terméket tartalmazza. Ha több ilyen van bármelyiket!