package booking;


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
@RequestMapping("/api/accommodations")
public class BookingController {


    private BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }


    @GetMapping
    public List<AccommodationDTO> getAccommodations(@RequestParam Optional<String> city){
        return service.getAccommodations(city);
    }

    @GetMapping("/{id}")
    public AccommodationDTO getAccommodation(@PathVariable("id") long id){
        return service.getAccommodation(id);
    }

    @PostMapping
    public AccommodationDTO addNewAccommodation(@Valid @RequestBody CreateAccommodationCommand command){

        return service.createAccommodation(command);

    }

    @PostMapping("/{id}/book")
    public AccommodationDTO addNewREservation(@PathVariable("id") long id, @RequestBody CreateReservationCommand command){
        return service.addNewReservation(id, command);
    }

    @PutMapping("/{id}")
    public AccommodationDTO updateDate(@PathVariable("id") long id, @RequestBody UpdatePriceCommand command){
        return service.updatePrice(id,command);
    }


    @DeleteMapping
    public void deleteAll(){
        service.deleteAll();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handleNotFound(IllegalArgumentException ex){
        Problem problem =
                Problem.builder()
                        .withType(URI.create("accommodation/not-found"))
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
    public ResponseEntity<Problem> handleWrongNumberOfREservation(IllegalStateException ex){
        Problem problem =
                Problem.builder()
                        .withType(URI.create("accommodation/bad-reservation"))
                        .withTitle("Too Much")
                        .withStatus(Status.BAD_REQUEST)
                        .withDetail(ex.getMessage())
                        .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

}
