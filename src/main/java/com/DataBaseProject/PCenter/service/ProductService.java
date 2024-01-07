package com.DataBaseProject.PCenter.service;

import com.DataBaseProject.PCenter.data.Category;
import com.DataBaseProject.PCenter.data.Product;
import com.DataBaseProject.PCenter.dto.ProductDto;
import com.DataBaseProject.PCenter.exception.ProductNotExistsException;
import com.DataBaseProject.PCenter.exception.ResourceNotFoundException;
import com.DataBaseProject.PCenter.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    public Product createProduct(ProductDto productDto, Category category){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategory(category);
        product.setCurrentQuantity(productDto.getCurrentQuantity());
        product.setSubcategory(productDto.getSubcategory());
        return productRepository.save(product);
    }
    public ProductDto getProductDto(Product product) {
        return productMapper.mapToDTO(product);
    }
    public Product getProductById(Integer id){
        return productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found"));
    }
    public List<ProductDto> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        List<ProductDto> allDtoProducts = new ArrayList<>();
        for(Product product: allProducts){
            allDtoProducts.add(getProductDto(product));
        }
        return allDtoProducts;
    }

    public List<Product> searchProducts(String query) {
        List<Product> products = productRepository.searchProducts(query);
        return products;
    }


    public void updateProduct(ProductDto productDto, Integer productId) throws Exception{
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new Exception("Product is not present"));
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCurrentQuantity(productDto.getCurrentQuantity());

        productRepository.save(product);
    }
    public Product getById(Integer productId) throws ProductNotExistsException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotExistsException("product id is invalid: " + productId);
        }
        return optionalProduct.get();
    }
}
