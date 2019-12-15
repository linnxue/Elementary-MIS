package com.xl.infomation.common;

import java.io.*;

public class QFFileReader {
//    public static StringBuffer buffer;

    static {
//        buffer = new StringBuffer(1024*1024);
    }

    //读取指定文件的内容
    public static StringBuffer readFile(String filePath ){

        StringBuffer stringBuffer = new StringBuffer(1024*1024);

        try {

            //根据指定的文件路径，创建文件输入流对象
            FileInputStream inputStream = new FileInputStream(filePath);
            //创建输入流的读取对象
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            //创建缓冲区读取对象
            BufferedReader reader = new BufferedReader(streamReader);

            String line = null;
            //调用缓冲区对象的方法，一行一行读取文件内容

            stringBuffer.setLength(0);

            while ((line = reader.readLine())!=null){

                stringBuffer.append(line);

            }
            inputStream.close();



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuffer;
    }



}
