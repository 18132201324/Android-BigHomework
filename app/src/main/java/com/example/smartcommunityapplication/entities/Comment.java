package com.example.smartcommunityapplication.entities;

public class Comment {
    private String image;
    private String content;
    private int score;
    private String evaluatorName;
    private String time;

    public Comment(String image, String content, int score, String evaluatorName, String time) {
        this.image = image;
        this.content = content;
        this.score = score;
        this.evaluatorName = evaluatorName;
        this.time = time;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getEvaluatorName() {
        return evaluatorName;
    }

    public void setEvaluatorName(String evaluatorName) {
        this.evaluatorName = evaluatorName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "image='" + image + '\'' +
                ", content='" + content + '\'' +
                ", score=" + score +
                ", evaluatorName='" + evaluatorName + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
