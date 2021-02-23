package com.zup.cdc.validators;

import com.zup.cdc.author.Author;
import com.zup.cdc.author.AuthorRepository;
import com.zup.cdc.author.AuthorRequestDTO;
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
        return AuthorRequestDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }

        AuthorRequestDTO formRequestDTO = (AuthorRequestDTO) target;

        Optional<Author> authorExist = authorRepository.findByEmail(formRequestDTO.getEmail());

        if(authorExist.isPresent()){
            errors.rejectValue("email", "Unique.AuthorRequestDTO.email");
        }
    }
}
