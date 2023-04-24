package com.spring.tweetanalyzergpt3.utils;

import org.springframework.stereotype.Service;

@Service
public class SentimentsSeparatorUtils {

    public static String separateSentiment(String tweet){
        if (tweet.contains("positive")) {
            return "positive";
        } else if (tweet.contains("negative")) {
            return "negative";
        } else {
            return "neutral";
        }
    }

}
