package booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accommodation {


    private long id;

    private String name;
    private String city;
    private List<Integer> reservation = new ArrayList<>();
    private long maxCapacity;
    private long availableCapacity;
    private int price;

    public Accommodation(long id, String name, String city, long  maxCapacity,int price) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.maxCapacity = maxCapacity;
        this.price = price;
        this.availableCapacity = maxCapacity;
    }

    public void addNewReservation(int number){
        if(number<availableCapacity){
            reservation.add(number);
            availableCapacity = maxCapacity-sumOfReservations();
        }else{
            throw new IllegalStateException("Bad request!");
        }
    }

    public long sumOfReservations(){
        return reservation.stream().collect(Collectors.summarizingInt(Integer::intValue)).getSum();
    }
}
