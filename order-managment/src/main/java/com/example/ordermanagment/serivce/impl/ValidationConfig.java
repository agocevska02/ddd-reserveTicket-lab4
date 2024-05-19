package com.example.ordermanagment.serivce.impl;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.validation.Validation;
import javax.validation.Validator;
import org.hibernate.validator.HibernateValidator;

@Configuration
public class ValidationConfig {
    @Bean
    public Validator validator() {

        return Validation.byDefaultProvider()
                .configure()
                .messageInterpolator( new ParameterMessageInterpolator())
                .buildValidatorFactory().getValidator();
    }
}