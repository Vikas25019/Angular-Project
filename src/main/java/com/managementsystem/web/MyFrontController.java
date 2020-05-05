package com.managementsystem.web;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyFrontController extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0] ;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        Class arr[] = {MvcConfig.class};
        return arr;
    }

    @Override
    protected String[] getServletMappings() {
        String arr[] = {"/"};
        return arr;
    }
}
