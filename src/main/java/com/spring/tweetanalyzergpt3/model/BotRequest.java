package com.spring.tweetanalyzergpt3.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BotRequest  implements Serializable {
    private String message;

    private List<Tweet> tweets;
}
