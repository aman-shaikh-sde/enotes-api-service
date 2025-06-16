package com.enotes_service.enoteserviceapis.Service.ServiceImpl;

import com.enotes_service.enoteserviceapis.DTOS.CategoryDTO;
import com.enotes_service.enoteserviceapis.DTOS.CategoryResponse;
import com.enotes_service.enoteserviceapis.Entity.Category;
import com.enotes_service.enoteserviceapis.Exception.ExistDataException;
import com.enotes_service.enoteserviceapis.Exception.ResourceNotFoundException;
import com.enotes_service.enoteserviceapis.Repository.CategoryRepo;
import com.enotes_service.enoteserviceapis.Service.CategoryService;
import com.enotes_service.enoteserviceapis.Util.Validation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.management.relation.RelationNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CategoryRepo categoryRepo;


    @Autowired
    private Validation validation;

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {

        // Validation Checking
        validation.categoryValidation(categoryDTO);

        // check category exist or not

        Boolean exist = categoryRepo.existsByName(categoryDTO.getName().trim());
        if (exist) {
            // throw error
            throw new ExistDataException("Category already exist");
        }

        Category category = mapper.map(categoryDTO, Category.class);

        if (ObjectUtils.isEmpty(category.getId())) {
            category.setIsDeleted(false);
//			category.setCreatedBy(1);
            category.setUpdatedDate(null);
            category.setUpdatedBy(null);
            category.setCreatedDate(new Date());
        } else {
            updateCategory(category);
        }

        Category saveCategory = categoryRepo.save(category);
        return mapper.map(saveCategory, CategoryDTO.class);

    }

    private void updateCategory(Category category) {
        Optional<Category> findById = categoryRepo.findById(category.getId());
        if (findById.isPresent()) {
            Category existCategory = findById.get();
            category.setCreatedBy(existCategory.getCreatedBy());
            category.setCreatedDate(existCategory.getCreatedDate());
            category.setIsDeleted(existCategory.getIsDeleted());

//			category.setUpdatedBy(1);
//			category.setUpdatedOn(new Date());
        }
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Category> allCategoty = categoryRepo.findAll();
        List<CategoryDTO> categories = allCategoty.stream().map(cat -> mapper.map(cat, CategoryDTO.class)).toList();

        return categories;
    }

    @Override
    public List<CategoryResponse> getisActiveTrue() {
        List<Category> activeCategory = categoryRepo.findByIsActiveTrueAndIsDeletedFalse();
        List<CategoryResponse> categoryResponses = activeCategory.stream().map(cat -> mapper.map(cat, CategoryResponse.class)).toList();
        return categoryResponses;
    }

    public CategoryDTO getCategoryById(Integer id) throws Exception {
        Category category = categoryRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Id Not Found" + id));

        if (!ObjectUtils.isEmpty(category)) {
            return mapper.map(category, CategoryDTO.class);
        }
        return null;
    }


    @Override
    public Boolean deleteCategory(Integer id) {
        Optional<Category> findByCatgeory = categoryRepo.findById(id);

        if (findByCatgeory.isPresent()) {
            Category category = findByCatgeory.get();
            category.setIsDeleted(true);
            categoryRepo.save(category);
            return true;
        }
        return false;
    }

}
    