package com.spring.tweetanalyzergpt3.service.concretes;

import com.spring.tweetanalyzergpt3.config.client.TwitterClient;
import com.spring.tweetanalyzergpt3.service.abstracts.TwitterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TwitterServiceImpl implements TwitterService {
    @Autowired
    private TwitterClient twitterClient;

    @Override
    public List<String> getRecentTweets(String tweet) throws TwitterException {

        try {
            List<Status> fetchTweets = twitterClient.fetchTweets(tweet);
            List<String> tweetsString = new ArrayList<>();

            log.info("Cekilen tweet sayisi: "+fetchTweets.size());
            for (Status status : fetchTweets) {
                tweetsString.add(status.getText());
            }
            return tweetsString;
        } catch (TwitterException e) {
            log.error(e.getMessage(), e);
           throw e;
        }
    }
}
