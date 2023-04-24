package com.spring.tweetanalyzergpt3.config.client;

import com.spring.tweetanalyzergpt3.service.concretes.GPTServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

@Service
@Slf4j
public class TwitterClient {

    @Autowired
    private GPTServiceImpl botService;

    public List<Status> fetchTweets(String tweet) throws TwitterException {

        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.setDebugEnabled(true)
                .setOAuthConsumerKey("******************")
                .setOAuthConsumerSecret("******************")
                .setOAuthAccessToken("******************-******************")
                .setOAuthAccessTokenSecret("******************");


        TwitterFactory tc = new TwitterFactory(builder.build());
        Twitter twitter = tc.getInstance();

        Query query = new Query(tweet+" lang:en");
        query.count(100);
        QueryResult result = twitter.search(query);


        List<Status> tweets = result.getTweets();
        return tweets;
//
//        List<String> tweetsString = new ArrayList<>();
//
//        for (Status status : tweets) {
//            TweetsEntity tweetsEntity = new TweetsEntity();
//            BotRequest request = new BotRequest();
//            request.setMessage("Sentiment analysis of tweet:\n\n"+status.getText());
//
//            log.info("request: "+request.getMessage());
//            ChatGptResponse response = botService.askQuestion(request);
//
//            String sentiment = response.getChoices().get(0).getText();
//
//            tweetsEntity.setTweet(sentiment);
//            tweetsString.add(response.getChoices().get(0).getText());
//            tweetsDao.save(tweetsEntity);
//            log.info("tweet: "+status.getText());
//            log.info("analyze: "+ response);
//        }
//
//        return tweetsString;
    }




}
