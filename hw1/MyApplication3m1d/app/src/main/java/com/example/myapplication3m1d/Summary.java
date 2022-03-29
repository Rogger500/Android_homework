package com.example.myapplication3m1d;

import android.os.Parcelable;

import java.io.Serializable;

public class Summary implements Serializable {
    private String difficulty;//难度
    private String title;//题目
    private String problemSolutionNum;//题解
    private String passRate;//通过率
    private String deteail;//详细信息

    public String getDeteail() {
        return deteail;
    }

    public void setDeteail(String deteail) {
        this.deteail = deteail;
    }

    public Summary(String difficulty, String title, String problemSolutionNum, String passRate, String deteail) {
        this.difficulty = difficulty;
        this.title = title;
        this.problemSolutionNum = problemSolutionNum;
        this.passRate = passRate;
        this.deteail = deteail;
    }

    public Summary(String difficulty, String title, String problemSolutionNum, String passRate) {
        this.difficulty = difficulty;
        this.title = title;
        this.problemSolutionNum = problemSolutionNum;
        this.passRate = passRate;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProblemSolutionNum() {
        return problemSolutionNum;
    }

    public void setProblemSolutionNum(String problemSolutionNum) {
        this.problemSolutionNum = problemSolutionNum;
    }

    public String getPassRate() {
        return passRate;
    }

    public void setPassRate(String passRate) {
        this.passRate = passRate;
    }
}
