package uz.pdp.pcmarket.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.pcmarket.entity.Characteristics;

@Projection(types = Characteristics.class)
public interface CustomCharacteristics {
    Integer getId();
    String getName();
    String getType();
}
