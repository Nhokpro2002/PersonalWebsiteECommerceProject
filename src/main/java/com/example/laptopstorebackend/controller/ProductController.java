package com.example.laptopstorebackend.controller;

import com.example.laptopstorebackend.constant.Category;
import com.example.laptopstorebackend.dto.ProductDTO;
import com.example.laptopstorebackend.dto.response.ApiResponse;
import com.example.laptopstorebackend.entity.Product;
import com.example.laptopstorebackend.service.implement.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductServiceImpl productServiceImpl;

    @GetMapping("/size")
    public ApiResponse<Integer> countNumberItems() {
        return ApiResponse.<Integer>builder()
                .code(200)
                .message("Size inventory")
                .data(productServiceImpl.countNumberItems())
                .build();
    }

    @GetMapping("/category/size")
    public ApiResponse<Integer> countNumberItemsByCategory(@RequestParam Category category) {
        return ApiResponse.<Integer>builder()
                .code(200)
                .message("Size inventory")
                .data(productServiceImpl.countNumberItemsByCategory(category))
                .build();
    }

    /**
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping()
    public ApiResponse<Page<ProductDTO>> findAll( @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "9") int size) {
        Page<ProductDTO> productDTOList = productServiceImpl.findAll(page, size);
        return ApiResponse.<Page<ProductDTO>>builder()
                .code(200)
                .message("List products")
                .data(productDTOList).build();
    }

    @GetMapping("/category")
    public ApiResponse<Page<ProductDTO>> findAllByCategory(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "9") int size,
                                                        @RequestParam Category productCategory) {
        Page<ProductDTO> productDTOList = productServiceImpl.findByCategory(page, size, productCategory);
        return ApiResponse.<Page<ProductDTO>>builder()
                .code(200)
                .message("List products have same Category")
                .data(productDTOList).build();
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/productId")
    public ApiResponse<ProductDTO> findById(@RequestParam Long id) {
        ProductDTO productDTO = productServiceImpl.findById(id);
        return ApiResponse.<ProductDTO>builder()
                .code(200)
                .message("Product")
                .data(productDTO).build();

    }

    /**
     *
     * @param product
     * @return
     */
    @PostMapping()
    public ApiResponse<ProductDTO> save(@RequestBody Product product) {
        ProductDTO productDTO = productServiceImpl.save(product);
        return ApiResponse.<ProductDTO>builder()
                .code(200)
                .message("Product be save")
                .data(productDTO).build();
    }

    /**
     *
     * @param productId
     * @return
     */
    @DeleteMapping()
    public ApiResponse<String> delete(@RequestParam Long productId) {
        productServiceImpl.deleteById(productId);
        return ApiResponse.<String>builder()
                .code(200)
                .message("Product be deleted")
                .build();
    }

    /**
     *
     * @param sellingPrice
     * @param productId
     * @return
     */
    @PatchMapping()
    public ApiResponse<ProductDTO> update(@RequestParam BigInteger sellingPrice, @RequestParam Long productId) {
        ProductDTO productDTO = productServiceImpl.updateSellingPrice(sellingPrice, productId);
        return ApiResponse.<ProductDTO>builder()
                .code(200)
                .message("Update product successfully")
                .data(productDTO)
                .build();
    }

}
