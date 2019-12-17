package com.xl.infomation.dao.impl;

import com.xl.infomation.common.QFDatabase;
import com.xl.infomation.common.RequestType;
import com.xl.infomation.dao.StudentDao;
import com.xl.infomation.domain.Student;

import java.util.ArrayList;

public class StudentDaoImpl implements StudentDao {

    //测试方法
    public static void main(String[] args) {
        StudentDaoImpl studentDao = new StudentDaoImpl();
        ArrayList<Student> students = studentDao.selectStudentList("0", 3);
        for (Student s : students) {
            System.out.println(s.getId());
            System.out.println(s.getName());
            System.out.println(s.getMajor());
        }
    }

    @Override
    public ArrayList<Student> selectStudentList(String pageIndex, int startIndex) {

        String sql = "select id,name,major from student limit ?,?";
        ArrayList<Student> students = QFDatabase.selectFromTable(sql, Student.class, startIndex, RequestType.perPageSize);

        return students;
    }

    @Override
    public ArrayList<Student> studentList(String pageIndex) {
        return null;
    }

    @Override
    public int addStudent(Student student) {
        String sql = " insert into course (name ,major,score) value (?,?,?) ";
        if (!searchStudentByName(student.getName())) {
            return QFDatabase.executeUpdate(sql, student.getName(), student.getMajor());
        }
        return -1;
    }

    @Override
    public boolean searchStudentByName(String name) {
        String sql = "select id from student where name = ?";
        return QFDatabase.isExists(sql, name);
    }

    @Override
    public int updateStudent(Student student) {
        return 0;
    }
}
