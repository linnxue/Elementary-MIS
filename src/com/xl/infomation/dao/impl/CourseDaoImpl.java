package com.xl.infomation.dao.impl;

import com.xl.infomation.common.QFDatabase;
import com.xl.infomation.dao.CourseDao;
import com.xl.infomation.domain.Course;

import java.util.ArrayList;

public class CourseDaoImpl implements CourseDao {

    //测试方法
    public static void main(String[] args) {
        CourseDaoImpl courseDao = new CourseDaoImpl();
        Course course = new Course();
        course.setMajor("aabb");
        course.setName("aabb");
        course.setScore(1);
        courseDao.addCourse(course);
    }

    @Override
    public ArrayList<Course> selectCourse(String sql) {
        return QFDatabase.selectFromTable(sql, Course.class);
    }

    @Override
    public int addCourse(Course course) {
        String sql = " insert into course (name ,major,score) value (?,?,?) ";
        if (!searchCourseByName(course.getName())) {
            return QFDatabase.executeUpdate(sql, course.getName(), course.getMajor(), course.getScore());
        }
        return -1;
    }

    @Override
    public boolean searchCourseByName(String name) {
        String sql = "select id from course where name = ?";
        return QFDatabase.isExists(sql, name);
    }

    @Override
    public int updateCourse(Course c) {
        String sql = "update course set name=?,major=?,score=? where id=?";
        return QFDatabase.executeUpdate(sql, c.getName(), c.getMajor(), c.getScore(), c.getId());

    }

}
