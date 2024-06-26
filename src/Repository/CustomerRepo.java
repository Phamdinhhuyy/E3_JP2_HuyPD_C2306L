package Repository;

import Entity.Customer;
import Entity.Room;
import Entity.RoomType;
import IGeneric.IGeneric;

import java.util.List;
import java.util.Optional;

public class CustomerRepo implements IGeneric <Customer> {
    public static List<Customer> customers;
    public CustomerRepo() {;}
    @Override
    public Customer add() {

        return null;
    }

    @Override
    public Customer delete(Customer customer) {

        return null;
    }

    @Override
    public Optional<Customer> findById(String id) {
        return customers.stream()
                .filter(c -> c.getId().equals(id)).findFirst();
    }



}
