package com.zup.cdc.shared.validators;

import com.zup.cdc.category.Category;
import com.zup.cdc.category.CategoryRepository;
import com.zup.cdc.category.CategoryRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class CustomUniqueCategoryValidator implements Validator {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return CategoryRequestDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }

        CategoryRequestDTO request = (CategoryRequestDTO) target;

        Optional<Category> categoryExist = categoryRepository.findByName(request.getName());

        if(categoryExist.isPresent()) {
            errors.rejectValue("name", "Unique.CategoryRequestDTO.name");
        }
    }
}
