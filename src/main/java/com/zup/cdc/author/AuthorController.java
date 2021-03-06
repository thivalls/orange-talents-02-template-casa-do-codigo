package com.zup.cdc.author;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @PersistenceContext
    private EntityManager em;

//    @Autowired
//    private CustomUniqueEmailValidator customUniqueEmailValidator;
//
//    @InitBinder
//    public void init(WebDataBinder binder) {
//        binder.addValidators(customUniqueEmailValidator);
//    }

    @PostMapping
    @Transactional
    public String store(@RequestBody @Valid AuthorRequestDTO form) {
        Author author = form.makeAuthor();
        em.persist(author);
        return author.toString();
    }
}
