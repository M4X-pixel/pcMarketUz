package uz.pdp.pcmarket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String name;
    private String model;
    private String description;

    private Integer attachmentId;
    private double price;
    private boolean active;
    private Integer categoryId;
}
