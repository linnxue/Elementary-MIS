package com.xl.infomation.common;

import com.xl.infomation.domain.Course;

import java.util.*;

public class Manager {

    private static ArrayList<Course> courseList() {
        String sql = "select id,name,major,score from course";
        return QFDatabase.selectFromTable(sql, Course.class);
    }

    //替换修改页模板
    public static String replaceUpdateCourse(Course c, String filePath) {

        //获得课程模板的全路径 filePath
        //读取课程模板的内容
        StringBuffer buffer = QFFileReader.readFile(filePath);


        StringBuffer sourceBuffer = new StringBuffer(buffer);

        Map<String, String> map = new HashMap<>();

        //保存要替换的值
        map.put("${id}", c.getId() + "");
        map.put("${name}", c.getName());
        map.put("${major}", c.getMajor());
        map.put("${score}", c.getScore() + "");

//        map.put("${value1}", c.getId() + "");
//        map.put("${value2}", c.getId() + "");

//        map.forEach((key, value) -> {
//            int index = sourceBuffer.indexOf(key);
//            sourceBuffer.replace(index, index + key.length(), value);
//
//        });

        //获得所有key的集合
        Set<String> set = map.keySet();

        //获取迭代器
        Iterator<String> iterator = set.iterator();

        //遍历所有key
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = map.get(key);
            int index = sourceBuffer.indexOf(key);
            if (index > 0) {
                sourceBuffer.replace(index, index + key.length(), value);
            }
        }
        return sourceBuffer.toString();
    }

    //替换一个对象的数据到原字符串中------替换列表页模板
    private static String replaceCourseList(Course c, String source) {

        StringBuffer sourceBuffer = new StringBuffer(source);

        String findStr = "${id}";
        int index = sourceBuffer.indexOf(findStr);
        if (index > 0) {
//            sourceBuffer.replace(index,index+findStr.length(),String.valueOf(c.getId()));
            sourceBuffer.replace(index, index + findStr.length(), c.getId() + "");
        }

        findStr = "${name}";
        index = sourceBuffer.indexOf(findStr);
        if (index > 0) {
            sourceBuffer.replace(index, index + findStr.length(), c.getName());
        }

        findStr = "${major}";
        index = sourceBuffer.indexOf(findStr);
        if (index > 0) {
            sourceBuffer.replace(index, index + findStr.length(), c.getMajor());
        }

        findStr = "${score}";
        index = sourceBuffer.indexOf(findStr);
        if (index > 0) {
            sourceBuffer.replace(index, index + findStr.length(), c.getScore() + "");
        }

        //替换删除id
        findStr = "${value1}";
        index = sourceBuffer.indexOf(findStr);
        if (index > 0) {
            sourceBuffer.replace(index, index + findStr.length(), c.getId() + "");
        }
        //替换修改id
        findStr = "${value2}";
        index = sourceBuffer.indexOf(findStr);
        if (index > 0) {
            sourceBuffer.replace(index, index + findStr.length(), c.getId() + "");
        }

        System.out.println("Manager.replaceCourse" + sourceBuffer.toString());

        return sourceBuffer.toString();

    }

    //替换课程内容的实际数据到html文件中（CourseTemplet.html）
    public static String replaceCourseContent(String filePath) {

        //获得课程模板的全路径 filePath
        //读取课程模板的内容
        StringBuffer buffer = QFFileReader.readFile(filePath);

        String startStr = "${LoopStart}";

        String endStr = "${LoopEnd}";

        int startIndex = buffer.indexOf(startStr);

        int endIndex = buffer.indexOf(endStr);

        StringBuffer loopBuffer = new StringBuffer();

        if (startIndex < endIndex) {
            String str = buffer.substring(startIndex + startStr.length(), endIndex);

            ArrayList<Course> list = courseList();
            for (Course c : list) {
                String courseStr = replaceCourseList(c, str);
//                System.out.println(c);

                loopBuffer.append(courseStr);
                System.out.println(loopBuffer);

            }
        }

        buffer.replace(startIndex, endIndex + endStr.length(), loopBuffer.toString());

        System.out.println(buffer.toString());

        return buffer.toString();

    }

}
