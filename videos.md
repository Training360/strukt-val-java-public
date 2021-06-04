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