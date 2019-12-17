package com.xl.infomation.domain;

//课程类
public class Course {
    //课程id
    private Integer id;
    //课程名
    private String name;
    //课程所属专业
    private String major;
    //学分
    private Integer score;

    public Course(){}

    public Course(Integer id, String name, String major, Integer score) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                ", score=" + score +
                '}';
    }
}
