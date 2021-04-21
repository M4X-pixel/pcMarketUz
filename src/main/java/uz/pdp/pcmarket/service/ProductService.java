package uz.pdp.pcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import uz.pdp.pcmarket.entity.Product;
import uz.pdp.pcmarket.model.ProductDto;
import uz.pdp.pcmarket.repository.AttachmentRepository;
import uz.pdp.pcmarket.repository.CategoryRepository;
import uz.pdp.pcmarket.repository.ProductRepository;
import uz.pdp.pcmarket.result.Result;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public Product getProduct(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElseGet(Product::new);
    }

    public Result addProduct(ProductDto dto){
        Product product = new Product(null,dto.getName(),dto.getModel(),dto.getDescription(),
                attachmentRepository.getOne(dto.getAttachmentId()),dto.getPrice(), categoryRepository.getOne(dto.getCategoryId()),dto.isActive());
          productRepository.save(product);
          return new Result("Product added", true);
    }

    public Result editProduct(ProductDto dto, Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) return new Result("Product not found",false);
        Product product = optionalProduct.get();
        product.setActive(dto.isActive());
        product.setAttachment(attachmentRepository.getOne(dto.getAttachmentId()));
        product.setCategory(categoryRepository.getOne(dto.getCategoryId()));
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setModel(dto.getModel());
        product.setName(dto.getName());
        productRepository.save(product);
        return new Result("Product edited",false);
    }

    public Result deleteProduct(Integer id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) return new Result("Product not found",false);
        productRepository.delete(optionalProduct.get());
        return new Result("Product deleted", true);
    }

}
