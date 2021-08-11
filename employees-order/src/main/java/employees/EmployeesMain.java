package employees;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesMain {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        var em = factory.createEntityManager();
        em.getTransaction().begin();

        Employee employee = new Employee("John Doe");
        employee.addAddresses(List.of(new Address("Bp1"), new Address("Bp2"), new Address("Bp3"), new Address("Bp4")));
        em.persist(employee);

        em.getTransaction().commit();

        long id = employee.getId();

        em.getTransaction().begin();
        employee = em.find(Employee.class, id);
        employee.addAddress(1, new Address("xxx"));

        employee.removeAddress(3);
        em.getTransaction().commit();

        List<Employee> employees = em.createQuery("select distinct e from Employee e join fetch e.addresses", Employee.class)
                .getResultList();
        System.out.println(employees);

        em.close();
        factory.close();
    }
}
