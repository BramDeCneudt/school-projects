/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import service.ProjectService;
import service.Service;

/**
 *
 * @author Bram
 */
@Configuration
@PropertySource("classpath:bundles/configuration.properties")
class ApplicationConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment env;

    @Bean(destroyMethod = "close")
    public Service service() {
        return new ProjectService(env);
    }

}
