package com.DataBaseProject.PCenter.service;

import com.DataBaseProject.PCenter.data.Category;
import com.DataBaseProject.PCenter.data.Product;
import com.DataBaseProject.PCenter.dto.ProductDto;
import com.DataBaseProject.PCenter.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public void createProduct(ProductDto productDto, Category category){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategory(category);
        productRepository.save(product);
//        Product product = Product.builder()
//                .name(productDto.getName())
//                .price(productDto.getPrice())
//                .description(productDto.getDescription())
//                .category(category)
//                .build();
//        productRepository.save(product);
    }
    public ProductDto getProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setDescription(product.getDescription());
        productDto.setName(product.getName());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setPrice(product.getPrice());
        productDto.setId(product.getId());
        return productDto;
    }
    public List<ProductDto> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        List<ProductDto> allDtoProducts = new ArrayList<>();
        for(Product product: allProducts){
            allDtoProducts.add(getProductDto(product));
        }
        return allDtoProducts;
    }
    public void updateProduct(ProductDto productDto, Integer productId) throws Exception{
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent()){
            throw new Exception("Product is not present");
        }
        Product product = optionalProduct.get();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        productRepository.save(product);
    }

}
