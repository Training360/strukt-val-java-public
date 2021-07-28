package cinema;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cinema")
public class CinemaController {

    private CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }



    @GetMapping
    public List<MovieDTO> getMovies(@RequestParam Optional<String> title ){
        return cinemaService.getMovies(title);
    }

    @GetMapping("/{id}")
    public MovieDTO getMovie(@PathVariable("id") long id){
        return cinemaService.getMovie(id);
    }


    @PostMapping
    public MovieDTO createMovie(@Valid @RequestBody CreateMovieCommand command){
         return cinemaService.createMovie(command);
    }

    @PostMapping("/{id}/reserve")
    public MovieDTO addNewReservation(@PathVariable("id") long id, @RequestBody CreateReservationCommand command){
        return cinemaService.createNewReservation(id, command);
    }

    @PutMapping("/{id}")
    public MovieDTO updateDate(@PathVariable("id") long id, @RequestBody UpdateDateCommand command){
          return cinemaService.updateMovieDate(id,command);
    }

    @DeleteMapping
    public void deleteAll(){
        cinemaService.deleteAll();
    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handleNotFound(IllegalArgumentException ex){
        Problem problem =
                Problem.builder()
                        .withType(URI.create("cinema/not-found"))
                        .withTitle("Not Found")
                        .withStatus(Status.NOT_FOUND)
                        .withDetail(ex.getMessage())
                        .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Problem> handleWrongNumberOfSeats(IllegalStateException ex){
        Problem problem =
                Problem.builder()
                        .withType(URI.create("cinema/bad-reservation"))
                        .withTitle("Too Much")
                        .withStatus(Status.BAD_REQUEST)
                        .withDetail(ex.getMessage())
                        .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

}
