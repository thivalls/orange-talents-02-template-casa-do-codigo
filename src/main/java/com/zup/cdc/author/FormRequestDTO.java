package com.zup.cdc.author;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class FormRequestDTO {
    @NotBlank
    private String name;

    @NotBlank
    @Length(max = 400)
    private String description;

    @NotBlank
    @Email
    private String email;

    public FormRequestDTO(@NotBlank String name, @NotBlank @Length(max = 400) String description, @NotBlank @Email String email) {
        this.name = name;
        this.description = description;
        this.email = email;
    }

    public Author makeAuthor() {
        return new Author(this.name, this.email, this.description);
    }
}
