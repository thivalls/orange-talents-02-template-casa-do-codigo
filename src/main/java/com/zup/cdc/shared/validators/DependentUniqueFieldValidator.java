package com.zup.cdc.shared.validators;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class DependentUniqueFieldValidator implements ConstraintValidator<DependentUniqueField, Object> {
    private String domainAttribute;
    private String domainDependentAttribute;
    private Class<?> klass;

    @PersistenceContext
    private EntityManager em;


    @Override
    public void initialize(DependentUniqueField constraintAnnotation) {
        domainAttribute = constraintAnnotation.fieldName();
        domainDependentAttribute = constraintAnnotation.dependentFieldName();
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
