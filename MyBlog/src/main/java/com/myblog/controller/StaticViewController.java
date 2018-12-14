package com.myblog.controller;
    
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
 
public class StaticViewController implements Controller {
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ctxRoot = request.getContextPath();
        String uri = request.getRequestURI();
        int len = 0;
 
        if(ctxRoot != null && ctxRoot.length() > 0) {
            len = uri.indexOf(ctxRoot) + ctxRoot.length();
        }
        uri = uri.substring(len);
        System.out.println("uri.split.length :" + uri.split("/").length);
        if(uri.split("/").length < 2) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        String page = uri.substring(1);

        
        String[] pageIdx = page.split("\\.");
        System.out.println(">>>"+pageIdx.length);
        System.out.println(">>>"+pageIdx[pageIdx.length-2]);
        
        return new ModelAndView(pageIdx[pageIdx.length-2]);
    }
}
