package booking;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationDTO {


    private String name;
    private String city;
    private long availableCapacity;
    private int price;
}
