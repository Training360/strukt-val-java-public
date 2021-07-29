package cinema;


import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CinemaService {

    private List<Movie> movies = new ArrayList<>();


    private AtomicLong id = new AtomicLong();



    private ModelMapper modelMapper;

    public CinemaService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public List<MovieDTO> getMovies(Optional<String> title) {

        Type targetListType = new TypeToken<List<MovieDTO>>() {}.getType();

        List<Movie> filtered = movies.stream()
                .filter(m->title.isEmpty() || m.getTitle().equalsIgnoreCase(title.get()))
                .toList();
        return modelMapper.map(filtered,targetListType);
    }


    public MovieDTO createMovie(CreateMovieCommand command) {

        Movie m = new Movie(id.incrementAndGet(),command.getTitle(),command.getDate(),command.getMaxReservation());

        movies.add(m);

        return modelMapper.map(m, MovieDTO.class);
    }

    private Movie findById(long id){
        return movies.stream()
                .filter(m->m.getId()==id)
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("Cannot find"));
    }

    public MovieDTO createNewReservation(long id, CreateReservationCommand command) {

        Movie actual = findById(id);

        actual.newReservation(command.getNumberOfSeats());

        return modelMapper.map(actual,MovieDTO.class);

    }

    public MovieDTO updateMovieDate(long id, UpdateDateCommand command) {
        Movie actual = findById(id);

        if(actual.getDate().isBefore(command.getDate())){
            actual.setDate(command.getDate());
        }

        return modelMapper.map(actual,MovieDTO.class);

    }

    public void deleteAll() {

        movies.clear();
        id = new AtomicLong();

    }

    public MovieDTO getMovie(long id) {


        return modelMapper.map(findById(id),MovieDTO.class);
    }
}
