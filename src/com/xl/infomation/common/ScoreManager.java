/*
package com.xl.infomation.common;

import com.xl.infomation.domain.Course;

import java.util.ArrayList;

public class ScoreManager {

    private static ArrayList<Course> courseList() {
        String sql = "select id,name,major,score from course";
        return QFDatabase.selectFromTable(sql, Course.class);
    }

    //替换一个对象的数据到原字符串中
    private static String replaceCourse(Course c, String source) {

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

        System.out.println("Manager.replaceCourse"+sourceBuffer.toString());

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
            for (Course c : list){
                String courseStr = replaceCourse(c,str);
//                System.out.println(c);

                loopBuffer.append(courseStr);
                System.out.println(loopBuffer);

            }
        }

        buffer.replace(startIndex,endIndex+endStr.length(),loopBuffer.toString());

        System.out.println(buffer.toString());

        return buffer.toString();

    }

}
*/
