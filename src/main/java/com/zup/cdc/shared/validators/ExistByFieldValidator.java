package com.zup.cdc.shared.validators;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistByFieldValidator implements ConstraintValidator<ExistByField, Object> {
    private String domainAttribute;
    private Class<?> klass;

    @PersistenceContext
    private EntityManager em;


    @Override
    public void initialize(ExistByField constraintAnnotation) {
        domainAttribute = constraintAnnotation.fieldName();
        klass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String jpql = "select 1 from " + klass.getName() + " where " + domainAttribute + " = :value";
        Query query = em.createQuery(jpql);
        query.setParameter("value", o);
        List<?> list = query.getResultList();

        Assert.state(list.size() <= 1, "Entidade " + klass + " não existe");
        return !list.isEmpty();
    }
}
