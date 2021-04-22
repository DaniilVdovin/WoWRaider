package com.daniilvdovin.wowraider.model;

public class MythicPluseProgressItem {
    public String
            type,
            score,
            tite;

    public MythicPluseProgressItem(String type, String score, String tite) {
        this.type = type;
        this.score = score;
        this.tite = tite;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTite() {
        return tite;
    }

    public void setTite(String tite) {
        this.tite = tite;
    }
}
