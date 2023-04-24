package com.spring.tweetanalyzergpt3.controller;


import com.spring.tweetanalyzergpt3.config.client.TwitterClient;
import com.spring.tweetanalyzergpt3.service.concretes.RecommendationServiceImpl;
import com.spring.tweetanalyzergpt3.service.concretes.SentimentAnalysisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import twitter4j.TwitterException;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ApiController {

    @Autowired
    private RecommendationServiceImpl recommendationService ;

    @Autowired
    private SentimentAnalysisServiceImpl sentimentAnalysisService;
    @GetMapping("/tweetSearch")
    public ModelAndView searchTweet() throws TwitterException {
        ModelAndView model = new ModelAndView("search-tweet");
        return model;
    }


    @PostMapping("/searchTweets")
    public ModelAndView tweetSubmits(@RequestParam("tweetForm") String tweet, Model models) throws TwitterException {
        ModelAndView model = new ModelAndView("result");
        Map<String, Double> result = sentimentAnalysisService.analyzeTweet(tweet);
        Map<String, String> solutions = recommendationService.solutionProposal(tweet);

        model.addObject("positiveProposal", solutions.get("positiveProposal"));
        model.addObject("negativeProposal", solutions.get("negativeProposal"));
        model.addObject("neutralProposal", solutions.get("neutralProposal"));
        model.addObject("positivePercentage", result.get("positive"));
        model.addObject("negativePercentage", result.get("negative"));
        model.addObject("neutralPercentage", result.get("neutral"));
        return model;
    }




}
