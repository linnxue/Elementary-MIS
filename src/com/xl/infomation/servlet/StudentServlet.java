package com.xl.infomation.servlet;

import com.xl.infomation.common.Replace;
import com.xl.infomation.common.RequestType;
import com.xl.infomation.service.StudentService;
import com.xl.infomation.service.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/StudentServlet")
public class StudentServlet extends HttpServlet {

    StudentService studentService = new StudentServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("StudentServlet.doPost");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("StudentServlet.doGet");


        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        String type = request.getParameter(RequestType.type);
        String id = request.getParameter("id");
        String page = request.getParameter("page");

        String curPage = "0";
        if (page != null) {
            curPage = page;
        }

        //获取当前资源所在的根路径
        String rootPath = getServletContext().getRealPath("/");
        String resultStr = "<html><head><body><h1> 出错了 </h1></body></head></html>";

        switch (type.trim()) {//去掉字符串前后空格
            case RequestType.addStr:
                break;
            case RequestType.searchStr:
                break;
            case RequestType.deleteStr:
                break;
            //处理更新请求
            case RequestType.updateStr:
                break;
            default:
                resultStr = Replace.ReplaceSelectStudentList(rootPath, curPage);
        }
        response.getOutputStream().write(resultStr.getBytes());

    }
}
