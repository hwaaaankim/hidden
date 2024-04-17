package com.dev.HiddenBATH.interceptor;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LogInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestURI = request.getRequestURI();
        if(!requestURI.contains("/front") 
        		&& !requestURI.contains("highLightTitle.png") 
        		&& !requestURI.contains("error")
        		&& !requestURI.contains("/administration")) {
            System.out.println(requestURI);
        	HttpSession session = request.getSession();
            int a = (int) (Math.random()*100);
    		if(request.getSession().getAttribute("user") == null) {
    			session.setAttribute("user", a);
    		}
    		return true;
        }
        
        return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler, 
			Exception ex)
			throws Exception {
	}

}
