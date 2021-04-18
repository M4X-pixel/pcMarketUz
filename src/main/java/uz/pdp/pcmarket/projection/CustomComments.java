package uz.pdp.pcmarket.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.pcmarket.entity.Comments;
import uz.pdp.pcmarket.entity.Product;
import uz.pdp.pcmarket.entity.User;

@Projection(types = Comments.class)
public interface CustomComments {
    Integer getId();
    Integer getNumberStars();
    String getBody();
    User getUser();
    Product getProduct();
}
