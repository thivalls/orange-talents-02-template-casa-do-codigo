package com.zup.cdc.author;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class FormRequestDTO {
    @NotBlank
    private String name;

    @NotBlank
    @Length(max = 400)
    private String description;

    @NotBlank
    @Email
    private String email;

    @NotNull
    private LocalDateTime createdAt = LocalDateTime.now();

    public void FormRequestDTO(String name, String email, String description) {
        this.name = name;
        this.email = email;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Author makeAuthor() {
        Author author = new Author();
        author.setName(this.getName());
        author.setEmail(this.getEmail());
        author.setDescription(this.getDescription());
        author.setCreatedAt(this.getCreatedAt());
        return author;
    }
}
