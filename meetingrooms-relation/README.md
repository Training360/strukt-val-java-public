Egészítsd ki a `MeetingRooms` alkalmazást!
Készíts egy `Meeting` nevű osztályt, melynek adattagjai tárolják a meetinget vezető ember nevét (String) és azt,
hogy mettől meddig tart a meeting. A `MeetingRoom` osztálynak legyen egy `Meeting`-eket tartalmazó listája, getter,
setter és egy `addMeeting()` metódusa. A `MariaDbMeetingRoomsRepository` osztályban, amikor elmentünk egy
`MeetingRoom`-ot, mentsük el a hozzá tartozó `Meeting`-eket is egy külön táblába, melyben külső kulccsal
hivatkozunk a `MeetingRoom`-ra. Próbáld meg visszaolvasni a táblák tartalmait egy listába!

Példa SQL-utasítás a `Meeting`-ek táblájának létrehozására (értelemszerűen át kell írni):

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