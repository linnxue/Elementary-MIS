package com.xl.infomation.service;

import com.xl.infomation.domain.Student;

import java.util.ArrayList;

public interface StudentService {

    //将传入的页码信息进行处理，然后根据结果处理数据库的信息；
    public ArrayList<Student> selectStudentList(String pageIndex);



}
