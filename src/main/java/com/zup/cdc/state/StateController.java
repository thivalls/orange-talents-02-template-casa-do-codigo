package com.zup.cdc.state;

import org.springframework.http.HttpStatus;
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
@RequestMapping("states")
public class StateController {
    @PersistenceContext
    private EntityManager em;

    @PostMapping
    @Transactional
    public String store(@RequestBody @Valid  StateRequest request) {
        // state, country_id
        State state = request.toState(em);

        Query query = em.createQuery("select s from State s where s.country.id = :fk and s.name = :name");
        query.setParameter("fk", request.getCountryId());
        query.setParameter("name", request.getName());
        List resultList = query.getResultList();

        if(resultList.size() >= 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This state has been already registered for this country");
        }

        em.persist(state);
        return state.toString();
    }
}
