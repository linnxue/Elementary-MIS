package com.xl.infomation.domain;

//课程类
public class Course {
    //课程id
    private int id;
    //课程名
    private String name;
    //课程所属专业
    private String major;
    //学分
    private int score;

    public Course(){}

    public Course(String name, String major, int score, int id) {
        this.name = name;
        this.major = major;
        this.score = score;
        this.id = id;
    }

    @Override
    public String toString(){
        String str = "insert into course (name,major,score) values (";
        str += "\""+ this.name+"\",";
        str += "\"" +this.major+ "\",";
        str += this.score + ");";

        return str;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
