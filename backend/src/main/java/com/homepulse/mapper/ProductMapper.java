package com.homepulse.mapper;

import com.homepulse.dto.ProductDetailDto;
import com.homepulse.dto.ProductDto;
import com.homepulse.model.Image;
import com.homepulse.model.Product;
import org.mapstruct.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "cdi", uses = {CategoryMapper.class})
public interface ProductMapper {

    @Mapping(target = "mainImageUrl", source = "images", qualifiedByName = "mainImageUrl")
    @Mapping(target = "categoryName", source = "category.name")
    ProductDto toProductDto(Product product);

    @Mapping(target = "imageUrls", source = "images", qualifiedByName = "imageUrls")
    @Mapping(target = "categoryName", source = "category.name")
    ProductDetailDto toProductDetailDto(Product product);

    @InheritInverseConfiguration(name = "toProductDto")
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Product toProduct(ProductDto dto);

    List<ProductDto> toProductDtoList(List<Product> products);

    List<ProductDetailDto> toProductDetailDtoList(List<Product> products);

    // Helpers
    @Named("mainImageUrl")
    static String mainImageUrl(List<Image> images) {
        if (images == null || images.isEmpty()) return null;
        return images.stream()
                .filter(i -> Boolean.TRUE.equals(i.getIsMain()))
                .findFirst()
                .map(Image::getImage)
                .orElse(images.getFirst().getImage());
    }

    @Named("imageUrls")
    static List<String> imageUrls(List<Image> images) {
        if (images == null) return Collections.emptyList();
        return images.stream().map(Image::getImage).collect(Collectors.toList());
    }
}
