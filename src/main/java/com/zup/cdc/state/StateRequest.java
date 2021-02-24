package com.zup.cdc.state;

import com.zup.cdc.country.Country;
import com.zup.cdc.country.CountryRequest;
import com.zup.cdc.shared.validators.ExistByField;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class StateRequest {
    @NotBlank
    private String name;

    @NotNull
    @ExistByField(fieldName = "id", domainClass = Country.class)
    private Long countryId;

    public State toState(EntityManager em) {
        return new State(this.name, em.find(Country.class, countryId));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }
}
