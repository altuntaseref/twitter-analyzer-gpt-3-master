package com.spring.tweetanalyzergpt3.service.abstracts;

import com.spring.tweetanalyzergpt3.model.BotRequest;
import com.spring.tweetanalyzergpt3.model.ChatGptResponse;
import org.springframework.stereotype.Service;

@Service
public interface GPTService {
    ChatGptResponse askQuestion(BotRequest botRequest);
}
