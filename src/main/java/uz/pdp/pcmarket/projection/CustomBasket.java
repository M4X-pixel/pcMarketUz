package uz.pdp.pcmarket.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.pcmarket.entity.Basket;
import uz.pdp.pcmarket.entity.OutputProduct;
import uz.pdp.pcmarket.entity.User;

import java.util.List;

@Projection(types = Basket.class)
public interface CustomBasket {
    Integer getId();
    List<OutputProduct> getOutputProducts();
    double getAllSum();
    User getUser();
}
