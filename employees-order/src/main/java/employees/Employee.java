package employees;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "emp_name")
    private String name;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL,orphanRemoval = true)
    @OrderColumn(name = "address_index")
    private List<Address> addresses = new ArrayList<>();

    public Employee(String name) {
        this.name = name;
    }

    public void addAddresses(List<Address> addresses) {
        addresses.forEach(this::addAddress);
    }

    public void addAddress(Address address) {
        addresses.add(address);
        address.setEmployee(this);
    }

    public void addAddress(int index, Address address) {
        addresses.add(index, address);
        address.setEmployee(this);
    }

    public void removeAddress(int index) {
        addresses.get(index).setEmployee(null);
        addresses.remove(index);
    }

}
