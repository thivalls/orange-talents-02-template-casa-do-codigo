package com.zup.cdc.author;

import com.zup.cdc.validators.UniqueField;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AuthorRequestDTO {
    @NotBlank
    private String name;

    @NotBlank
    @Length(max = 400)
    private String description;

    @NotBlank
    @Email
    @UniqueField(fieldName = "email", domainClass = Author.class)
    private String email;

    public AuthorRequestDTO(@NotBlank String name, @NotBlank @Length(max = 400) String description, @NotBlank @Email String email) {
        this.name = name;
        this.description = description;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public Author makeAuthor() {
        return new Author(this.name, this.description, this.email);
    }
}
