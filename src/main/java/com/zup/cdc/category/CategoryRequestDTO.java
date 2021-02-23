package com.zup.cdc.category;

import javax.validation.constraints.NotBlank;

public class CategoryRequestDTO {
    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public Category toCategory(String name) {
        return new Category(name);
    }
}
