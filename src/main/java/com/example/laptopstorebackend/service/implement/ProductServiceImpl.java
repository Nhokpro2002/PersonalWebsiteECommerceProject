package com.example.laptopstorebackend.service.implement;

import com.example.laptopstorebackend.dto.ProductDTO;
import com.example.laptopstorebackend.entity.Product;
import com.example.laptopstorebackend.exception.ResourceNotFoundException;
import com.example.laptopstorebackend.mapper.ProductConverter;
import com.example.laptopstorebackend.repository.ProductRepository;
import com.example.laptopstorebackend.service.interfaces.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductDTO> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductConverter::convertFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO findById(Long id) {
        Optional<Product> optionalExistingProduct = productRepository.findById(id);

        if (optionalExistingProduct.isPresent()) {
            Product product = optionalExistingProduct.get();
            return ProductConverter.convertFromEntity(product);
        }
        throw new ResourceNotFoundException("Product not found with productId: " + id);
    }

    @Override
    public ProductDTO save(Product product) {
        productRepository.save(product);
        return ProductConverter.convertFromEntity(product);
    }

    @Override
    public ProductDTO deleteById(Long id) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found by productId: " + id));
        existingProduct.setStock(0);
        return ProductConverter.convertFromEntity(existingProduct);
    }

    @Override
    public ProductDTO update(BigDecimal sellingPrice, Long id) {
        Optional<Product> optionalExistingProduct = productRepository.findById(id);

        if (optionalExistingProduct.isEmpty()) {
            throw new ResourceNotFoundException("Product not found with productId " + id);
        }

        Product existingProduct = optionalExistingProduct.get();
        existingProduct.setSellingPrice(sellingPrice);
        productRepository.save(existingProduct);
        return ProductConverter.convertFromEntity(existingProduct);
    }

}
