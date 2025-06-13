package com.enotes_service.enoteserviceapis.Service;

import com.enotes_service.enoteserviceapis.DTOS.CategoryDTO;
import com.enotes_service.enoteserviceapis.DTOS.CategoryResponse;

import java.util.List;

public interface CategoryService {

    public Boolean saveCategory(CategoryDTO categoryDTO);
    public List<CategoryDTO> getAllCategory();


    List<CategoryResponse> getisActiveTrue();
    public CategoryDTO getCategoryById(Integer id) throws Exception;
    public Boolean deleteCategory(Integer id);
}
