package com.zup.cdc.book;

import com.zup.cdc.author.Author;
import com.zup.cdc.category.Category;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String title;

    @NotBlank
    @Size(max = 500)
    private String brief;

    @Column(columnDefinition = "text")
    private String summary;

    @NotNull
    @Min(100)
    private int pages;

    @NotNull
    @Min(20)
    private BigDecimal price;

    @NotBlank
    @Column(unique = true)
    private String isbn;

    @NotNull
    private LocalDate publishDate;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Author author;

    @Deprecated
    public Book() {
    }

    public Book(@NotBlank String title, @NotBlank @Size(max = 500) String brief, String summary, @NotNull @Min(100) int pages, @NotNull @Min(20) BigDecimal price, @NotBlank String isbn, @NotNull LocalDate publishDate, Category category, Author author) {
        this.title = title;
        this.brief = brief;
        this.summary = summary;
        this.pages = pages;
        this.price = price;
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.category = category;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBrief() {
        return brief;
    }

    public String getSummary() {
        return summary;
    }

    public int getPages() {
        return pages;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public Category getCategory() {
        return category;
    }

    public Author getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", brief='" + brief + '\'' +
                ", summary='" + summary + '\'' +
                ", pages=" + pages +
                ", price=" + price +
                ", isbn='" + isbn + '\'' +
                ", category=" + category +
                ", author=" + author +
                '}';
    }

    /*
     * Esta situação seria possível, mas seria melhor deixar a responsabilidade do response para o controller
     * para que esta classe não seja inflada a cada tipo de response diferente que seja necessária no sistema
     */

    //    public BookResponse toBookResponse() {
    //        return new BookResponse(this);
    //    }
}
