package com.xl.infomation.dao;

import com.xl.infomation.common.QFDatabase;
import com.xl.infomation.domain.Score;
import com.xl.infomation.domain.Student;

import java.util.ArrayList;

public interface ScoreDao {


    public ArrayList<Score> selectScore(String sql);

    //添加一个学生信息
    public int addScore(Score score);

    //根据学生名字查ID
    public boolean searchScoreByName(String name);

    //修改一个学生的信息
    public int updateScore(Score score);

    //查询学生记录条数
    public static int count(){
        String sql = "select count(1) from score";
        return QFDatabase.count(sql);
    }


}
