package com.zup.cdc.book;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("books")
public class BookController {
    @PersistenceContext
    private EntityManager em;

    @PostMapping
    @Transactional
    public String store(@RequestBody @Valid BookRequestDTO request) {
        Book book = request.toBook(em);
        em.persist(book);

        return book.toString();
    }
}
