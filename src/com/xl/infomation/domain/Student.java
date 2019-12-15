package com.xl.infomation.domain;

import java.util.Random;

public class Student {
    //学生名
    private String name;
    //学生id
    private int id;
    //学生地址
    private String address;
    //学生年龄
    private int age;
    //学生性别
    private char sex;
    //专业
    private String major;

    public Student(){
        this("","",0,'男',0);
    }
    public static String majorString(){
        Random r = new Random();
        String[] majorArray = {"Computer","English","Math","Article","Sport"};
        return majorArray[r.nextInt(majorArray.length)];
    }

    public Student(String name, String address, int age, char sex, int id) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.age = age;
        this.sex = sex;
        this.major = majorString();

    }


    public void courseToFile(){
        Random random = new Random();
        //int count = random.nextInt()

    }
    @Override
    //通过重写toString,把当前对象内容转成插入语句
    public String toString(){
        // insert into student (id,name,age,sex,address) values (1,"a",6,'男',"ttt");
        String str = "insert into student (name,age,sex,address,major) values (";
        //str += this.id+",";
        str += "\""+ this.name+"\",";
        str += this.age+",";
        str += "\'"+ this.sex+"\',";
        str += "\"" +this.address+ "\",";
        str += "\"" +this.major+ "\"" + ");";

        return str;

    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }
}
