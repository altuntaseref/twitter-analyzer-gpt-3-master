package com.spring.tweetanalyzergpt3.service.abstracts;

import com.spring.tweetanalyzergpt3.model.TwitterModel;
import org.springframework.stereotype.Service;
import twitter4j.TwitterException;

import java.util.List;

@Service
public interface TwitterService {

    List<String> getRecentTweets(String model) throws TwitterException;

}
