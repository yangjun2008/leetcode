package com.aiplayer.learning.leetcode.client.model;

public class Solution {
    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public static enum Language {
        Java, CPlus, Python
    }

    private Language language;
    private boolean officialAnswer;
    private boolean bestAnswer;
    private int votes;
    private String titleName;
    private String content;

    public boolean isOfficialAnswer() {
        return officialAnswer;
    }

    public void setOfficialAnswer(boolean officialAnswer) {
        this.officialAnswer = officialAnswer;
    }

    public boolean isBestAnswer() {
        return bestAnswer;
    }

    public void setBestAnswer(boolean bestAnswer) {
        this.bestAnswer = bestAnswer;
    }

    public int getVotes() {
        return votes;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
