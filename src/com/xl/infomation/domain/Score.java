package com.xl.infomation.domain;

//成绩类
public class Score {
    //成绩id
    private int id;
    //学生id
    private int sid;
    //课程id
    private int cid;
    //成绩
    private int score;

    public Score(){
        this(0,0,0);
    }
    public Score(int sid, int cid, int score) {
        this.sid = sid;
        this.cid = cid;
        this.score = score;
    }
    @Override
    public String toString(){
        String str = "insert into score (sid,cid,score) values (";
        str +=  this.sid+",";
        str += this.cid+ ",";
        str += this.score + ");";

        return str;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
