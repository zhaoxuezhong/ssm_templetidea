package com.zxz.interceptor;

import com.zxz.utils.Constants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SysInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,
							Object handler) throws Exception {  
	   //intercept  
	   HttpSession session = request.getSession();  
	   String urlPath = request.getServletPath();
        Object auUser =  ((Object)session.getAttribute(Constants.SESSION_USER));
	   
	   if(null == auUser){
	       session.setAttribute("error","对不起！你没有登陆不能访问该页面，请登录后访问！");
		   response.sendRedirect(request.getContextPath());
		   return false;
	   }else{
	       return true;
	   }
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}
}
