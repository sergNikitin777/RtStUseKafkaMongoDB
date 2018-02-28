/**
 *
 */
package com.kafkaiot.controller;

import org.apache.log4j.BasicConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import com.kafkaiot.service.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kafkaiot
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.kafkaiot.controller")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    KafkaEventConsumerMongo kafkaEventConsumerMongo;

    @Autowired
    KafkaEventConsumerPostgres kafkaEventConsumerPostgres;

    @PostConstruct
    public void contextInitialized() {
        System.out.println("Context Initialised");
        System.out.println("start");
        BasicConfigurator.configure();

        kafkaEventConsumerMongo.start();

        kafkaEventConsumerPostgres.start();


//        try {
//            KafkaEventConsumer kafkaConsumer = new KafkaEventConsumer();
//            kafkaConsumer.start();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        List<MediaType> mediatypes = new ArrayList<MediaType>();
        mediatypes.add(MediaType.APPLICATION_JSON);
        converter.setSupportedMediaTypes(mediatypes);
        converters.add(converter);
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

}
