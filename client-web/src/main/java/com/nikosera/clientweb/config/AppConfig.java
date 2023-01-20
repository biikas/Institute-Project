package com.nikosera.clientweb.config;

import com.nikosera.cas.config.properties.CasFileConfig;
import com.nikosera.common.formatter.UnhashFormatterFactory;
import com.nikosera.common.service.IdHasherService;
import com.nikosera.common.service.impl.IdHasherServiceHashIdsImpl;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.AllArgsConstructor;
import org.hashids.Hashids;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author Narayan Joshi
 * @email narenzoshi@gmail.com
 */
@Configuration
@AutoConfigureAfter(CasFileConfig.class)
@AllArgsConstructor
public class AppConfig extends WebMvcConfigurationSupport {

    private CasFileConfig casConfig;

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/resources/");
        registry.addResourceHandler("swagger-resources/**");
        registry.addResourceHandler("swagger-resources/configuration/ui");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/**");
        registry.addResourceHandler("/img/**").addResourceLocations("/img/**");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/**");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    Hashids hashids() {
        return new Hashids(casConfig.getHashId().getSalt(), casConfig.getHashId().getMinHashLength());
    }

    @Bean
    IdHasherService idHasher() {
        return new IdHasherServiceHashIdsImpl(hashids());
    }

    @Override
    protected void addFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldAnnotation(new UnhashFormatterFactory(idHasher()));
    }

    @Bean
    @Primary
    RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jacksonMessageConverter = new MappingJackson2HttpMessageConverter();

        ObjectMapper objectMapper = jacksonMessageConverter.getObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.disable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        converters.add(jacksonMessageConverter);

        converters.add(new ByteArrayHttpMessageConverter());
        // supports text/html in spring restcontroller statements
        converters.add(new StringHttpMessageConverter(Charset.forName("utf-8")));
    }

    @Bean
    ObjectMapper xmlMapper() {
        return new XmlMapper();
    }
}
