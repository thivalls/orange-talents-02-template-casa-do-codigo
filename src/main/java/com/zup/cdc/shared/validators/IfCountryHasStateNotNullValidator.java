package com.zup.cdc.shared.validators;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IfCountryHasStateNotNullValidator implements ConstraintValidator<IfCountryHasStateNotNull, Object> {
    private String domainAttribute;
    private Class<?> klass;

    @PersistenceContext
    private EntityManager em;

    @Override
    public void initialize(IfCountryHasStateNotNull constraintAnnotation) {
        domainAttribute = constraintAnnotation.fieldName();
        klass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        //String jpql = "select c from " + klass.getName() + "group by c.state";
//        Query query = em.createNativeQuery(sql);
        // Query query = em.createQuery(jpql);
//        query.setParameter("value", o);
        //List<?> list = query.getResultList();
        //System.out.println(list.size());
        // Assert.state(list.size() <= 1, "Entidade " + klass + " já existe");
        return true;
    }
}
