## Pirates

Ahoy Pirates!

Hozz létre egy három rétegű kalóz játék alkalmazást!

### Hajó
 
Legyen egy hajó (Ship) osztály, amiből csak egy példány lehet!

A hajónak legyen:

- egy neve
- egy kapitánya (Captain)
- egy matrózok listája (Sailor)
- egy tisztek listája (Officer)
- egy raktér a lopott aranyaknak (int, kezdőérték nulla)

Amikor elindul az alkalmazás rögtön jöjjön létre az alap állapot. Egy hajó, egy kapitány, két tiszt, tíz matróz. (Ezek a maximális számok a későbbiekben is!)

### Tengerészek

Legyen egy ősosztály (SeaDog), amiből leszármazik a kapitány, a tiszt, és a matróz osztályok.

Mindkinek van

- egy neve (String, létrehozáskor adható meg)
- egy ládikó, amiben az összeharácsolt aranyait gyűjti (int, kezdőérték nulla)
- egy halálszáma (int, létrehozáskor random generálódik)

Halálszám: A kapitánynak 1-10-ig sorsolunk egy számot, a tiszteknek 1-5-ig, a matrózoknak 1-3-ig.

### Lehetőségek

Mindegyik külön http hívás legyen!

- Listázd ki a hajó teljes állapotát, legénységestül, mindenestül!
- Menjenek a kalózok fosztogatni! Írj egy logikát, ami erre a hívásra a következőket csinálja:
	- Végigiterálunk a matrózok listáján, mindegyiknek random kisorsolunk egy számot 1-3-ig. Abban az esetben, ha ez a szám egybe esik a halálszámával, töröld ezt a matrózt.
	- Ismétled meg az előzőt a tisztek listájára is, csak itt 1-5-ig legyen a random számok sorsolása.
	- A kapitánynál is sorsolj ki 1-10-ig egy számot.
	- Abban az esetben ha a harc végén nem maradt se tiszt se matóróz, vagy meghalt a kapitány, küldj egy hibaüzenetet, és vége a játéknak.
	- Ha túlélte a kapitány, és legalább egy a legénység többi tagja közül, akkor sorsolj ki egy random számot 200-1000 között. Ennyi aranyat sikerült szerezni, ezt add hozzá a hajó rakteréhez.
	- A harc után kapjunk vissza némi információt: mekkora a zsákmány, és egy listát az elesett tisztek és matrózok neveivel. 
- Osztozkodás! Ha egy ilyen hívás történik, oszd el a hajóban található kincset a legénység között a következőképpen:
	- A kapitány megkapja a 50%-ot.
	- Ezután az egész 30%-át elosztjunk az élő tisztek között. (Ha egy sincs éppen, akkor ez az összeg marad a raktérben.)
	- Az élő matrózok között elosztjuk az egész 20%-át. (Ha nincs élő matróz, akkor az összeg itt is marad a közösben.)
	- Figyelj oda, hogy csak egész számokkal dolgozz! A maradék maradjon is a raktérben!
	- Az osztozkodás után kapjunk vissza némi információt: Mennyi volt a raktérben? Mennyit kapott a kapitány? Mennyi lett a tisztek jussa fejenként? Mennyi került a matrózok ládájába fejenként? Mennyi maradt a közösben? 
- Legénység toborzása! Felvehetsz egy új tagot a csapatba. Te adod meg a nevét, és hogy az illető matróz legyen vagy tiszt. (A halál száma példányosításkor generálódik!)
Persze senki nem jön ingyen a hajóra. Ha tisztet veszel föl, akkor 50 aranyat kell neki rögtön adnod a közösből. A matrózok beérik 10 arannyal is.
Fontos, hogy a matrózok létszáma nem lehet 10-nél több a hajón. Tisztból pedig csak 2 kell. Felvétel előtt mindig végezz ellenőrzést, és dobj hibaüzenetet, ha túl lenne lépve a keret.
Akkor is dobj hibaüzenetet, ha nincs a raktérben elegendő pénz az újonc számára!
Sikeres toborzás után kapjunk vissza egy üzenetet, például "Üdv a KapzsiKampó fedélzeten Johnny tiszt!".

### A játék vége

Ha a kapitány meghal, vagy egy harc végén nem marad se tiszt se matróz, onnantól bármilyen kérést indítasz, jöjjön egy hibaüzenet, hogy vége a játéknak.

----------------------------------------

### OPTIONAL 1.

A hajó rendelkezzen állapottal (int), ami kezdetben 100!  
A harc végén generálj egy random számot 10-40 között! Ezt vond le a hajó állapot értékéből!  
Legyen egy olyan http hívás, amely során javíthatod a hajódat a közös pénzből! 1 pontnyi javítás ára 10 arany.  
Ha a hajó állapota 0, akkor is vége a játéknak. 100 fölé nem mehet az érték. Csak addig javíthatsz, amíg van legalább 10 arany a közösben. (A legénység nem ad kölcsönt!)

### OPTIONAL 2.

Legyen egy reset funkció, amivel vesztés esetén visszaállítod a játékot az eredeti állapotára!

### OPTIONAL 3.

Dead man's chest! Akik meghalnak a harc során, azok ládájának tartalmát tedd át a közösbe!

### OPTIONAL 4.

Vegyél fel egy plusz attribútumot a legénység minden tagjának, amely megmutatja, hogy eddig hány csatát élt túl! Kezdőérték legyen nulla, harc során növeld egyel, ha túléli!
Ez az érték is legyen látható, mikor kilistázod a hajó állapotát!
