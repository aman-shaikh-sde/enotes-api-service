package com.enotes_service.enoteserviceapis.Controller;

import com.enotes_service.enoteserviceapis.DTOS.CategoryDTO;
import com.enotes_service.enoteserviceapis.DTOS.CategoryResponse;
import com.enotes_service.enoteserviceapis.Entity.Category;
import com.enotes_service.enoteserviceapis.Handler.CommonUtil;
import com.enotes_service.enoteserviceapis.Service.CategoryService;
import com.enotes_service.enoteserviceapis.Service.ServiceImpl.CategoryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl service;


   @PostMapping("/category")
   public ResponseEntity<?> saveCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
       CategoryDTO savedCategory = service.saveCategory(categoryDTO);
       if (!ObjectUtils.isEmpty(savedCategory)) {
           return CommonUtil.createBuildResponseMessage("Category Saved Successfully",HttpStatus.CREATED,savedCategory);
           //return new ResponseEntity<>("Saved Succefully", HttpStatus.CREATED);
       } else {
           return CommonUtil.createErrorResponseMessage("Category Not Saved",HttpStatus.INTERNAL_SERVER_ERROR);
           //return new ResponseEntity<>("Not Saved",HttpStatus.INTERNAL_SERVER_ERROR);

       }
   }


    @GetMapping("/category")
    public ResponseEntity<?> getAllCategory(){

     List<CategoryDTO> allCategory=service.getAllCategory();
     if(CollectionUtils.isEmpty(allCategory)){

        return ResponseEntity.noContent().build();
    }else{
         return CommonUtil.createBuildResponse(allCategory,HttpStatus.OK);
         //return new ResponseEntity<>(allCategory,HttpStatus.OK);
     }

}


@GetMapping("/isActive-Category")
public ResponseEntity<?> getActiveCategory(){

       List<CategoryResponse> activeCategory=service.getisActiveTrue();
    if(CollectionUtils.isEmpty(activeCategory)){

        return ResponseEntity.noContent().build();
    }else{
        return CommonUtil.createBuildResponse(activeCategory,HttpStatus.FOUND);
    }

}

@GetMapping("/category/{id}")
public ResponseEntity<?> getCategoryById(@PathVariable Integer id) throws Exception {
    CategoryDTO categoryDTO = service.getCategoryById(id);
    if (ObjectUtils.isEmpty(categoryDTO)) {
        return CommonUtil.createErrorResponseMessage("Category not found with id " + id, HttpStatus.NOT_FOUND);

        //return new ResponseEntity<>("Category not found with id " + id, HttpStatus.NOT_FOUND);

    }

    return CommonUtil.createBuildResponse(categoryDTO,HttpStatus.OK);
    //return new ResponseEntity<>(categoryDTO,HttpStatus.OK);
}

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Integer id) {
        Boolean deleted = service.deleteCategory(id);
        if (deleted) {
            return CommonUtil.createBuildResponseMessage("Category deleted success", HttpStatus.OK,null);
            // return new ResponseEntity<>("Category deleted success", HttpStatus.OK);
        }
        return CommonUtil.createErrorResponseMessage("Category Not deleted", HttpStatus.INTERNAL_SERVER_ERROR);

        // return new ResponseEntity<>("Category Not deleted", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
