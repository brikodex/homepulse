package com.homepulse.mapper;

import com.homepulse.dto.ProductDetailDto;
import com.homepulse.dto.ProductDto;
import com.homepulse.model.Category;
import com.homepulse.model.Product;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-08T09:04:52+0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@ApplicationScoped
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDto toProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setMainImageUrl( ProductMapper.mainImageUrl( product.getImages() ) );
        productDto.setCategoryName( productCategoryName( product ) );
        productDto.setId( product.getId() );
        productDto.setName( product.getName() );
        productDto.setDescription( product.getDescription() );
        productDto.setPrice( product.getPrice() );
        if ( product.getStockQuantity() != null ) {
            productDto.setStockQuantity( product.getStockQuantity() );
        }

        return productDto;
    }

    @Override
    public ProductDetailDto toProductDetailDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDetailDto productDetailDto = new ProductDetailDto();

        productDetailDto.setImageUrls( ProductMapper.imageUrls( product.getImages() ) );
        productDetailDto.setCategoryName( productCategoryName( product ) );
        productDetailDto.setId( product.getId() );
        productDetailDto.setName( product.getName() );
        productDetailDto.setDescription( product.getDescription() );
        productDetailDto.setPrice( product.getPrice() );
        if ( product.getStockQuantity() != null ) {
            productDetailDto.setStockQuantity( product.getStockQuantity() );
        }

        return productDetailDto;
    }

    @Override
    public Product toProduct(ProductDto dto) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( dto.getId() );
        product.setName( dto.getName() );
        product.setDescription( dto.getDescription() );
        product.setPrice( dto.getPrice() );
        product.setStockQuantity( dto.getStockQuantity() );

        return product;
    }

    @Override
    public List<ProductDto> toProductDtoList(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductDto> list = new ArrayList<ProductDto>( products.size() );
        for ( Product product : products ) {
            list.add( toProductDto( product ) );
        }

        return list;
    }

    @Override
    public List<ProductDetailDto> toProductDetailDtoList(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductDetailDto> list = new ArrayList<ProductDetailDto>( products.size() );
        for ( Product product : products ) {
            list.add( toProductDetailDto( product ) );
        }

        return list;
    }

    private String productCategoryName(Product product) {
        if ( product == null ) {
            return null;
        }
        Category category = product.getCategory();
        if ( category == null ) {
            return null;
        }
        String name = category.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
