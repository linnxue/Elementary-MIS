package com.xl.infomation.servlet;

import com.xl.infomation.common.Manager;
import com.xl.infomation.common.StudentManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/StudentServlet")
public class StudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("StudentServlet.doPost");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("StudentServlet.doGet");

        //获得当前资源的根目录
        String filePath = getServletContext().getRealPath("/");

        //拼接文件名
        filePath += "/StudentTemplet.html";
//        System.out.println("StudentFilePath:"+filePath);


        //读取文件内容并替换
        String string = StudentManager.replaceStudentContent(filePath);

        //设置当前请求对象的编码方式
        request.setCharacterEncoding("utf-8");

        //设置响应数据类型的编码
        response.setContentType("text/html;charset=utf-8");

        //response.getWriter()---字符流
        //response.getOutputStream()---字节流

        response.getOutputStream().write(string.getBytes());

//     response.sendRedirect("StudentTemplet.html");


    }
}
