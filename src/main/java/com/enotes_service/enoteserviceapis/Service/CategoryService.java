package com.enotes_service.enoteserviceapis.Service;

import com.enotes_service.enoteserviceapis.DTOS.CategoryDTO;
import com.enotes_service.enoteserviceapis.DTOS.CategoryResponse;

import java.util.List;

public interface CategoryService {

    public boolean saveCategory(CategoryDTO categoryDTO);
    public List<CategoryDTO> getAllCategory();


    List<CategoryResponse> getisActiveTrue();
    public CategoryDTO getCategoryById(Integer id);
    public void deleteCategory(Integer id);
}
