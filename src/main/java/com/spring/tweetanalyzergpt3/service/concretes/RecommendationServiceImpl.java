package com.spring.tweetanalyzergpt3.service.concretes;

import com.spring.tweetanalyzergpt3.model.BotRequest;
import com.spring.tweetanalyzergpt3.model.ChatGptQueryModel;
import com.spring.tweetanalyzergpt3.model.ChatGptResponse;
import com.spring.tweetanalyzergpt3.service.abstracts.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.TwitterException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    private GPTServiceImpl gptService;

    @Autowired
    private SentimentAnalysisServiceImpl sentimentAnalysisService;

    @Override
    public Map<String, String> solutionProposal(String tweet) throws TwitterException {
        Map<String, List<String>> analyzedResponse = sentimentAnalysisService.fetchAndInquireTweet(tweet);
        String positive = positiveTweetPropsal(analyzedResponse.get("positiveTweets"), tweet);
        String negative = negativeTweetPropsal(analyzedResponse.get("negativeTweets"), tweet);
        String neutral = neutralTweetPropsal(analyzedResponse.get("neutralTweets"), tweet);

        Map<String, String> proposalMap = new HashMap<>();
        proposalMap.put("positiveProposal", positive);
        proposalMap.put("negativeProposal", negative);
        proposalMap.put("neutralProposal", neutral);

        return proposalMap;
    }

    @Override
    public String positiveTweetPropsal(List<String> positiveTweets, String tweet) {
        ChatGptQueryModel chatGptQueryModel = new ChatGptQueryModel();
        String tweets = String.join(",", positiveTweets);
        String query = tweets + chatGptQueryModel.positiveProposal(tweet);
        BotRequest botRequest = new BotRequest();
        botRequest.setMessage(query);
        ChatGptResponse chatGptResponse = gptService.askQuestion(botRequest);
        String response = chatGptResponse.getChoices().get(0).getText();

        return response;
    }

    @Override
    public String negativeTweetPropsal(List<String> negativeTweets, String tweet) {
        ChatGptQueryModel chatGptQueryModel = new ChatGptQueryModel();
        String tweets = String.join(",", negativeTweets);
        String query = tweets + chatGptQueryModel.negativeProposal(tweet);
        BotRequest botRequest = new BotRequest();
        botRequest.setMessage(query);
        ChatGptResponse chatGptResponse = gptService.askQuestion(botRequest);
        String response = chatGptResponse.getChoices().get(0).getText();

        return response;
    }

    @Override
    public String neutralTweetPropsal(List<String> neutralTweets, String tweet) {
        ChatGptQueryModel chatGptQueryModel = new ChatGptQueryModel();
        if (neutralTweets != null) {
            String tweets = String.join(",", neutralTweets);
            String query = tweets + chatGptQueryModel.neutralProposal(tweet);
            BotRequest botRequest = new BotRequest();
            botRequest.setMessage(query);
            ChatGptResponse chatGptResponse = gptService.askQuestion(botRequest);
            String response = chatGptResponse.getChoices().get(0).getText();
            return response;
        } else {
            return null;
        }


    }
}
