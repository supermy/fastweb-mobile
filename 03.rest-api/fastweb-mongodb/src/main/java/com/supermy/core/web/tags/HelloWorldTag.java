package com.supermy.core.web.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

//标签处理类，继承 SimpleTagSupport 父类
public class HelloWorldTag extends SimpleTagSupport 
{ 
 // 重写 doTag 方法，该方法在标签结束生成页面内容
 public void doTag()throws JspException, 
     IOException 
 { 
     // 获取页面输出流，并输出字符串
     getJspContext().getOut().write("Hello World"); 
 } 
} 

