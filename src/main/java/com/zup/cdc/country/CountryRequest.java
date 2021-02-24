package com.zup.cdc.country;

import com.zup.cdc.shared.validators.UniqueField;

import javax.validation.constraints.NotBlank;

public class CountryRequest {
    @NotBlank
    @UniqueField(fieldName = "name", domainClass = Country.class, message = "This country has been already registered")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country toCountry() {
        return new Country(this.name);
    }
}
