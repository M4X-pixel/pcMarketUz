package uz.pdp.pcmarket.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.pcmarket.entity.OutputProduct;
import uz.pdp.pcmarket.entity.Product;

@Projection(types = OutputProduct.class)
public interface CustomOutputProduct {
    Integer getId();
    Integer getAmount();
    Product getProduct();
}
