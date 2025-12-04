package com.homepulse.repository;

import com.homepulse.model.Category;

public interface CategoryRepository {
    Category findById(Long id);
    Category findByName(String name);
}
