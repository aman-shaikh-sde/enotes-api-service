package com.enotes_service.enoteserviceapis.Service.ServiceImpl;

import com.enotes_service.enoteserviceapis.DTOS.CategoryDTO;
import com.enotes_service.enoteserviceapis.DTOS.CategoryResponse;
import com.enotes_service.enoteserviceapis.Entity.Category;
import com.enotes_service.enoteserviceapis.Repository.CategoryRepo;
import com.enotes_service.enoteserviceapis.Service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CategoryRepo categoryRepo;



    @Override
    public boolean saveCategory(CategoryDTO categoryDTO) {


      Category category=  mapper.map(categoryDTO,Category.class);
        category.setDeleted(false);
        category.setCreatedBy(1);
        category.setCreatedDate(new Date());
        category.setActive(categoryDTO.isActive());
        Category saveCategory =categoryRepo.save(category);

        if(ObjectUtils.isEmpty(saveCategory)){
            return false;
        }
        return true;
    }


    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Category> allCategoty=categoryRepo.findAll();
        List<CategoryDTO> categories=allCategoty.stream().map(cat->mapper.map(cat,CategoryDTO.class)).toList();

        return categories;
    }

    @Override
    public List<CategoryResponse> getisActiveTrue() {
        List<Category> activeCategory = categoryRepo.findByisActiveTrue();
        List<CategoryResponse> categoryResponses = activeCategory.stream().map(cat -> mapper.map(cat, CategoryResponse.class)).toList();
    return categoryResponses;
    }
}
    