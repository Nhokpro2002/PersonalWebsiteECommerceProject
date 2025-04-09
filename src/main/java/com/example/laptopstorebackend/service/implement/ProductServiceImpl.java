package com.example.laptopstorebackend.service.implement;

import com.example.laptopstorebackend.dto.ProductDTO;
import com.example.laptopstorebackend.entity.Product;
import com.example.laptopstorebackend.exception.ResourceNotFoundException;
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

    /**
     *
     * @return
     */
    @Override
    public List<ProductDTO> findAll() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::convertFromEntity)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public ProductDTO findById(Long id) {
        Optional<Product> optionalExistingProduct = productRepository.findById(id);

        if (optionalExistingProduct.isPresent()) {
            Product product = optionalExistingProduct.get();
            return convertFromEntity(product);
        }
        throw new ResourceNotFoundException("Product not found with productId: " + id);
    }

    /**
     *
     * @param product
     * @return
     */
    @Override
    public ProductDTO save(Product product) {
        productRepository.save(product);
        return convertFromEntity(product);
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public ProductDTO deleteById(Long id) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found by productId: " + id));
        existingProduct.setStock(0);
        return convertFromEntity(existingProduct);
    }

    /**
     *
     * @param sellingPrice
     * @param id
     * @return
     */
    @Override
    public ProductDTO updateSellingPrice(BigDecimal sellingPrice, Long id) {
        Optional<Product> optionalExistingProduct = productRepository.findById(id);

        if (optionalExistingProduct.isEmpty()) {
            throw new ResourceNotFoundException("Product not found with productId " + id);
        }

        Product existingProduct = optionalExistingProduct.get();
        existingProduct.setSellingPrice(sellingPrice);
        productRepository.save(existingProduct);
        return convertFromEntity(existingProduct);
    }

    @Override
    public void updateStock(Integer newStock, String productName) {
        Product existingProduct = (Product) productRepository.findByProductName(productName)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        existingProduct.setStock(newStock);
        productRepository.save(existingProduct);
    }

    /**
     *
     * @param product
     * @return
     */
    public ProductDTO convertFromEntity(Product product) {
        return ProductDTO.builder()
                .productName(product.getProductName())
                .description(product.getDescription())
                .sellingPrice(product.getSellingPrice())
                .imageUrl(product.getImageURL())
                .category(product.getCategory())
                .stock(product.getStock())
                .build();
    }

}
