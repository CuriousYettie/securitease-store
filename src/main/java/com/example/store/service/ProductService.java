package com.example.store.service;

import com.example.store.dto.ProductDTO;
import com.example.store.entity.Product;
import com.example.store.mapper.ProductMapper;
import com.example.store.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public List<ProductDTO> getAllProducts() {
        return productMapper.productsToProductDTOs(productRepository.findAll());
    }

    public ProductDTO getProductById(Long id) {

        return productMapper.productToProductDTO(productRepository.findById(id).orElse(null));
    }

    public ProductDTO createProduct(Product product) {
        return productMapper.productToProductDTO(productRepository.save(product));
    }
}
