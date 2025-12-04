package com.homepulse.mapper;

import com.homepulse.dto.CategoryDto;
import com.homepulse.dto.SubCategoryDto;
import com.homepulse.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapper {
    public static CategoryDto toCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
       if (category.getParent() != null) {
           CategoryDto parentCategoryDto = new CategoryDto();
           parentCategoryDto.setId(category.getParent().getId());
           parentCategoryDto.setName(category.getParent().getName());
           categoryDto.setParent(parentCategoryDto.getParent());
       }
       if (category.getChildren() != null) {
           List<CategoryDto> categoryDtoList = new ArrayList<>();
           for (Category categoryChild : category.getChildren()) {
               CategoryDto categoryChildDto = new CategoryDto();
               categoryChildDto.setId(categoryChild.getId());
               categoryChildDto.setName(categoryChild.getName());
               categoryDtoList.add(categoryChildDto);

           }
       }
        return categoryDto;
    }
        public static Category toCategory(CategoryDto categoryDto) {
            Category category = new Category();
            category.setId(categoryDto.getId());
            category.setName(categoryDto.getName());
            if (categoryDto.getParent() != null) {
                Category parentCategory = new Category();
                category.setId(categoryDto.getParent().getId());
                category.setName(category.getParent().getName());
                category.setParent(parentCategory.getParent());
            }
            if (categoryDto.getChildren() != null) {
                List<Category> categoryList = new ArrayList<>();
                for (SubCategoryDto categoryChildDto : categoryDto.getChildren()) {
                    Category categoryChild = new Category();
                    categoryChild.setId(categoryChildDto.getId());
                    categoryChild.setName(categoryChildDto.getName());
                    categoryList.add(categoryChild);

                }
            }
            return category;
        }
}
