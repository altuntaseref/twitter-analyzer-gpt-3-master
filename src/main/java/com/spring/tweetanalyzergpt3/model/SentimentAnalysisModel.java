package com.spring.tweetanalyzergpt3.model;

import lombok.Data;

@Data
public class SentimentAnalysisModel {
    private TwitterModel twitterModel;
    private BotRequest gptModel;
}
