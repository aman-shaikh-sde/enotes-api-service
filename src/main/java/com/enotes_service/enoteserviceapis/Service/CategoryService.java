package com.enotes_service.enoteserviceapis.Service;

import com.enotes_service.enoteserviceapis.Entity.Category;

import java.util.List;

public interface CategoryService {

    public boolean saveCategory(Category category);
    public List<Category> getAllCategory();


}
