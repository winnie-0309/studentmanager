package com.filter;

import com.exception.ApplicationException;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionFilter implements Filter {
    private static Logger logger = Logger.getLogger(ExceptionFilter.class);
    //跳转的错误信息页面
    private String errorPage;

    public void init(FilterConfig filterConfig) throws ServletException {
        //读取错误信息提示页面路径
        errorPage = filterConfig.getInitParameter("errorPage");
        if (null == errorPage || "".equals(errorPage)) {
            throw new RuntimeException("没有配置错误信息跳转页面,请再web.xml中进行配置\n<init-param>\n<param-name>errorPage</param-name>\n<param-value>/error.jsp</param-value>\n </init-param>\n路径可以是你自己设定的任何有效路径页面！！");
        }
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //捕获你抛出的业务异常
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            logger.error("过滤器异常:" + e.getMessage(), e);
            request.setAttribute("exception", new ApplicationException(e));//存储业务异常信息类
            request.getRequestDispatcher(errorPage).forward(request, response);//跳转到信息提示页面！！
        }
    }

    public void destroy() {

    }
}
