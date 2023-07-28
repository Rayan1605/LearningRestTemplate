package guru.springframework.spring6resttemplate.config;

import lombok.Builder;
import lombok.Value;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.web.client.RestTemplateBuilderConfigurer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.DefaultUriBuilderFactory;
@Configuration
public class RestTemplateBuilderConfig {

  //The RestTemplateBuilder in Spring is used to create and configure RestTemplate instances.
    // Here are some key points:
    //
    //RestTemplate is used to make HTTP requests in Spring, like GET and POST.
    //
    //RestTemplateBuilder constructs the RestTemplate with desired settings.
    //
    //It uses a builder pattern API to customize things like:
    //
    //Base URL
    //Message converters for mapping JSON and XML
    //Client HTTP libraries like Apache HttpComponents
    //Custom request/response interceptors
    //Builds an immutable RestTemplate with the provided customization.
    //
    //Convenient way to centrally configure and inject preset RestTemplates.

    //and here we are making our own customized RestTemplateBuilder by changing the base url
    // Make sure you use RestTemplateBuilderConfigurer and the configure method to use the
    // default spring RestTemplateBuilder and then change the base url
    @Bean
    RestTemplateBuilder restTemplateBuilder(RestTemplateBuilderConfigurer configurer){

        RestTemplateBuilder restTemplateBuilder =  configurer.configure(new RestTemplateBuilder());
        DefaultUriBuilderFactory defaultUriBuilderFactory =
                new DefaultUriBuilderFactory("http://localhost:8080");
        //You need the uriTemplateHandler so it return the correct url
        return restTemplateBuilder.uriTemplateHandler(defaultUriBuilderFactory);

    }
}
