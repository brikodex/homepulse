package com.homepulse.service;

import com.homepulse.dto.ProductDto;
import com.homepulse.dto.ProductDetailDto;
import com.homepulse.mapper.ProductMapper;
import com.homepulse.model.Product;
import com.homepulse.repository.CategoryRepository;
import com.homepulse.repository.ProductRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ProductServiceImpl implements ProductService {

    @Inject
    private ProductRepository productRepository;

    @Inject
    private CategoryRepository categoryRepository;

    @Inject
    private ProductMapper productMapper;

    @Override
    public ProductDto getProduct(Long id) {
        Product product = productRepository.findById(id);
        return productMapper.toProductDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(productMapper::toProductDto).collect(Collectors.toList());
    }

    @Override
    public ProductDetailDto getDetail(Long id) {
        Product product = productRepository.findById(id);
        return productMapper.toProductDetailDto(product);
    }

    @Override
    public void addProduct(ProductDto productDto) {
        productRepository.save(productMapper.toProduct(productDto));

    }


    @Override
    public boolean deleteProduct(Long id) {
        productRepository.deleteById(id);
        return true;
    }
}
