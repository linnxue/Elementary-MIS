package com.xl.infomation.dao;

import com.xl.infomation.common.QFDatabase;
import com.xl.infomation.domain.Student;

import java.util.ArrayList;

public interface StudentDao {

    public ArrayList<Student> selectStudentList(String pageIndex, int startIndex);

    public  ArrayList<Student> studentList(String pageIndex);
//        String sql = "select id,name,major,score from course limit ?,?";
//
//        int curPage = Integer.parseInt(pageIndex);
//
//        int startIndex = curPage* RequestType.perPageSize;
//
//        return QFDatabase.selectFromTable(sql, Course.class,startIndex,RequestType.perPageSize);


    //添加一个学生信息
    public int addStudent(Student student);

    //根据学生名字查ID
    public boolean searchStudentByName(String name);

    //修改一个学生的信息
    public int updateStudent(Student student);

    //查询学生记录条数
    public static int count(){
        String sql = "select count(1) from student";
        return QFDatabase.count(sql);
    }

}
