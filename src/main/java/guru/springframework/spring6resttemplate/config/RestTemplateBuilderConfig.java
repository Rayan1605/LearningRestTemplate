package guru.springframework.spring6resttemplate.config;

import lombok.Builder;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.web.client.RestTemplateBuilderConfigurer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.util.DefaultUriBuilderFactory;

public class RestTemplateBuilderConfig {
    @Bean
    RestTemplateBuilder restTemplateBuilder(RestTemplateBuilderConfigurer configurer){

        RestTemplateBuilder restTemplateBuilder =  configurer.configure(new RestTemplateBuilder());
        DefaultUriBuilderFactory defaultUriBuilderFactory =
                new DefaultUriBuilderFactory("http://localhost:8080");
        //You need the uriTemplateHandler so it return the correct url
        return restTemplateBuilder.uriTemplateHandler(defaultUriBuilderFactory);

    }
}
