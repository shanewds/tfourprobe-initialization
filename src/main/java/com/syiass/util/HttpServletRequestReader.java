package com.syiass.util;

//读取HTTP请求的内容
import java.io.*;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpServletRequestReader
{



    public static String doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        StringBuilder stringBuilder = new StringBuilder();
        /*
            1. 设置字符编码格式
         */
        request.setCharacterEncoding("UTF-8");
        /*
            2. 获取请求方法
         */
        System.out.println("Method:"+request.getMethod());
        //3. 请求的URL/URI
        System.out.println("URL:"+request.getRequestURL() + " URI:"+ request.getRequestURI());
        //4. 请求的协议版本比如http1.1
        System.out.println("httpProtocolVersion:" + request.getProtocol());
        //5. 请求头字段名称
        @SuppressWarnings("unchecked")
        Enumeration<String> enumeration = (Enumeration<String>)request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String headerName = enumeration.nextElement();
            System.out.println("(Header) " + headerName + " : " + request.getHeader(headerName));
        }
        //6. 获取实体内容
        System.out.println("实体内容"+request.getInputStream());
        //7. 请求参数
        @SuppressWarnings("unchecked")
        Enumeration<String> parameterEnumeration = request.getParameterNames();
        while (parameterEnumeration.hasMoreElements()) {
            String parameterName = parameterEnumeration.nextElement();
            System.out.println("(parameter) "+parameterName + " : " + request.getParameter(parameterName));
            stringBuilder.append(parameterName+"="+request.getParameter(parameterName)+"&");

        }

        //去掉最后一个&符号
        return  stringBuilder.substring(0,stringBuilder.length()-1);

//        response.setContentType("text/html");
//        PrintWriter out = response.getWriter();
//        out.println("OK");
//        out.flush();
//        out.close();


    }







}

