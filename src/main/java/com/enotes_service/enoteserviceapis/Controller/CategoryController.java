package com.enotes_service.enoteserviceapis.Controller;

import com.enotes_service.enoteserviceapis.DTOS.CategoryDTO;
import com.enotes_service.enoteserviceapis.DTOS.CategoryResponse;
import com.enotes_service.enoteserviceapis.Entity.Category;
import com.enotes_service.enoteserviceapis.Service.CategoryService;
import com.enotes_service.enoteserviceapis.Service.ServiceImpl.CategoryServiceImpl;
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
   public ResponseEntity<?> saveCategory(@RequestBody CategoryDTO categoryDTO) {
       boolean savedCategory = service.saveCategory(categoryDTO);
       if (savedCategory) {
           return new ResponseEntity<>("Saved Succefully", HttpStatus.CREATED);
       } else {
           return new ResponseEntity<>("Not Saved",HttpStatus.INTERNAL_SERVER_ERROR);

       }
   }


    @GetMapping("/category")
    public ResponseEntity<?> getAllCategory(){

     List<CategoryDTO> allCategory=service.getAllCategory();
     if(CollectionUtils.isEmpty(allCategory)){

        return ResponseEntity.noContent().build();
    }else{
         return new ResponseEntity<>(allCategory,HttpStatus.OK);
     }

}


@GetMapping("/isActive-Category")
public ResponseEntity<?> getActiveCategory(){

       List<CategoryResponse> activeCategory=service.getisActiveTrue();
    if(CollectionUtils.isEmpty(activeCategory)){

        return ResponseEntity.noContent().build();
    }else{
        return new  ResponseEntity<>(activeCategory,HttpStatus.OK);
    }

}

@GetMapping("/category/{id}")
public ResponseEntity<?> getCategoryById(@PathVariable Integer id) throws Exception {
    CategoryDTO categoryDTO = service.getCategoryById(id);
    if (ObjectUtils.isEmpty(categoryDTO)) {
        return new ResponseEntity<>("Category not found with id " + id, HttpStatus.NOT_FOUND);

    }
    return new ResponseEntity<>(categoryDTO,HttpStatus.OK);
}

@DeleteMapping("/category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id){

       this.service.deleteCategory(id);
return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
}

}
