package com.spring.tweetanalyzergpt3.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class ChatGptConfig {
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String API_KEY = "*********************************";
    public static final String MODEL = "text-davinci-002";
    public static final Integer MAX_TOKEN = 1024;
    public static final Double TEMPERATURE = 0.5;
    public static final Double TOP_P = 1.0;
    public static final String MEDIA_TYPE = "application/json; charset=UTF-8";
    public static final String URL = "https://api.openai.com/v1/completions";


}
