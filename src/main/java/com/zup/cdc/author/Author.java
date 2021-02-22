package com.zup.cdc.author;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotBlank String name;
    private @NotBlank @Length(max = 400)String description;

    @Column(unique=true)
    @NotBlank
    @Email
    private String email;

    private LocalDateTime createdAt = LocalDateTime.now();

    @Deprecated
    public Author() {
    }

    public Author(@NotBlank String name, @NotBlank @Length(max = 400) String description, @NotBlank @Email String email) {
        this.name = name;
        this.description = description;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
