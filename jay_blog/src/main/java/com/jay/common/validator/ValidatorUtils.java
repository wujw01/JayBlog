package com.jay.common.validator;


import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Configuration;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * 公共的参数校验
 *
 * @author xiang.wei
 */
public class ValidatorUtils {
    private static Validator validator;

    static {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .buildValidatorFactory();
        validator = validatorFactory.getValidator();

    }

    /**
     * 配置快速返回
     * @param object
     * @param groups
     * @return
     */
    public static String validEntity(Object object, Class<?>... groups) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (constraintViolations != null && constraintViolations.size() > 0) {
            for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
                return constraintViolation.getMessage();
            }
        }
        return null;
    }
}
