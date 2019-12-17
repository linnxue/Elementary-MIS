package com.xl.infomation.domain;

//成绩类
public class Score {
    //成绩id
    private Integer id;
    //学生id
    private Integer sid;
    //课程id
    private Integer cid;
    //成绩
    private Integer score;

    public Score(){

    }

    public Score(Integer id, Integer sid, Integer cid, Integer score) {
        this.id = id;
        this.sid = sid;
        this.cid = cid;
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", sid=" + sid +
                ", cid=" + cid +
                ", score=" + score +
                '}';
    }
}
