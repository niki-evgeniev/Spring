package com.example.xmlexercise.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintViolation;

import java.util.Set;

public interface ValidationUtil {

    <E> boolean isValid (E entity);

    <E> Set<ConstraintViolation<E>> violation(E entity);
}
