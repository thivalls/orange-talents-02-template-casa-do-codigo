package com.zup.cdc.client.dto;

import com.zup.cdc.country.Country;

public class CountryDto {
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
