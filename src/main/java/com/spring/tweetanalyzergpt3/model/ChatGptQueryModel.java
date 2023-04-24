package com.spring.tweetanalyzergpt3.model;

import lombok.Data;

@Data
public class ChatGptQueryModel {
    public String POSITIVE_PROPOSAL;
    public String NEGATIVE_PROPOSAL;
    public String NEUTRAL_PROPOSAL = "";

    public String positiveProposal(String tweet){
        this.POSITIVE_PROPOSAL = String.format( " What are the common characteristics of these comma-separated tweets related to %s with positive emotions, and how can we increase these positive features?",tweet);
        return this.POSITIVE_PROPOSAL;
    }

    public String negativeProposal(String tweet){
        this.NEGATIVE_PROPOSAL = String.format( "  What are the common characteristics of these comma-separated tweets related to %s with negative emotions, and how can we reduce these negative features?",tweet);
        return this.NEGATIVE_PROPOSAL;
    }

    public String neutralProposal(String tweet){
        this.POSITIVE_PROPOSAL = String.format( "  What are the common characteristics of these comma-separated tweets related to %s with neutral emotions, and and what can we do about it?",tweet);
        return this.POSITIVE_PROPOSAL;
    }
}
