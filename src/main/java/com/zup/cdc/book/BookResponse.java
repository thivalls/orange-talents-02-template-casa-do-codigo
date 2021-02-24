package com.zup.cdc.book;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookResponse {
    private Long id;
    private String title;
    private String brief;
    private String summary;
    private int pages;
    private BigDecimal price;
    private String isbn;
    private LocalDate publishDate;
    private String category;
    private String authorName;
    private String authorDescription;

    public BookResponse(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.brief = book.getBrief();
        this.summary = book.getSummary();
        this.pages = book.getPages();
        this.price = book.getPrice();
        this.isbn = book.getIsbn();
        this.publishDate = book.getPublishDate();
        this.category = book.getCategory().getName();
        this.authorName = book.getAuthor().getName();
        this.authorDescription = book.getAuthor().getDescription();
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

    public String getCategory() {
        return category;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getAuthorDescription() {
        return authorDescription;
    }
}
