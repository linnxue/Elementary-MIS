package com.xl.infomation.common;

import com.xl.infomation.dao.StudentDao;
import com.xl.infomation.domain.Student;
import com.xl.infomation.service.StudentService;
import com.xl.infomation.service.StudentServiceImpl;

import java.util.*;

//获取根路径和当前页，用当前页信息获取数据库数据，用根路径获取html页面，然后将二者替换
public class Replace {

    StudentService studentService = new StudentServiceImpl();

    //完成主体内容的替换 辅助方法①
    private static String replaceStudentList(Student s, String source) {

        //获得课程模板的全路径 filePath
        //读取课程模板的内容

        StringBuffer sourceBuffer = new StringBuffer(source);

        Map<String, String> map = new HashMap<>();

        //保存要替换的值
        map.put("${id}", s.getId() + "");
        map.put("${name}", s.getName());
        map.put("${major}", s.getMajor());

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

    //完成页码的替换 辅助方法②
    public static StringBuffer replacePage(StringBuffer buffer, String pageIndex) {

        int nextpage = Integer.parseInt(pageIndex) + 1;
        int prepage = Integer.parseInt(pageIndex) - 1;
        if (prepage < 0) {
            prepage = 0;
        }

        int count = StudentDao.count();
        int lastPage = 0;
        if (count % RequestType.perPageSize == 0) {
            lastPage = count / RequestType.perPageSize - 1;
        } else {
            lastPage = count / RequestType.perPageSize;
        }
        if (nextpage > lastPage) {
            nextpage = lastPage;
        }

        //创建map对象
        Map<String, String> map = new HashMap<>();

        map.put("${nextpage}", nextpage + "");
        map.put("${prepage}", prepage + "");
        map.put("${lastpage}", lastPage + "");

        //获得所有的key的集合
        Set<String> set = map.keySet();
        //获得迭代器
        Iterator<String> iterator = set.iterator();
        //遍历所有key的集合
        while (iterator.hasNext()) {
            //获得每一个key
            String key = iterator.next();
            //获得和这个key对应的值
            String value = map.get(key);

            //查找将标记替换成真正的数据
            int index = buffer.indexOf(key);
            if (index > 0) {
                buffer.replace(index, index + key.length(), value);
            }
        }
        return buffer;

    }

    //将Service处理的结果，和html文件中对应的字符替换，然后返回结果)
    //controller传入根路径和当前页参数
    public static String ReplaceSelectStudentList(String rootPath, String curPage) {

        StudentService studentService = new StudentServiceImpl();

        //获取数据库数据（每页显示的数据）
        ArrayList<Student> students = studentService.selectStudentList(curPage);

        //拼接文件名
        String filePath = rootPath + "/StudentTemplet.html";

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

            //获取列表信息（数据库信息）
//            ArrayList<Course> list = courseList(pageIndex);
            for (Student s : students) {
                //完成主体信息的替换
                String studentStr = replaceStudentList(s, str);

                loopBuffer.append(studentStr);
                System.out.println(loopBuffer);
            }
        }

        buffer.replace(startIndex, endIndex + endStr.length(), loopBuffer.toString());

        System.out.println(buffer.toString());

        //完成页码的替换
        /**
         *
         */
        buffer = replacePage(buffer, curPage);

        return buffer.toString();

    }

}
