package lineorders;


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
@RequestMapping("/api/orders")
public class OrderController {


    private OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public List<OrderDTO> getOrders(@RequestParam Optional<Integer> month){
        return service.getOrders(month);
    }

    @GetMapping("/{id}")
    public OrderDTO getOrder(@PathVariable("id") long id){
        return service.getOrder(id);
    }

    @GetMapping("/shippingIncome")
    public int getFullShippingIncome(){
        return service.getFullShippingIncome();
    }

    @PostMapping
    public OrderDTO createOrder(@Valid @RequestBody CreateOrderCommand command){
         return service.createOrder(command);
    }

    @PutMapping("/{id}/shipping")
    public OrderDTO readyForShipping(@PathVariable("id") long id, @Valid @RequestBody ShippingCommand command){
        return service.updateForShipping(id, command);
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
                        .withType(URI.create("order/not-found"))
                        .withTitle("Not Found")
                        .withStatus(Status.NOT_FOUND)
                        .withDetail(ex.getMessage())
                        .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }
}
