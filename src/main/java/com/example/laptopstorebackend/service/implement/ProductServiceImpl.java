package com.example.laptopstorebackend.service.implement;

import com.example.laptopstorebackend.dto.ProductDTO;
import com.example.laptopstorebackend.entity.Product;
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
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return ProductConverter.convertFromEntity(product);
    }

    @Override
    public ProductDTO save(Product product) {
        productRepository.save(product);
        return ProductConverter.convertFromEntity(product);
    }

    @Override
    public void deleteById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public ProductDTO update(BigDecimal sellingPrice, Long id) {
        Optional<Product> optionalExistingProduct = productRepository.findById(id);

        if (optionalExistingProduct.isEmpty()) {
            throw new RuntimeException("Product not found");
        }

        Product existingProduct = optionalExistingProduct.get();
        existingProduct.setSellingPrice(sellingPrice);
        productRepository.save(existingProduct);
        return ProductConverter.convertFromEntity(existingProduct);
    }

}
