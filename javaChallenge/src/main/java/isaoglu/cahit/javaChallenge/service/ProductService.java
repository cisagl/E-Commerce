package isaoglu.cahit.javaChallenge.service;

import isaoglu.cahit.javaChallenge.entity.Product;
import isaoglu.cahit.javaChallenge.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product createProduct(Product product){
        System.out.println("Ürün kaydedildi");
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct){
        Product product = productRepository.findById(id).orElseThrow();
        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        product.setStock(updatedProduct.getStock());
        System.out.println("Ürün güncellendi");
        return productRepository.save(product);
    }

    public void deleteProduct (Long id){
        productRepository.delete(productRepository.getReferenceById(id));
    }
}
