package com.lsn.user.configurer;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestTemplateConfiguration {

  @Bean
  public RestTemplate restTemplate(ClientHttpRequestFactory factory){
    RestTemplate restTemplate = new RestTemplate(factory);
    MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
    List<MediaType> mediaTypes = new ArrayList<>();
    mediaTypes.add(MediaType.ALL);//支持全部类型
    messageConverter.setSupportedMediaTypes(mediaTypes);
    restTemplate.getMessageConverters().add(messageConverter);
    return restTemplate;
  }

  @Bean
  public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
    SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
    factory.setReadTimeout(3000);
    factory.setConnectTimeout(3000);
    return factory;
  }

}