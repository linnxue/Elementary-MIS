package com.xl.infomation.common;

import com.xl.infomation.dao.CourseDao;
import com.xl.infomation.domain.Course;

import java.util.*;

public class Test {

    //查数据库获取数据
    private static ArrayList<Course> courseList(String pageIndex) {
        String sql = "select id,name,major,score from course limit ?,?";
        int curPage = Integer.parseInt(pageIndex);
        int startIndex = curPage * RequestType.perPageSize;
        return QFDatabase.selectFromTable(sql, Course.class, startIndex, RequestType.perPageSize);
    }
    public static String replaceUpdateCourse(Course c, String filePath) {
        StringBuffer buffer = QFFileReader.readFile(filePath);
        StringBuffer sourceBuffer = new StringBuffer(buffer);
        Map<String, String> map = new HashMap<>();
        map.put("${id}", c.getId() + "");
        map.put("${name}", c.getName());
        map.put("${major}", c.getMajor());
        map.put("${score}", c.getScore() + "");
        Set<String> set = map.keySet();
        Iterator<String> iterator = set.iterator();
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
    private static String replaceCourseList(Course c, String source) {
        StringBuffer sourceBuffer = new StringBuffer(source);
        String findStr = "${id}";
        int index = sourceBuffer.indexOf(findStr);
        if (index > 0) {
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
        findStr = "${value1}";
        index = sourceBuffer.indexOf(findStr);
        if (index > 0) {
            sourceBuffer.replace(index, index + findStr.length(), c.getId() + "");
        }
        findStr = "${value2}";
        index = sourceBuffer.indexOf(findStr);
        if (index > 0) {
            sourceBuffer.replace(index, index + findStr.length(), c.getId() + "");
        }
        System.out.println("Manager.replaceCourse" + sourceBuffer.toString());
        return sourceBuffer.toString();
    }
    public static String replaceCourseContent(String filePath, String pageIndex) {
        StringBuffer buffer = QFFileReader.readFile(filePath);
        String startStr = "${LoopStart}";
        String endStr = "${LoopEnd}";
        int startIndex = buffer.indexOf(startStr);
        int endIndex = buffer.indexOf(endStr);
        StringBuffer loopBuffer = new StringBuffer();
        if (startIndex < endIndex) {
            String str = buffer.substring(startIndex + startStr.length(), endIndex);
            ArrayList<Course> list = courseList(pageIndex);
            for (Course c : list) {
                String courseStr = replaceCourseList(c, str);
                loopBuffer.append(courseStr);
                System.out.println(loopBuffer);
            }
        }
        buffer.replace(startIndex, endIndex + endStr.length(), loopBuffer.toString());
        System.out.println(buffer.toString());
        buffer = replacePage(buffer, pageIndex);
        return buffer.toString();
    }
    public static StringBuffer replacePage(StringBuffer buffer, String pageIndex) {
        int nextpage = Integer.parseInt(pageIndex) + 1;
        int prepage = Integer.parseInt(pageIndex) - 1;
        if (prepage < 0) {
            prepage = 0;
        }
        int count = CourseDao.count();
        int lastPage = 0;
        if (count % RequestType.perPageSize == 0) {
            lastPage = count / RequestType.perPageSize - 1;
        } else {
            lastPage = count / RequestType.perPageSize;
        }
        if (nextpage > lastPage) {
            nextpage = lastPage;
        }
        Map<String, String> map = new HashMap<>();
        map.put("${nextpage}", nextpage + "");
        map.put("${prepage}", prepage + "");
        map.put("${lastpage}", lastPage + "");
        Set<String> set = map.keySet();
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = map.get(key);
            int index = buffer.indexOf(key);
            if (index > 0) {
                buffer.replace(index, index + key.length(), value);
            }
        }
        return buffer;
    }
}
