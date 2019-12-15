package com.xl.infomation.common;

//自定义的请求类型，类似接口说明
public class RequestType {

    //格式：   请求的html文件或servlet文件 ？ 参数名 = 参数值
    //例如：   CourseServlet?add=1

    //用来标识 添加 请求------type参数对应的四选一的值
   /* public static final int add = 1;
    //删除
    public static final int delete = 2;
    //搜索
    public static final int search = 3;
    //更新（修改）
    public static final int update = 4;*/

    //增删改查的参数名------请求的参数名  type参数对应的四选一的值
    public static final String addStr = "add";
    public static final String deleteStr = "delete";
    public static final String searchStr = "search";
    public static final String updateStr = "update";
    public static final String saveStr = "save";

    //请求的功能类型参数名 ：对应 增删改查
    public static final String type = "type";

    //id参数名 --- 参数值为相应表中的id号
    //学生id 在请求中的参数名
    public static final String studentid = "studentid";
    //课程id 在请求中的参数名
    public static final String courseid = "courseid";
    //分数id 在请求中的参数名
    public static final String scoreid = "scoreid";

}
