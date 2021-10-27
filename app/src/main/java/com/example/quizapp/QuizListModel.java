package com.example.quizapp;

import com.google.firebase.firestore.DocumentId;

public class QuizListModel {
    //@DocumentId
    private String name, desc, imgae, level, visibility;
    private long questions;
    private String quiz_id;

    public QuizListModel() {
    }

    public QuizListModel(String name, String desc, String imgae, String level, String visibility, long questions, String quiz_id) {
        this.name = name;
        this.desc = desc;
        this.imgae = imgae;
        this.level = level;
        this.visibility = visibility;
        this.questions = questions;
        this.quiz_id = quiz_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImgae() {
        return imgae;
    }

    public void setImgae(String imgae) {
        this.imgae = imgae;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public long getQuestions() {
        return questions;
    }

    public void setQuestions(long questions) {
        this.questions = questions;
    }

    public String getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(String quiz_id) {
        this.quiz_id = quiz_id;
    }
}
