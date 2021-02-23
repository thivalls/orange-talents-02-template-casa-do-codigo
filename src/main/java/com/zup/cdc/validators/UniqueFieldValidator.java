package com.zup.cdc.validators;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueFieldValidator implements ConstraintValidator<UniqueField, Object> {
    private String domainAttribute;
    private Class<?> klass;

    @PersistenceContext
    private EntityManager em;


    @Override
    public void initialize(UniqueField constraintAnnotation) {
        domainAttribute = constraintAnnotation.fieldName();
        klass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String jpql = "select 1 from " + klass.getName() + " where " + domainAttribute + " = :value";
        Query query = em.createQuery(jpql);
        query.setParameter("value", o);
        List<?> list = query.getResultList();

        Assert.state(list.size() <= 1, "Entidade " + klass + " já existe");
        return list.isEmpty();
    }
}
