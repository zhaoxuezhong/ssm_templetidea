package com.zxz.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.zxz.utils.Constants;
import com.zxz.utils.CustomTimestampEditor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;


/**
 * BaseController
 * @author bdqn_hl
 * @date 2014-3-1
 */
public class BaseController {
	protected Logger logger = Logger.getLogger(BaseController.class);
	private Object currentUser;


	public Object getCurrentUser() {
		if(null == this.currentUser){
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			HttpSession session = request.getSession(false);
			if(null != session){
				currentUser = (Object)session.getAttribute(Constants.SESSION_USER);
			}else {
				currentUser = null;
			}
		}
		return currentUser;
	}

	public void setCurrentUser(Object currentUser) {
		this.currentUser = currentUser;
	}
	
	@InitBinder
	public void InitBinder(WebDataBinder dataBinder){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		dataBinder.registerCustomEditor(Timestamp.class, new CustomTimestampEditor(dateFormat, true));
	}
}
