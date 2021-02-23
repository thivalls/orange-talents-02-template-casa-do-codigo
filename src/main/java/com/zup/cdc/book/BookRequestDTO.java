package com.zup.cdc.book;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zup.cdc.author.Author;
import com.zup.cdc.category.Category;
import com.zup.cdc.shared.validators.ExistByField;
import com.zup.cdc.shared.validators.UniqueField;

import javax.persistence.EntityManager;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public class BookRequestDTO {
    @NotBlank
    @UniqueField(fieldName = "title", domainClass = Book.class)
    private String title;

    @NotBlank
    @Size(max = 500)
    private String brief;

    @NotBlank
    private String summary;

    @NotNull
    @Min(100)
    private int pages;

    @NotNull
    @Min(20)
    private BigDecimal price;

    @NotBlank
    @UniqueField(fieldName = "isbn", domainClass = Book.class)
    private String isbn;

    @Future
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    // @DateTimeFormat(pattern = "") em casos de Form URL Enconded
    private LocalDate publishDate;

    @NotNull
    @ExistByField(fieldName = "id", domainClass = Category.class)
    private Long categoryId;

    @NotNull
    @ExistByField(fieldName = "id", domainClass = Author.class)
    private Long authorId;

    public BookRequestDTO(@NotBlank String title, @NotBlank @Size(max = 500) String brief, @NotBlank String summary, @NotBlank @Size(min = 100) int pages, @NotBlank @Size(min = 20) BigDecimal price, @NotBlank String isbn, @Future LocalDate publishDate, @NotBlank Long categoryId, @NotBlank Long authorId) {
        this.title = title;
        this.brief = brief;
        this.summary = summary;
        this.pages = pages;
        this.price = price;
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.categoryId = categoryId;
        this.authorId = authorId;
    }

    // Apenas para o Jackson fazer o bind no formato de data
    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public Book toBook(EntityManager em) {
        @NotNull Author author = em.find(Author.class, this.authorId);
        @NotNull Category category = em.find(Category.class, this.categoryId);
        return new Book(
                this.title,
                this.brief,
                this.summary,
                this.pages,
                this.price,
                this.isbn,
                this.publishDate,
                category,
                author
        );
    }

    @Override
    public String toString() {
        return "BookRequestDTO{" +
                "title='" + title + '\'' +
                ", brief='" + brief + '\'' +
                ", summary='" + summary + '\'' +
                ", pages=" + pages +
                ", price=" + price +
                ", isbn='" + isbn + '\'' +
                ", publishDate=" + publishDate +
                ", categoryId=" + categoryId +
                ", authorId=" + authorId +
                '}';
    }
}
