package com.zup.cdc.book;

import com.zup.cdc.author.Author;

public class AuthorBookResponse {
    private String name;
    private String description;

    public AuthorBookResponse(Author author) {
        this.name = author.getName();
        this.description = author.getDescription();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
