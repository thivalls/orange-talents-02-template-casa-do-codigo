package com.zup.cdc.state;

import com.zup.cdc.country.Country;
import com.zup.cdc.shared.validators.ExistByField;
import com.zup.cdc.shared.validators.UniqueField;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class StateRequest {
    @NotBlank
    private String name;

    @NotNull
    @ExistByField(fieldName = "id", domainClass = Country.class, message = "This country not exist")
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
