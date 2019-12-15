package com.xl.infomation.common;

import com.xl.infomation.domain.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudentManager {

    private static ArrayList<Student> studentList() {
        String sql = "select id,name,address,major from student";

        ArrayList<Student> students = QFDatabase.selectFromTable(sql, Student.class);

        System.out.println(students.size());

        return students;
    }

    //替换一个对象的数据到原字符串中
    private static String replaceStudent(Student s, String source) {

        StringBuffer sourceBuffer = new StringBuffer(source);

        Map<String, String> map = new HashMap<>();

        map.put("${id}",s.getId()+"");
        map.put("${name}",s.getName());
        map.put("${address}",s.getAddress());
        map.put("${major}",s.getMajor());

        map.forEach((key, value) -> {
            int index = sourceBuffer.indexOf(key);
            sourceBuffer.replace(index, index + key.length(), value);

        });

       /*
        String findStr = "${id}";
        int index = sourceBuffer.indexOf(findStr);
        if (index > 0) {
            sourceBuffer.replace(index, index + findStr.length(), s.getId() + "");
            System.out.println(sourceBuffer);
        }

        findStr = "${name}";
        index = sourceBuffer.indexOf(findStr);
        if (index > 0) {
            sourceBuffer.replace(index, index + findStr.length(), s.getName());
        }

        findStr = "${address}";
        index = sourceBuffer.indexOf(findStr);
        if (index > 0) {
            sourceBuffer.replace(index, index + findStr.length(), s.getAddress());
        }


        findStr = "${major}";
        index = sourceBuffer.indexOf(findStr);
        if (index > 0) {
            sourceBuffer.replace(index, index + findStr.length(), s.getMajor() + "");
        }

//        System.out.println("Manager.replaceStudent"+sourceBuffer.toString());*/

        return sourceBuffer.toString();

    }

    //替换课程内容的实际数据到html文件中（StudentTemplet.html）
    public static String replaceStudentContent(String filePath) {

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

            ArrayList<Student> list = studentList();
            for (Student s : list) {
                String studentStr = replaceStudent(s, str);
//                System.out.println(c);
                loopBuffer.append(studentStr);
//                System.out.println(loopBuffer);

            }
        }

        buffer.replace(startIndex, endIndex + endStr.length(), loopBuffer.toString());
//        System.out.println(buffer.toString());
        return buffer.toString();

    }

}
