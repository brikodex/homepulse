package com.homepulse.service;

import com.homepulse.dto.ProductDetailDto;
import com.homepulse.dto.ProductDto;

import java.util.List;

public interface ProductService {
    void addProduct(ProductDto product);

    List<ProductDto> getAllProducts();

    ProductDto getProduct(Long id);
    ProductDetailDto getDetail(Long id);
    boolean deleteProduct(Long id);

}
