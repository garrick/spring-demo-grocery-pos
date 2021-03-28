package org.commandline.grocerypos.config;

import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan
public class WebMVCConfiguration {

        @Bean
        public ViewResolver freemarkerViewResolver() {
            ViewResolver resolver = new FreeMarkerViewResolver();
            return resolver;
        }

        @Bean
        public FreeMarkerConfigurer freemarkerConfig() {
            val freeMarkerConfigurer = new FreeMarkerConfigurer();
            freeMarkerConfigurer.setTemplateLoaderPath("classpath:/templates/ftl");
            return freeMarkerConfigurer;
        }
}
