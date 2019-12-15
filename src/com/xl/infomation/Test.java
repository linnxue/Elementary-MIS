package com.xl.infomation;

import com.xl.infomation.domain.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {

        String source = null;

        StringBuffer sourceBuffer = new StringBuffer(source);

        Map<String,Student> map = new HashMap<>();

        String[] find = {"${id}", "${name}","${address}","${major}"};
        int index = sourceBuffer.indexOf(find[0]);


        Student s = new Student();

        for (int i = 0; i < 500; i++) {

            map.put(find[0],s);
            sourceBuffer.replace(index, index + find[0].length(), s.getId() + "");

            map.put(find[1],s);
            sourceBuffer.replace(index, index + find[1].length(), s.getName());

            map.put(find[2],s);
            sourceBuffer.replace(index, index + find[2].length(), s.getAddress());

            map.put(find[3],s);
            sourceBuffer.replace(index, index + find[3].length(), s.getMajor() + "");

        }










        String findStr = "${id}";

        //返回指定子字符串第一次出现的字符串内的索引。
//        int index = sourceBuffer.indexOf(findStr);

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




        System.out.println("Manager.replaceStudent"+sourceBuffer.toString());

        sourceBuffer.toString();

    }
}
