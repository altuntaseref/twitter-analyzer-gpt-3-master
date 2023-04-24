package com.spring.tweetanalyzergpt3.service.abstracts;

import org.springframework.stereotype.Service;
import twitter4j.TwitterException;

import java.util.List;
import java.util.Map;

@Service
public interface RecommendationService {
    Map<String, String> solutionProposal(String tweet) throws TwitterException;
    String positiveTweetPropsal(List<String> positiveTweets,String tweet);
    String negativeTweetPropsal(List<String> negativeTweets,String tweet);
    String neutralTweetPropsal(List<String> neutralTweets,String tweet);

}
