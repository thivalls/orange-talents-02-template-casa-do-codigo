package com.zup.cdc.state;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("states")
public class StateController {
    @PersistenceContext
    private EntityManager em;

    @PostMapping
    @Transactional
    public String store(@RequestBody @Valid  StateRequest request) {
        // state, country_id
        State state = request.toState(em);
        em.persist(state);
        return state.toString();
    }
}
