package com.example.laptopstorebackend.service.implement;

import com.example.laptopstorebackend.constant.Category;
import com.example.laptopstorebackend.dto.ProductDTO;
import com.example.laptopstorebackend.entity.Product;
import com.example.laptopstorebackend.exception.ResourceNotFoundException;
import com.example.laptopstorebackend.repository.ProductRepository;
import com.example.laptopstorebackend.service.interfaces.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;

    /**
     *
     * @return
     */
    @Override
    public Page<ProductDTO> findAll(int page, int size ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(this::convertFromEntity);
    }

    /**
     *
     * @return
     */
    @Override
    public Integer countNumberItems() {
        List<Product> products = productRepository.findAll();
        return products.size();
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
     * @param productId
     * @return
     */
    @Override
    public ProductDTO deleteById(Long productId) {
        productRepository.deleteById(productId);
        return null;
    }

    /**
     *
     * @param sellingPrice
     * @param id
     * @return
     */
    @Override
    public ProductDTO updateSellingPrice(BigInteger sellingPrice, Long id) {
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

    @Override
    public Page<ProductDTO> findByCategory(int page, int size, Category category) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAllByCategory(category, pageable);
        return products.map(this::convertFromEntity);
    }

    /**
     *
     * @param product
     * @return
     */
    public ProductDTO convertFromEntity(Product product) {
        return ProductDTO.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .sellingPrice(product.getSellingPrice())
                .imageUrl(product.getImageURL())
                .category(product.getCategory())
                .stock(product.getStock())
                .build();
    }

}
