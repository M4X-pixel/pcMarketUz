package uz.pdp.pcmarket.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.pcmarket.entity.User;
import uz.pdp.pcmarket.entity.address.Address;

@Projection(types = User.class)
public interface CustomUser {
    Integer getId();
    String getFullName();
    String getEmail();
    String getPassword();
    Address getAddress();

}
