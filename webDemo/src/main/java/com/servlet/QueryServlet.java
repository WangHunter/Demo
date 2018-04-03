package com.servlet;

import com.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.SingleThreadModel;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/29.
 */
public class QueryServlet extends HttpServlet implements SingleThreadModel{

    private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(QueryServlet.class
            .getName());
    @Autowired
    private QueryService queryService;
    private static final long serialVersionUID = 1L;


    @Override
    public void init() throws ServletException {
        super.init();
        WebApplicationContext context = WebApplicationContextUtils
                .getRequiredWebApplicationContext(getServletContext());
        queryService = (QueryService) context
                .getBean("queryServiceImpl");
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        new ArrayList<String>();
        String getUserid = request.getParameter("userid");
        String getItemID = queryService.queryItemByUserid(getUserid);
        response.setHeader("Content-type", "text/html;charset=UTF-8");  //让用utf8来解析返回的数据
        PrintWriter out = response.getWriter();
        out.println(getItemID);
        out.flush();
        out.close();
    }
}
