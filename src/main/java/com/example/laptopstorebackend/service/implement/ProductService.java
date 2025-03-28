package com.example.laptopstorebackend.service.implement;

import com.example.laptopstorebackend.dto.ProductDTO;
import com.example.laptopstorebackend.entity.Product;
import com.example.laptopstorebackend.exception.AppException;
import com.example.laptopstorebackend.mapper.ProductConverter;
import com.example.laptopstorebackend.repository.ProductRepository;
import com.example.laptopstorebackend.service.interfaces.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;


    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductConverter::covertFromEntity)
                .toList();
    }

    @Override
    public ProductDTO getProductById(Long productId) {
        Product product = productRepository.findByProductId(productId)
                .orElseThrow(() -> new AppException(404, "Product not found"));
        return ProductConverter.covertFromEntity(product);
    }

    @Override
    public ProductDTO saveProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long productId) {

    }

    @Override
    public ProductDTO updateProduct(Product product) {
        return null;
    }
}
