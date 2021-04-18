package uz.pdp.pcmarket.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.pcmarket.entity.address.Address;
import uz.pdp.pcmarket.entity.address.District;

@Projection(types = Address.class)
public interface CustomAddress {
    Integer getId();
    Integer getHomeNumber();
    District getDistrict();
}
