package com.zup.cdc.category;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("categories")
public class CategoryController {
    @PersistenceContext
    private EntityManager em;

//    @Autowired
//    private CustomUniqueCategoryValidator customUniqueCategoryValidator;
//
//    @InitBinder
//    public void init(WebDataBinder binder) {
//        binder.addValidators(customUniqueCategoryValidator);
//    }

    @PostMapping
    @Transactional
    public String store(@RequestBody @Valid CategoryRequestDTO request) {
        Category category = request.toCategory(request.getName());
        em.persist(category);
        return category.toString();
    }
}
