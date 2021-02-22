package com.zup.cdc.author.validators;

import com.zup.cdc.author.Author;
import com.zup.cdc.author.AuthorRepository;
import com.zup.cdc.author.FormRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class CustomUniqueEmailValidator implements Validator {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return FormRequestDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }
        System.out.println("passei por aqui");
        FormRequestDTO formRequestDTO = (FormRequestDTO) target;

        Optional<Author> authorExist = authorRepository.findByEmail(formRequestDTO.getEmail());

        if(authorExist.isPresent()){
            errors.rejectValue("email", "Unique.FormRequestDTO.email");
        }
    }
}
