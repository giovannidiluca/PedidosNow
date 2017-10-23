package giovannidiluca.pedidos.Order;

import java.io.Serializable;

/**
 * Created by Matheus on 10/10/2017.
 */

public class OrderDetail implements Serializable{
    int id;
    int order_id;
    Product product;
    String quantity;
    String price;

    public int getId() {
        return id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public Product getProduct() {
        return product;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price;
    }
}
