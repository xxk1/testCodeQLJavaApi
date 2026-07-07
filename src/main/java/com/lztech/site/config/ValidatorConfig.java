package com.lztech.site.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;
import java.util.Properties;

/**
 * @Author: ShiChunLu
 * @Date: 2023/1/5 5:01 下午
 * @Version 1.0
 */
@Configuration
public class ValidatorConfig {

    @Autowired
    private MessageSource messageSource;

    @Bean
    public Validator validator()
    {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setProviderClass(HibernateValidator.class);
        Properties properties = new Properties();
        properties.setProperty("hibernate.validator.fail_fast","false");
        localValidatorFactoryBean.setValidationProperties(properties);
        localValidatorFactoryBean.afterPropertiesSet();
        return localValidatorFactoryBean.getValidator();
    }
}
