package com.aiplayer.learning.leetcode.client.model;

import java.util.List;

public class Problem {
    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public int getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(int seqNum) {
        this.seqNum = seqNum;
    }

    public List<Solution> getSolutionList() {
        return solutionList;
    }

    public void setSolutionList(List<Solution> solutionList) {
        this.solutionList = solutionList;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getQuestionUrl() {
        return questionUrl;
    }

    public void setQuestionUrl(String questionUrl) {
        this.questionUrl = questionUrl;
    }

    public static enum Level {
        Hard(3, "Hard"), Medium(2, "Medium"), Easy(1, "Easy");

        public final int level;
        public final String name;

        Level(int level, String name) {
            this.level = level;
            this.name = name;
        }

        public static Level fromLevel(int level) {
            for(Level l : Level.values()) {
                if(l.level == level) {
                    return l;
                }
            }
            return null;
        }
    }

    private Level level;
    private List<String> tags;
    private int seqNum;
    private String questionName;
    private String questionUrl;
    private List<Solution> solutionList;
}
