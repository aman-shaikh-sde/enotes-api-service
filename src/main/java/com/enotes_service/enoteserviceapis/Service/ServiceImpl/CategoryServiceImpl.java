package com.enotes_service.enoteserviceapis.Service.ServiceImpl;

import com.enotes_service.enoteserviceapis.DTOS.CategoryDTO;
import com.enotes_service.enoteserviceapis.DTOS.CategoryResponse;
import com.enotes_service.enoteserviceapis.Entity.Category;
import com.enotes_service.enoteserviceapis.Exception.ResourceNotFoundException;
import com.enotes_service.enoteserviceapis.Repository.CategoryRepo;
import com.enotes_service.enoteserviceapis.Service.CategoryService;
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



    @Override
    public boolean saveCategory(CategoryDTO categoryDTO) {


        Category category = mapper.map(categoryDTO, Category.class);

        if (ObjectUtils.isEmpty(category.getId())){
            category.setDeleted(false);
            //   category.setCreatedBy(1);
        category.setCreatedDate(new Date());
        category.setActive(categoryDTO.isActive());
    }else{
            updateCategory(category);
        }
        Category saveCategory =categoryRepo.save(category);

        if(ObjectUtils.isEmpty(saveCategory)){
            return false;
        }
        return true;
    }

    private void updateCategory(Category category) {

        Optional<Category> findById=categoryRepo.findById(category.getId());
        if(findById.isPresent()) {
            Category existCategory = findById.get();

            category.setCreatedBy(existCategory.getCreatedBy());
            category.setCreatedDate(existCategory.getCreatedDate());
    //            category.setUpdatedDate(new Date());
    //            category.setUpdatedBy(1);
        }



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

    public CategoryDTO getCategoryById(Integer id) throws Exception{
        Category category=categoryRepo.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Id Not Found"+id));

        if(!ObjectUtils.isEmpty(category)){
        return mapper.map(category,CategoryDTO.class);
        }
        return null;
    }



    @Override
    public void deleteCategory(Integer id) {
        Category CatId=categoryRepo.findById(id).orElse(new Category());
        this.categoryRepo.delete(CatId);

    }

}
    