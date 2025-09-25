package br.com.fiap.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class I18nConfiguration implements WebMvcConfigurer {

    @Bean
    MessageSource messageSource(){
        var messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    @Bean
    LocaleChangeInterceptor localeChangeInterceptor(){
        var localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor((localeChangeInterceptor()));
    }

    @Bean
    LocaleResolver localeResolver(){
        var localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("pt", "BR"));
        return localeResolver;
    }
}
