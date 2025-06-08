package com.enotes_service.enoteserviceapis.Service.ServiceImpl;

import com.enotes_service.enoteserviceapis.Entity.Category;
import com.enotes_service.enoteserviceapis.Repository.CategoryRepo;
import com.enotes_service.enoteserviceapis.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;



    @Override
    public boolean saveCategory(Category category) {
        category.setDeleted(false);
        category.setCreatedBy(1);
        category.setCreatedDate(new Date());
        Category saveCategory =categoryRepo.save(category);

        if(ObjectUtils.isEmpty(saveCategory)){
            return false;
        }
        return true;
    }


    @Override
    public List<Category> getAllCategory() {
        List<Category>allCategoty=categoryRepo.findAll();
        return allCategoty;
    }
}
