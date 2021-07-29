package lineorders;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderService {

    private List<Order> orders = new ArrayList<>();

    private AtomicLong idGenerator = new AtomicLong();

    private ModelMapper modelMapper;

    public OrderService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public OrderDTO createOrder(CreateOrderCommand command) {
        Order order = new Order(idGenerator.incrementAndGet(),command.getProductNumber());

        orders.add(order);

        return modelMapper.map(order,OrderDTO.class);

    }

    public List<OrderDTO> getOrders(Optional<Integer> month) {
        Type targetListType = new TypeToken<List<OrderDTO>>() {}.getType();

        List<Order> filtered = orders.stream()
                .filter(o->month.isEmpty() || o.getOrderDate().getMonthValue()==month.get())
                .toList();

        return modelMapper.map(filtered,targetListType);
    }

    public OrderDTO getOrder(long id) {

        return modelMapper.map(findById(id),OrderDTO.class);
    }


    private Order findById(long id){
        return orders.stream()
                .filter(o->o.getId()==id)
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("Cannot find"));
    }

    public OrderDTO updateForShipping(long id, ShippingCommand command) {

        Order actual = findById(id);
        System.out.println(actual.getShippingDate());

        if(actual.getShippingDate()==null) {
            actual.setShippingPrice(command.getShippingPrice());
            actual.setShippingDate(LocalDate.now());
        }

        return modelMapper.map(actual,OrderDTO.class);
    }

    public int getFullShippingIncome() {
        return orders.stream()
                .mapToInt(o->o.getShippingPrice())
                .sum();


    }

    public void deleteAll() {

        orders.clear();
        idGenerator=new AtomicLong();

    }
}
