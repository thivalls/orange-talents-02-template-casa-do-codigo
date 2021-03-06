package com.zup.cdc.book;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

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

    @GetMapping
    @Transactional
    public List<Book> all() {
        Query query = em.createQuery("select b.id, b.title from Book b");
        return query.getResultList();
    }

//    @GetMapping("/{id}")
//    @Transactional
//    public ResponseEntity<?> show(@PathVariable("id") Long id) {
//        Book book = em.find(Book.class, id);
//        if(book == null) {
//            return ResponseEntity.notFound().build();
//        }
//        BookResponse bookResponse = new BookResponse(book);
//        return ResponseEntity.ok(bookResponse);
//    }

    @GetMapping("/{id}")
    @Transactional
    public BookResponse show(@PathVariable("id") Long id) {
        Book book = em.find(Book.class, id);
        if(book == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        BookResponse bookResponse = new BookResponse(book);
        return bookResponse;
    }
}
