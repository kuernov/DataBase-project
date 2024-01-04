package com.DataBaseProject.PCenter.controller;

import com.DataBaseProject.PCenter.common.ApiResponse;
import com.DataBaseProject.PCenter.data.Category;
import com.DataBaseProject.PCenter.data.Product;
import com.DataBaseProject.PCenter.dto.ProductDto;
import com.DataBaseProject.PCenter.repository.CategoryRepository;
import com.DataBaseProject.PCenter.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController  {

    private final ProductService productService;
    private final CategoryRepository categoryRepository;

    @PostMapping
    public ResponseEntity<ApiResponse> createProduct(@RequestBody @Validated ProductDto productDto){
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategory().getId());
        if(!optionalCategory.isPresent())
            return new ResponseEntity<>(new ApiResponse(false, "category does not exists"), HttpStatus.BAD_REQUEST);
        productService.createProduct(productDto, optionalCategory.get());
        return new ResponseEntity<>(new ApiResponse(true, "product has been added"),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam("query") String query){
        return ResponseEntity.ok(productService.searchProducts(query));
    }


    @PutMapping("/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Integer productId, @RequestBody ProductDto productDto) throws Exception{
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategory().getId());
        if (optionalCategory.isPresent())
            return new ResponseEntity<>(new ApiResponse(false, "category does not exists"),HttpStatus.BAD_REQUEST);
        productService.updateProduct(productDto,productId);
        return new ResponseEntity<>(new ApiResponse(true, "product has been updated"),HttpStatus.OK);
    }
}
