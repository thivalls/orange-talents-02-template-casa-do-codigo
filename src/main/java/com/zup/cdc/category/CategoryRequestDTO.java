package com.zup.cdc.category;

import com.zup.cdc.shared.validators.UniqueField;

import javax.validation.constraints.NotBlank;

public class CategoryRequestDTO {
    @NotBlank
    @UniqueField(fieldName = "name", domainClass = Category.class, message = "This category has been already registered")
    private String name;

    public String getName() {
        return name;
    }

    public Category toCategory(String name) {
        return new Category(name);
    }
}
