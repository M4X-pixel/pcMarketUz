package uz.pdp.pcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pcmarket.model.ProductDto;
import uz.pdp.pcmarket.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;
@PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR','OPERATOR')")
@GetMapping
    public HttpEntity<?> getProducts(){
    return ResponseEntity.ok(productService.getProducts());
}
@PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR','OPERATOR')")
@GetMapping("/{id}")
    public HttpEntity<?> getProduct(@PathVariable Integer id){
    return ResponseEntity.ok(productService.getProduct(id));
}
@PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
@PostMapping
    public HttpEntity<?> addProduct(@RequestBody ProductDto productDto){
    return ResponseEntity.ok(productService.addProduct(productDto));
}
@PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
@PutMapping("/{id}")
    public HttpEntity<?> editProduct(@RequestBody ProductDto dto, @PathVariable Integer id){
    if (productService.editProduct(dto,id).isStatus()){
        return ResponseEntity.ok(productService.editProduct(dto,id));
    }
    return ResponseEntity.notFound().build();
}
@PreAuthorize(value = "hasRole('SUPER_ADMIN')")
@DeleteMapping("/{id}")
    public HttpEntity<?> deleteProduct(@PathVariable Integer id){
    if (productService.deleteProduct(id).isStatus()){
        return ResponseEntity.ok(productService.deleteProduct(id));
    }
    return ResponseEntity.notFound().build();
}
}
