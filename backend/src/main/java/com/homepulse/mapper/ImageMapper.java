package com.homepulse.mapper;

import com.homepulse.dto.ImageDto;
import com.homepulse.model.Image;

public class ImageMapper {
    public static ImageDto toImageDto(Image image) {
        ImageDto imageDto = new ImageDto();
        imageDto.setId(image.getId());
        imageDto.setImage(image.getImage());
        imageDto.setIsMain(image.getIsMain());
        imageDto.setPosition(image.getPosition());
        imageDto.setCreatedAt(image.getCreatedAt());
        imageDto.setUpdatedAt(image.getUpdatedAt());
        return imageDto;
    }
    public static Image toImage(ImageDto imageDto) {
        Image image = new Image();
        image.setId(imageDto.getId());
        image.setImage(imageDto.getImage());
        image.setIsMain(imageDto.getIsMain());
        image.setPosition(imageDto.getPosition());
        image.setCreatedAt(imageDto.getCreatedAt());
        image.setUpdatedAt(imageDto.getUpdatedAt());
        return image;
    }

}
