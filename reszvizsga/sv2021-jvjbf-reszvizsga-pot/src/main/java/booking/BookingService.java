package booking;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookingService {


    private List<Accommodation> accommodations = new ArrayList<>();

    private AtomicLong id = new AtomicLong();

    private ModelMapper modelMapper;

    public BookingService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<AccommodationDTO> getAccommodations(Optional<String> city) {

        Type targetListType = new TypeToken<List<AccommodationDTO>>() {}.getType();

        List<Accommodation> filtered = accommodations.stream()
                .filter(a->city.isEmpty() || a.getCity().equalsIgnoreCase(city.get()))
                .toList();

        return modelMapper.map(filtered,targetListType);
    }

    private Accommodation findById(long id){
        return accommodations.stream()
                .filter(m->m.getId()==id)
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("Cannot find"));
    }

    public AccommodationDTO getAccommodation(long id) {

        return modelMapper.map(findById(id),AccommodationDTO.class);
    }

    public AccommodationDTO createAccommodation(CreateAccommodationCommand command) {


        Accommodation actual = new Accommodation(id.incrementAndGet(),command.getName(), command.getCity(),command.getMaxCapacity(),command.getPrice());

        accommodations.add(actual);
       AccommodationDTO result =  modelMapper.map(actual,AccommodationDTO.class);

       return result;

    }

    public AccommodationDTO addNewReservation(long id, CreateReservationCommand command) {
        Accommodation actual = findById(id);

        actual.addNewReservation(command.getNumberOfPeople());

        return modelMapper.map(actual,AccommodationDTO.class);

    }

    public AccommodationDTO updatePrice(long id, UpdatePriceCommand command) {
        Accommodation actual = findById(id);
        if(actual.getPrice()!=command.getPrice()){
            actual.setPrice(command.getPrice());
        }

        return modelMapper.map(actual,AccommodationDTO.class);

    }

    public void deleteAll() {

        accommodations.clear();
        id= new AtomicLong();
    }
}
