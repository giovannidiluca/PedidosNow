package giovannidiluca.pedidos.Order;

import java.io.Serializable;

/**
 * Created by Matheus on 10/10/2017.
 */

public class Product implements Serializable {
    int id;
    String name;
    String ean;
    String image;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEan() {
        return ean;
    }

    public String getImage() {
        return image;
    }
}
