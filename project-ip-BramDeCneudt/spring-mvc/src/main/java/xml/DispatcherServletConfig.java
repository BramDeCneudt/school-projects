/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import java.util.Locale;
import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.ParameterizableViewController;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 *
 * @author Bram
 */
@Configuration
@ComponentScan("controller")
@EnableWebMvc
class DispatcherServletConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localChangeResolver());
    }

    @Override
    public Validator getValidator() {
        return localValidatorFactoryBean();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/");
    }

    @Bean(name = "validator")
    public LocalValidatorFactoryBean localValidatorFactoryBean() {
        LocalValidatorFactoryBean lvfb = new LocalValidatorFactoryBean();

        lvfb.setValidationMessageSource(resourceBundleMessageSource());

        return lvfb;
    }

    @Bean
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public SimpleUrlHandlerMapping urlMapping() {
        SimpleUrlHandlerMapping urlMapping = new SimpleUrlHandlerMapping();

        Properties props = new Properties();
        props.put("index.htm", this.getIndexController());

        urlMapping.setMappings(props);
        return urlMapping;
    }

    @Bean
    public ParameterizableViewController getIndexController() {
        ParameterizableViewController indexController = new ParameterizableViewController();
        indexController.setViewName("index");
        return indexController;
    }

    @Bean
    ParameterizableViewController getAboutController() {
        ParameterizableViewController aboutController = new ParameterizableViewController();
        aboutController.setViewName("about");
        return aboutController;
    }

    @Bean
    ParameterizableViewController getContactController() {
        ParameterizableViewController contactController = new ParameterizableViewController();
        contactController.setViewName("contact");
        return contactController;
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(20848820);
        multipartResolver.setDefaultEncoding("utf-8");
        return multipartResolver;
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

    @Bean(name = "messageSource")
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("bundles/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean(name = "validationSource")
    public ResourceBundleMessageSource resourceBundleMessageSource() {
        ResourceBundleMessageSource validationSource = new ResourceBundleMessageSource();
        validationSource.setBasename("bundles/validationMessages");
        return validationSource;
    }

}
