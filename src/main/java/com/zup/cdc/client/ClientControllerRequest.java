package com.zup.cdc.client;

import com.zup.cdc.country.Country;
import com.zup.cdc.shared.validators.ExistByField;
import com.zup.cdc.shared.validators.UniqueField;
import com.zup.cdc.shared.validators.ValidCpfAndCnpj;
import com.zup.cdc.state.State;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ClientControllerRequest {

    @NotBlank
    @Email
    @UniqueField(domainClass = Client.class, fieldName = "email", message = "This email has already been registered")
    private String email;

    @NotBlank
    private String name;
    @NotBlank
    private String lastname;

    @NotBlank
    @ValidCpfAndCnpj(domainClass = Client.class, fieldName = "document", message = "Invalid document")
    @UniqueField(domainClass = Client.class, fieldName = "document", message = "This document has already been registered")
    private String document;

    @NotBlank
    private String address;
    @NotBlank
    private String complement;
    @NotBlank
    private String city;

    @NotNull
    @ExistByField(domainClass = Country.class, fieldName = "id", message = "This country not exist")
    private Long country;

    private Long state;

    @NotBlank
    private String phone;
    @NotBlank
    private String zipcode;

    public Client toModel(EntityManager em) {
        //1
        Country selectedCountry = em.find(Country.class, country);

        //1
        if (selectedCountry.getState().size() >= 1 && state == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The state is required for this country");
        }

        //1
        if (selectedCountry.getState().size() == 0 && state != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This country has no states registered, please remove the state field");
        }

        //4
        if (state != null) {
            State selectedState = em.find(State.class, state);

            if (selectedState == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This state does not exist");
            }

            if (selectedState.getCountry().getId() != this.country && selectedCountry.getState().size() >= 1) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This state does not belong to this country");
            }

            return new Client(email, name, lastname, document, address, complement, city, country, selectedState, phone, zipcode);
        }

        //1
        return new Client(email, name, lastname, document, address, complement, city, country, null, phone, zipcode);
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getDocument() {
        return document;
    }

    public String getAddress() {
        return address;
    }

    public String getComplement() {
        return complement;
    }

    public String getCity() {
        return city;
    }

    public Long getCountry() {
        return country;
    }

    public Long getState() {
        return state;
    }

    public String getPhone() {
        return phone;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(Long country) {
        this.country = country;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
