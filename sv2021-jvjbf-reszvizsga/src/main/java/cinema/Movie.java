package cinema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {


    private long id;

    private String title;
    private LocalDateTime date;
    private int maxReservation;
    private int freeSpaces;

    public Movie(long id, String title, LocalDateTime date, int maxReservation) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.maxReservation = maxReservation;
        freeSpaces = maxReservation;
    }


    public void newReservation(int numberOfSeats){
       if(numberOfSeats<freeSpaces){
           freeSpaces-=numberOfSeats;
       }else{
           throw new IllegalStateException("Bad request!");
       }
    }
}
