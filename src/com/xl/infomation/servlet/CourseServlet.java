package com.xl.infomation.servlet;

import com.xl.infomation.common.RequestType;
import com.xl.infomation.service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/CourseServlet")
public class CourseServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CourseServlet.doPost");

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        String rootPath = getServletContext().getRealPath("/");
        String resultStr = "<html><head><body><h1> 出错了 </h1></body></head></html>";

        String type = request.getParameter(RequestType.type);
        System.out.println("CourseServlet.doPost" + type);

        String name = request.getParameter("name");
        String major = request.getParameter("major");
        String score = request.getParameter("score");
        String id = request.getParameter("id");

        switch (type.trim()) {//去掉字符串前后空格
            case RequestType.addStr:

                boolean success = CourseService.insertCourse(rootPath, name, major, score);
                if (success) {
                    response.sendRedirect("CourseAdd.html");
                } else {
                    //如果输入出错，返回并且提示
                    response.getOutputStream().println(resultStr);
                }
                break;
            case RequestType.searchStr:
                break;
            case RequestType.deleteStr:
                break;
            case RequestType.updateStr:
                break;
            case RequestType.saveStr:
                resultStr = CourseService.saveCourse(rootPath, name, major, score,id);

                response.getOutputStream().write(resultStr.getBytes());

                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CourseServlet.doGet");

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        String type = request.getParameter(RequestType.type);
        String id = request.getParameter("id");

        String rootPath = getServletContext().getRealPath("/");
        String resultStr = "<html><head><body><h1> 出错了 </h1></body></head></html>";

        switch (type.trim()) {//去掉字符串前后空格
            case RequestType.addStr:
                break;
            case RequestType.searchStr:
                break;

            case RequestType.deleteStr:
                resultStr = CourseService.deleteAndShowCourseList(rootPath, id);
                break;

                //处理更新请求
            case RequestType.updateStr:
                System.out.println("type" + type + ",id" + id);
                resultStr = CourseService.updateCourse(rootPath, id);
                break;
            default:
                System.out.println("CourseServlet.doGet+请求课程列表页");
                resultStr = CourseService.showCourseList(rootPath);
        }
        response.getOutputStream().write(resultStr.getBytes());
    }
}
