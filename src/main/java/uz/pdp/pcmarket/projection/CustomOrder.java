package uz.pdp.pcmarket.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.pcmarket.entity.Basket;
import uz.pdp.pcmarket.entity.Order;

import java.util.Date;

@Projection(types = Order.class)
public interface CustomOrder {
    Integer getId();
    Date getDate();
    Basket getBasket();
    String getDetails();
}
