package com.supermy.core.web.tags;

import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class QueryTag extends SimpleTagSupport
{
    //标签的属性
    private String driver;
    private String url;
    private String user;
    private String pass;
    private String sql;
    //执行数据库访问的对象 
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private ResultSetMetaData rsmd = null;
    //标签属性driver的setter方法
    public void setDriver(String driver) {
        this.driver = driver; 
    }
        //标签属性url的setter方法
    public void setUrl(String url) {
        this.url = url; 
    }
        //标签属性user的setter方法
    public void setUser(String user) {
        this.user = user; 
    }
        //标签属性pass的setter方法
    public void setPass(String pass) {
        this.pass = pass; 
    }
        //标签属性driver的getter方法
    public String getDriver() {
        return (this.driver); 
    }
        //标签属性url的getter方法
    public String getUrl() {
        return (this.url); 
    }
        //标签属性user的getter方法
    public String getUser() {
        return (this.user); 
    }
        //标签属性pass的getter方法
    public String getPass() {
        return (this.pass); 
    }
        //标签属性sql的getter方法
    public String getSql() {
        return (this.sql); 
    }
        //标签属性sql的setter方法
    public void setSql(String sql) {
        this.sql = sql; 
    }
    public void doTag()throws JspException,
        IOException
    {
           try
        {
            //注册驱动
            Class.forName(driver);
            //获取数据库连接
            conn = DriverManager.getConnection(url,user,pass);
            //创建Statement对象
            stmt = conn.createStatement();
            //执行查询
            rs = stmt.executeQuery(sql);
            rsmd = rs.getMetaData();
            //获取列数目
            int columnCount = rsmd.getColumnCount();
            //获取页面输出流
            Writer out = getJspContext().getOut();
            //在页面输出表格
            out.write("<table border='1' bgColor='9999cc' width='400'>");
            //遍历结果集
            while (rs.next())
            {
                out.write("<tr>");
                //逐列输出查询到的数据
                for (int i = 1 ; i <= columnCount ; i++ )
                {
                    out.write("<td>");
                    out.write(rs.getString(i));
                    out.write("</td>");
                }
                out.write("</tr>");
            }
        }
        catch(ClassNotFoundException cnfe)
        {
            cnfe.printStackTrace();
            throw new JspException("自定义标签错误" + cnfe.getMessage());
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new JspException("自定义标签错误" + ex.getMessage());
        }
        finally
        {
            //关闭结果集
            try
            {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            }
            catch (SQLException sqle)
            {
                sqle.printStackTrace();
            }
        }
    }
}

