/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

/**
 *
 * @author Bram
 */
@Configuration
@ComponentScan("controller")
@EnableWebMvc
public class DispatcherServletConfig extends WebMvcConfigurerAdapter {
    
        @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/html/**").addResourceLocations("/html/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localChangeResolver());
    }

    @Override
    public Validator getValidator() {
        return localValidatorFactoryBean();
    }

    @Bean(name = "validator")
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        LocalValidatorFactoryBean lvfb = new LocalValidatorFactoryBean();

        lvfb.setValidationMessageSource(resourceBundleMessageSource());

        return lvfb;
    }

    @Bean(name = "validationSource")
    public ResourceBundleMessageSource resourceBundleMessageSource() {
        ResourceBundleMessageSource validationSource = new ResourceBundleMessageSource();
        validationSource.setBasename("bundles/validationMessages");
        return validationSource;
    }

    @Bean(name = "localeResolver")
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localResolver = new CookieLocaleResolver();
        localResolver.setDefaultLocale(Locale.forLanguageTag("nl"));
        return localResolver;
    }

    @Bean
    public LocaleChangeInterceptor localChangeResolver() {
        LocaleChangeInterceptor localChangeResolver = new LocaleChangeInterceptor();
        localChangeResolver.setParamName("lang");
        return localChangeResolver;
    }


}
