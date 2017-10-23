package giovannidiluca.pedidos.Order;

/**
 * Created by Matheus on 10/10/2017.
 */

public class OrderSimple {
    int id;
    Customer customer;
    Store store;
    Status status;
    String created_at;
    String deliver_at;

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Store getStore() {
        return store;
    }

    public Status getStatus() {
        return status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getDeliver_at() {
        return deliver_at;
    }


    @Override
    public String toString() {
        return "\n#" + id + " - " + customer.name + "\n" +
                store.name + "\nEntrega: " + deliver_at + "\n";
    }
}
