package com.xl.infomation.service;

import com.xl.infomation.common.Manager;
import com.xl.infomation.common.QFDatabase;
import com.xl.infomation.dao.CourseDao;
import com.xl.infomation.dao.impl.CourseDaoImpl;
import com.xl.infomation.domain.Course;

import java.util.ArrayList;

public class CourseService {

    public static String showCourseList(String rootPath,String pageIndex) {
        System.out.println("CourseService.showCourseList:显示所有的课程信息");
        //拼接文件名
        String filePath = rootPath + "/CourseTemplet.html";
        System.out.println("CourseFilePath:" + filePath);
        //读取文件内容并替换
        return Manager.replaceCourseContent(filePath,pageIndex);
    }

    //先删除指定的课程 然后再显示其余课程列表
    public static String deleteAndShowCourseList(String rootPath, String id) {
        if (id != null) {
            String sql = "delete from course where id = ?";
            int state = QFDatabase.executeUpdate(sql, Integer.parseInt(id));
            System.out.println("state:" + state);
        }
        return showCourseList(rootPath,"0");
    }

    //进入修改页
    public static String updateCourse(String rootPath, String id) {

        if (id != null) {
            String sql = "select id,name,major,score from course where id = ?";
            ArrayList<Course> courses = QFDatabase.selectFromTable(sql, Course.class, id);
            if (courses.size() == 1) {
                //拼接文件名
                String filePath = rootPath + "/CourseUpdate.html";
                System.out.println("CourseFilePath:" + filePath);
                //读取文件内容并替换
                return Manager.replaceUpdateCourse(courses.get(0), filePath);
            } else {
                System.out.println("CourseServlet.doGet：查询结果不唯一");
            }
        }
        return "<html><head><body><h1> 出错了 </h1></body></head></html>";
    }

    public static String saveCourse(String rootPath, String name, String major, String score, String id) {
        if (name == null || name.length() == 0) {
            System.out.println("用户名不能为空");
        } else if (major == null || major.length() == 0) {
            System.out.println("专业不能为空");
        } else if (score == null || score.length() == 0 || Integer.parseInt(score) == 0) {
            System.out.println("成绩不能为空");
        } else {
            Course c = new Course(name, major, Integer.parseInt(score), Integer.parseInt(id));
            CourseDao courseDao = new CourseDaoImpl();

            int success = courseDao.updateCourse(c);
            System.out.println(success);
            if (success > 0) {
                //回列表页
                return showCourseList(rootPath,"0");
            } else {
                System.out.println("CourseServlet.doPost:" + "添加失败");
            }
        }

        return "<html><head><body><h1> 出错了 </h1></body></head></html>";

    }

    public static boolean insertCourse(String rootPath, String name, String major, String score) {

        if (name == null || name.length() == 0) {
            System.out.println("用户名不能为空");
        } else if (major == null || major.length() == 0) {
            System.out.println("专业不能为空");
        } else if (score == null || score.length() == 0 || Integer.parseInt(score) == 0) {
            System.out.println("成绩不能为空");
        } else {
            Course c = new Course(name, major, Integer.parseInt(score), 0);
            CourseDao courseDao = new CourseDaoImpl();
            int success = courseDao.addCourse(c);
            System.out.println(success);
            if (success > 0) {
                return true;
            } else {
                System.out.println("CourseServlet.doPost:" + "添加失败");
            }
        }
        return false;
    }
}
