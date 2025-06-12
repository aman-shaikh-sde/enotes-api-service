package com.enotes_service.enoteserviceapis.Util;

import com.enotes_service.enoteserviceapis.DTOS.CategoryDTO;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class Validation {

    public void categoryValidation(CategoryDTO categoryDTO) {

        Map<String, Object> error = new LinkedHashMap<>();

        if (ObjectUtils.isEmpty(categoryDTO)) {
            throw new IllegalArgumentException("category Object/JSON shouldn't be null or empty");
        } else {

            // validation name field
            if (ObjectUtils.isEmpty(categoryDTO.getName())) {
                error.put("name", "name field is empty or null");
            } else {
                if (categoryDTO.getName().length() < 3) {
                    error.put("name", "name length min 3");
                }
                if (categoryDTO.getName().length() > 100) {
                    error.put("name", "name length max 100");
                }
            }

            // validation dscription
            if (ObjectUtils.isEmpty(categoryDTO.getDescription())) {
                error.put("description", "description field is empty or null");
            }

            // validation isActive
            if (ObjectUtils.isEmpty(categoryDTO.isActive())) {
                error.put("isActive", "isActive field is empty or null");
            } else {
                if (categoryDTO.isActive() != Boolean.TRUE.booleanValue()
                        && categoryDTO.isActive() != Boolean.FALSE.booleanValue()) {
                    error.put("isActive", "invalid value isActive field ");
                }
            }
        }

        if (!error.isEmpty()) {
            throw new ValidationException(String.valueOf(error));
        }

    }

}
