package com.example.laptopstorebackend.controller;

import com.example.laptopstorebackend.dto.ProductDTO;
import com.example.laptopstorebackend.dto.response.ApiResponse;
import com.example.laptopstorebackend.entity.Product;
import com.example.laptopstorebackend.service.implement.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductServiceImpl productServiceImpl;

    @GetMapping()
    public ApiResponse<List<ProductDTO>> findAll() {
        List<ProductDTO> productDTOList = productServiceImpl.findAll();
        return ApiResponse.<List<ProductDTO>>builder()
                .code(200)
                .message("List products")
                .data(productDTOList).build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO productDTO = productServiceImpl.findById(id);
        return ApiResponse.<ProductDTO>builder()
                .code(200)
                .message("Product")
                .data(productDTO).build();

    }

    @PostMapping()
    public ApiResponse<ProductDTO> save(@RequestBody Product product) {
        ProductDTO productDTO = productServiceImpl.save(product);
        return ApiResponse.<ProductDTO>builder()
                .code(200)
                .message("Product be save")
                .data(productDTO).build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {
        productServiceImpl.deleteById(id);
        return ApiResponse.<String>builder()
                .code(200)
                .message("Product be deleted")
                .build();
    }

    @PatchMapping("/{id}")
    public ApiResponse<ProductDTO> update(@RequestBody BigDecimal sellingPrice, @PathVariable Long id) {
        ProductDTO productDTO = productServiceImpl.update(sellingPrice, id);
        return ApiResponse.<ProductDTO>builder()
                .code(200)
                .message("Update product successfully")
                .data(productDTO)
                .build();
    }

}
