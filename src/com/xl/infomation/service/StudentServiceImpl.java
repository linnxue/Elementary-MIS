package com.xl.infomation.service;

import com.xl.infomation.common.RequestType;
import com.xl.infomation.dao.StudentDao;
import com.xl.infomation.dao.impl.StudentDaoImpl;
import com.xl.infomation.domain.Student;

import java.util.ArrayList;

public class StudentServiceImpl implements StudentService {

    StudentDao studentDao = new StudentDaoImpl();

    //测试方法
    public static void main(String[] args) {
        StudentServiceImpl studentService = new StudentServiceImpl();
        ArrayList<Student> students = studentService.selectStudentList("0");
        for (Student s : students) {
            System.out.println(s.getId());

        }
    }

    @Override
    public ArrayList<Student> selectStudentList(String pageIndex) {
        int curPage = Integer.parseInt(pageIndex);
        int startIndex = curPage * RequestType.perPageSize;
        ArrayList<Student> students = studentDao.selectStudentList(pageIndex, startIndex);
        return students;
    }




}
