package com.xl.infomation.common;

import com.xl.infomation.dao.CourseDao;
import com.xl.infomation.domain.Course;

import java.util.*;

public class Manager {

    private static ArrayList<Course> courseList(String pageIndex) {
        String sql = "select id,name,major,score from course limit ?,?";

        int curPage = Integer.parseInt(pageIndex);

        int startIndex = curPage*RequestType.perPageSize;

        return QFDatabase.selectFromTable(sql, Course.class,startIndex,RequestType.perPageSize);
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
    public static String replaceCourseContent(String filePath,String pageIndex) {

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

            ArrayList<Course> list = courseList(pageIndex);
            for (Course c : list) {
                String courseStr = replaceCourseList(c, str);
//                System.out.println(c);

                loopBuffer.append(courseStr);
                System.out.println(loopBuffer);

            }
        }

        buffer.replace(startIndex, endIndex + endStr.length(), loopBuffer.toString());

        System.out.println(buffer.toString());

        //完成页码的替换

        buffer = replacePage(buffer,pageIndex);

        return buffer.toString();

    }

    //完成页码的替换 辅助方法
    public static StringBuffer replacePage(StringBuffer buffer,String pageIndex){

        int nextpage = Integer.parseInt(pageIndex)+1;
        int prepage = Integer.parseInt(pageIndex)-1;
        if(prepage<0){
            prepage=0;
        }

        int count = CourseDao.count();
        int lastPage= 0;
        if(count%RequestType.perPageSize==0){
            lastPage =count/RequestType.perPageSize-1;
        }
        else{
            lastPage=count/RequestType.perPageSize;
        }
        if(nextpage>lastPage){
            nextpage=lastPage;
        }

        //创建map对象
        Map<String,String> map = new HashMap<>();

        map.put("${nextpage}", nextpage+"");
        map.put("${prepage}", prepage+"");
        map.put("${lastpage}", lastPage+"");


        //获得所有的key的集合
        Set<String> set = map.keySet();
        //获得迭代器
        Iterator<String> iterator = set.iterator();
        //遍历所有key的集合
        while (iterator.hasNext()){
            //获得每一个key
            String key = iterator.next();
            //获得和这个key对应的值
            String value = map.get(key);

            //查找将标记替换成真正的数据
            int index = buffer.indexOf(key);
            if(index>0){
                buffer.replace(index, index+key.length(), value);
            }
        }
        return buffer;

    }

}
