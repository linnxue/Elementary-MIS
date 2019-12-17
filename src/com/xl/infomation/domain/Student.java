package com.xl.infomation.domain;

import java.util.List;

public class Student {
    //学生名
    private String name;
    //学生id
    private Integer id;
    //学生地址
    private String address;
    //学生年龄
    private Integer age;
    //学生性别
    private String sex;
    //专业
    private String major;

    //课程
    private List<Course> courses;

    //分数
    private  List<Score> scores;

    public Student(){

    }

    public Student(String name, Integer id, String address, Integer age, String sex, String major, List<Course> courses, List<Score> scores) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.age = age;
        this.sex = sex;
        this.major = major;
        this.courses = courses;
        this.scores = scores;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }
}
