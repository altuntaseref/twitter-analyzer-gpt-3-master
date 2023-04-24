package com.spring.tweetanalyzergpt3.service.abstracts;

import com.spring.tweetanalyzergpt3.model.SentimentAnalysisModel;
import org.springframework.stereotype.Service;
import twitter4j.TwitterException;

import java.util.List;
import java.util.Map;

@Service
public interface SentimentAnalysisService {
    Map<String, List<String>> fetchAndInquireTweet(String sentimentAnalysisModel) throws TwitterException;
    Map<String, Double> separeteSentiments(List<String> tweetList);
    Map<String, Double> analyzeTweet(String sentimentAnalysisModel) throws TwitterException;
}
