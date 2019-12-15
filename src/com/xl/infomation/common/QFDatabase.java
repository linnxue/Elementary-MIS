package com.xl.infomation.common;//设计一个用来方便操作数据库的类

import com.xl.infomation.domain.Student;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;

//声明抽象类 并包含泛型参数
public class QFDatabase<E> {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/java1904?useSSL=true&characterEncoding=utf8";
    private static String userName = "root";
    private static String password = "123456";

    //声明连接类的变量(引用)
//    private static Connection connection;
    //注册驱动，只执行一次，所以我们选择使用静态代码块
    static {
        try {
            System.out.println("com.xl.infomation.common.QFDatabase.static initializer");
            //注册驱动
            Class.forName(driver);

            //连接数据库
//            connection();

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Connection connection() {
        try {

            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println(connection != null ? "连接成功" : "连接失败");
            return connection;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    //关闭资源
    public static void close(Connection c, Statement s, ResultSet r) {
        try {
            if (r != null) {
                r.close();
            }
            if (s != null) {
                s.close();
            }
            if (c != null) {
                c.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {

        }
    }

    //绑定预处理的参数
    private static void setParams(PreparedStatement ps, Object... params) {
        if (ps == null || null == params) {
            return;
        }

        try {
            int i = 1;
            for (Object o : params) {
                ps.setObject(i++, o);
            }
        } catch (SQLException e) {
            System.out.println("com.xl.infomation.common.QFDatabase.setParams" + e.getMessage());
        }
    }

    //用来执行sql语句,声明包含一个可变参的方法,可变参数好比数组
    public static int executeUpdate(String sql, Object... params) {

        Connection connection = connection();
        PreparedStatement ps = null;
        try {
            if (connection != null) {
                ps = connection.prepareStatement(sql);
                setParams(ps, params);
                int resultState = ps.executeUpdate();
                return resultState;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            //关闭资源
            close(connection, ps, null);
        }
        return -1;
    }

   /* //查询行数
    public static int executeUpdate(String sql){

        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            if(connection!=null){
                ps = connection.prepareStatement(sql);
                rs = ps.executeQuery();
                rs.next();
                int resultState = rs.getInt(1);
                return resultState;
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        finally {
            //关闭资源
            close(null, ps, null);
        }
        return -1;
    }*/

    public static boolean isExists(String sql, Object... params) {

        Connection connection = connection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            if (connection != null) {
                ps = connection.prepareStatement(sql);
                setParams(ps, params);
                rs = ps.executeQuery();
                while (rs.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            //关闭资源
            close(connection, ps, rs);
        }
        return false;
    }

    //查询指定表中满足条件的记录条数
    //写查询语句时，类似这样，select count（1）from 表名 where ??
    public static int count(String sql, Object... params) {

        Connection connection = connection();
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            if (connection != null) {
                ps = connection.prepareStatement(sql);
                setParams(ps, params);
                rs = ps.executeQuery();

                while (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            //关闭资源
            close(connection, ps, rs);
        }
        return -1;
    }

    //解析结果集
    //public abstract void parseResultSet(E element,ResultSet rs,ArrayList<E> list);
    public static <E> void parseResultSet(Class<E> eClass, ResultSet rs, ArrayList<E> list) {

        try {
            //从当前结果集中获得所有的字段名信息
            ResultSetMetaData metaData = rs.getMetaData();
            while (rs.next()) {
                //com.xl.infomation.domain.Student s = newInstance();
                //创建一个新的E类型的对象
                E newInstance = eClass.newInstance();
                // E  e = new E();
                //遍历所有的字段名
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    //获得第i个字段的名字，从1开始
                    String lableName = metaData.getColumnLabel(i + 1);
                    //获得第i个字段的值
                    Object value = rs.getObject(lableName);
                    //System.out.println("com.xl.infomation.common.QFDatabase.parseResultSet:" + lableName);
                    try {
                        //根据类的类型和属性名，获得它的一个属性
                        PropertyDescriptor pd = new PropertyDescriptor(lableName, eClass);
                        if (pd != null) {
                            //获得当前属性的setter方法
                            Method method = pd.getWriteMethod();
                            //调用当前setter方法，保存对象的当前属性值
                            method.invoke(newInstance, value);
                            //获得当前属性的getter方法
                            //pd.getReadMethod()
                        }
                    } catch (IntrospectionException e) {
                        //e.printStackTrace();
                        System.out.println("com.xl.infomation.common.QFDatabase.parseResultSet:" + e.getMessage());
                        continue;
                    } catch (InvocationTargetException e) {
                        //e.printStackTrace();
                        System.out.println("com.xl.infomation.common.QFDatabase.parseResultSet:" + e.getMessage());
                        continue;
                    } finally {

                    }
                }
                //将新对象保存到数组中
                list.add(newInstance);
            }
        } catch (SQLException e) {

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static <E> ArrayList<E> selectFromTable(String sql, Class<E> eClass, Object... params) {

        PreparedStatement ps = null;
        Connection connection = connection();
        ResultSet rs = null;
        try {
            if (connection != null) {

                //创建用来保存结果的ArrayList对象
                ArrayList<E> list = new ArrayList<>();
                //获得PreparedStatement对象
                ps = connection.prepareStatement(sql);
                //绑定参数值(也就是sql字符串中的?的值)
                setParams(ps, params);
                //执行查询
                rs = ps.executeQuery();

                //解析结果集，此方法为抽象方法，需要子类实现
                parseResultSet(eClass, rs, list);
                return list;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close(connection, ps, rs);
        }
        return null;
    }

    public Student newInstance() {
        return new Student();
    }
    //public abstract int[] parseInsertData(ArrayList<E> list,PreparedStatement ps,Object... params);

    //批量插入
    //暂时未实现
    //传来的 参数是接口实现类的对象
    public int[] insertToTable(String sql, ArrayList<E> list, Object... params) {
        PreparedStatement ps = null;
        Connection connection = connection();
        int[] results = null;
        try {
            if (connection != null) {
                ps = connection.prepareStatement(sql);
                //设置不要自动提交
                connection.setAutoCommit(false);
                //由子类实现的抽象方法
                //results = parseInsertData(list,ps,params);
                //手动提交数据
                connection.commit();
            }
        } catch (SQLException e) {

            try {
                //如果批量插入发生异常,数据回滚，退回批量操作之前的状态
                connection.rollback();
            } catch (SQLException ee) {
                System.out.println(ee.getMessage());
            }
            System.out.println("com.xl.infomation.common.QFDatabase.insertToTable");
        } finally {
            close(connection, ps, null);
        }
        return results;
    }

}
