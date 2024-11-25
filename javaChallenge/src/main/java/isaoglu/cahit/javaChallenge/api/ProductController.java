package isaoglu.cahit.javaChallenge.api;

import isaoglu.cahit.javaChallenge.entity.Product;
import isaoglu.cahit.javaChallenge.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping()
    public String home(){
        return "Ürün";
    }

    @GetMapping("/all")
    public List<Product> findAll(){
        return productService.findAll();
    }

    @PostMapping("/save")
    public Product save(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        productService.deleteProduct(id);
    }

}
