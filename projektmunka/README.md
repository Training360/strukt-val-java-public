# Strukt�rav�lt� r�sztvev�k halad�s�t nyomonk�vet� alkalmaz�s

## V�zi�

A projektmunk�t egy val�s ig�ny keltette �letre. A Strukt�rav�lt�
projekt sor�n a r�sztvev�k el�rehalad�s�nak ellen�rz�se, t�rol�sa,
lek�rdez�se, �ttekint�se el�g k�r�lm�nyes, k�l�nb�z� rendszerekben
t�rt�nik.

A projektmunka eredm�ny�t fel fogjuk haszn�lni a k�vetkez�
�vfolyamokn�l, teh�t egy val�s szoftvert fogunk
megval�s�tani. Az egy�ni �tletek megval�s�t�s�ra is van lehet�s�g
a megadottakon fel�l. A legjobbakat �sszev�logatjuk, �s egy
projektbe m�soljuk.

Amennyiben a k�telez� r�szt megcsin�ltad, a tov�bbiakr�l egyeztess�nk,
mert m�g t�bb r�szlet ki van dolgozva!

Projekt neve legyen `mentor-tools`!

## Sprint 1

### Funkcion�lis k�vetelm�nyek

A k�vetkez�ket kell karbantartani:

* �vfolyamokat (`TrainingClass`)
* R�sztvev�ket (`Student`), egy r�sztvev� ak�r t�bb �vfolyamon is szerepelhet
* Tanmeneteket (`Syllabus`)
* A tanmenetekhez modulok (`Module`), ahhoz leck�k (`Lesson`) tartoznak
* A tanmenetet �vfolyamhoz lehet rendelni
* Be lehessen jel�lni, hogy melyik r�sztvev� melyik leck�t dolgozta fel: megn�zte a vide�t, �s elk�sz�tette a
  gyakorlati feladatot

A k�l�nb�z� adatokat kell nyilv�ntartani:

#### �vfolyam

* Elnevez�s (nem �res, max. 255 karakter)
* Kezd�s d�tuma (tetsz�leges)
* Befejez�s d�tuma (tetsz�leges, k�s�bb legyen, mint a kezd�s)

Lehet list�zni, lek�rdezni, l�trehozni, mindh�rom attrib�tumot m�dos�tani, t�r�lni.

#### R�sztvev�k

* N�v (nem �res, max. 255 karakter)
* E-mail c�m (nem �res, max. 255 karakter)
* GitHub felhaszn�l�n�v (nem �res, max. 255 karakter)
* Megjegyz�s

#### �vfolyam - r�sztvev� kapcsolat

Egy r�sztvev� be tud iratkozni egy tanfolyamra. Ezt a beiratkoz�s
oszt�ly/t�bla tartalmazza (`registration`).

A beiratkoz�snak vannak st�tuszai: akt�v (`ACTIVE`), kil�p�s alatt (`EXIT_IN_PROGRESS`), kil�pett (`EXITED`).
Hiszen egy r�sztvev� egy �vfolyamon lehet akt�v, m�g egy m�sikon kil�pett.

Beiratkoz�s t�rt�nhet a `/trainingclasses/{id}/registrations` c�men. Meg kell adni a r�sztvev� azonos�t�j�t.
Itt le lehet k�rdezni az �vfolyamra beiratkozottakat (a r�sztvev�kr�l csak az id-j�t, nev�t �s st�tusz�t adja vissza).

Egy r�sztvev� beiratkoz�sait is le lehet k�rdezni a `/students/{id}/registrations` c�men. 
Csak az �vfolyamok id-j�t �s nev�t adja vissza.

#### Tanmenet

* N�v (nem �res, max. 255 karakter)

Egy tanmenet t�bb �vfolyamhoz is tartozhat, egy �vfolyamhoz egy tanmenet!

Lehet list�zni, lek�rdezni, l�trehozni, minden adatot m�dos�tani, t�r�lni.

#### Modul

* C�m (nem �res, max. 255 karakter)
* URL (nem �res, max. 255 karakter)

Egy tanmenethez t�bb modul is tartozhat.

Lehet list�zni, lek�rdezni, l�trehozni, minden adatot m�dos�tani, t�r�lni.

#### Lecke

* C�m (nem �res, max. 255 karakter)
* URL (nem �res, max. 255 karakter)

Egy modulhoz t�bb lecke is tartozhat.

Lehet list�zni, lek�rdezni, l�trehozni, minden adatot m�dos�tani, t�r�lni.

#### Lecke elv�gz�se

* Melyik r�sztvev�
* Melyik leck�t
* Vide�t, gyakorlati feladatot, vagy mindkett�t

Szint�n egy kapcsol� entit�sra van sz�ks�g, ami hivatkozik egy r�sztvev�re �s egy leck�re (`LessonCompletion`). 
Valamint az adatai: 

* Vide�t megn�zte-e (nem `boolean`, hanem enum: `COMPLETED`, `NOT_COMPLETED`)
* Gyakorlati feladatot elv�gezte-e (nem `boolean`, hanem enum: `COMPLETED`, `NOT_COMPLETED`)
* Elv�gz�s�nek d�tuma
* Commit URL-je (lehet �res, max. 255 karakter)

El�rhet� a `/students/{id}/lessioncompletition` c�men.

Lehet list�zni, lek�rdezni, l�trehozni, minden adatot m�dos�tani (kiv�ve a r�sztvev�t), t�r�lni.

### Nem-funkcion�lis k�vetelm�nyek

Klasszikus h�romr�teg� alkalmaz�s, MariaDB adatb�zissal,
Java Spring backenddel, REST webszolg�ltat�sokkal.

K�vetelm�nyek t�telesen:

* SQL adatb�zis kezel� r�teg megval�s�t�sa Spring Data JPA-val (`Repository`)
* Flyway - a scriptek a funkci�kkal egy�tt k�sz�ljenek, sz�val ahogy beker�l az entit�s, �gy ker�lj�n be egy
  plusz script is, ami a t�bl�t l�trehozza
* �zleti logika r�teg megval�s�t�sa `@Service` oszt�lyokkal
* Integr�ci�s tesztek megl�te (el�g TestRestTemplate tesztek), legal�bb 80%-os tesztlefedetts�g
* Controller r�teg megval�s�t�sa, RESTful API implement�l�s�ra. Az API v�gpontoknak a `/api` c�men kell el�rhet�eknek lenni�k.
* Hibakezel�s, valid�ci�
* Swagger fel�let
* HTTP f�jl a tesztel�shez
* Dockerfile
* GitHub Classroomban kell a megold�st beadni
* Commitolni legal�bb entit�sonk�nt, �s hozz� tartoz� REST v�gpontonk�nt

Cheat sheet: https://github.com/Training360/strukt-val-java-public/blob/master/annotations%20-%20cheat%20sheet.md