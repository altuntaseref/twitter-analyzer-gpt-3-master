package com.spring.tweetanalyzergpt3.service.concretes;

import com.spring.tweetanalyzergpt3.model.BotRequest;
import com.spring.tweetanalyzergpt3.model.ChatGptResponse;
import com.spring.tweetanalyzergpt3.model.SentimentAnalysisModel;
import com.spring.tweetanalyzergpt3.service.abstracts.RecommendationService;
import com.spring.tweetanalyzergpt3.service.abstracts.SentimentAnalysisService;
import com.spring.tweetanalyzergpt3.utils.SentimentsSeparatorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.TwitterException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class SentimentAnalysisServiceImpl implements SentimentAnalysisService {
    @Autowired
    private TwitterServiceImpl twitterService;
    @Autowired
    private GPTServiceImpl gptService;

    @Override
    public Map<String, Double> analyzeTweet(String tweet) throws TwitterException {
        Map<String, List<String>> analyzedResponses = fetchAndInquireTweet(tweet);

        Map<String, Double> separatedResponse = separeteSentiments(analyzedResponses.get("analyzedResponses"));
        return separatedResponse;
    }

    @Override
    public Map<String, List<String>> fetchAndInquireTweet(String tweetl) throws TwitterException {

        Map<String, List<String>> categorizedTweets = new HashMap<>();

        List<String> getRecentTweets = twitterService.getRecentTweets(tweetl);
        List<String> analyzedResponses = new ArrayList<>();

        List<String> positiveTweets = new ArrayList<>();
        List<String> negativeTweets = new ArrayList<>();
        List<String> neutraTweets = new ArrayList<>();


        for (String getRecentTweet : getRecentTweets) {

            BotRequest request = new BotRequest();
            request.setMessage("Sentiment analysis of tweet:\n\n" + getRecentTweet);

            ChatGptResponse chatGptResponse = gptService.askQuestion(request);

            String response = chatGptResponse.getChoices().get(0).getText();
            String analyzedResponse = SentimentsSeparatorUtils.separateSentiment(response);

            switch (analyzedResponse) {
                case "positive":
                    positiveTweets.add(getRecentTweet);
                    break;
                case "negative":
                    negativeTweets.add(getRecentTweet);
                    break;
                case "neutral":
                    neutraTweets.add(getRecentTweet);
                    break;
                default:
                    break;
            }

            analyzedResponses.add(analyzedResponse);

        }
//        String positiveAnalyze = recommendationService.positiveTweetPropsal(positiveTweets);
//        String negativeAnalyze = recommendationService.negativeTweetPropsal(negativeTweets);
//        String neutralAnalyze = recommendationService.neutralTweetPropsal(neutraTweets);

        categorizedTweets.put("analyzedResponses",analyzedResponses);
        categorizedTweets.put("positiveTweets",positiveTweets);
        categorizedTweets.put("negativeTweets",negativeTweets);
        categorizedTweets.put("neutraTweets",neutraTweets);


        return categorizedTweets;
    }

    @Override
    public Map<String, Double> separeteSentiments(List<String> tweetList) {
        int positiveCount = 0;
        int negativeCount = 0;
        int neutralCount = 0;
        for (String tweet : tweetList) {
            switch (tweet) {
                case "positive":
                    positiveCount++;
                    break;
                case "negative":
                    negativeCount++;
                    break;
                case "neutral":
                    neutralCount++;
                    break;
                default:
                    break;
            }
        }
        int totalCount = positiveCount + negativeCount + neutralCount;
        double positivePercentage = (double) positiveCount / totalCount * 100;
        double negativePercentage = (double) negativeCount / totalCount * 100;
        double neutralPercentage = (double) neutralCount / totalCount * 100;

        Map<String, Double> result = new HashMap<>();
        result.put("positive", positivePercentage);
        result.put("negative", negativePercentage);
        result.put("neutral", neutralPercentage);
        return result;
    }


}
