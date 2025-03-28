package com.example.laptopstorebackend.service.implement;

import com.example.laptopstorebackend.entity.Product;
import com.example.laptopstorebackend.repository.ProductRepository;
import com.example.laptopstorebackend.service.interfaces.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Product updateProduct(Product product) {
        Optional<Product> existingProduct = productRepository.findById(product.getProductId());

        if (existingProduct.isPresent()) {
            Product updatedProduct = Product

            return productRepository.save(updatedProduct);
        } else {
            throw new RuntimeException("Product not found with ID: " + product.getId());
        }
    }
}
