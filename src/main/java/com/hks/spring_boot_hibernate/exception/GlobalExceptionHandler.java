package com.hks.spring_boot_hibernate.exception;

import com.hks.spring_boot_hibernate.utils.JSONResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 捕获全局异常显示友好页面
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	final static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	public static final String IMOOC_ERROR_VIEW = "error";

//	@ExceptionHandler(value = Exception.class)
//    public Object errorHandler(HttpServletRequest reqest, 
//    		HttpServletResponse response, Exception e) throws Exception {
//    	
//    	e.printStackTrace();
//    	
//		ModelAndView mav = new ModelAndView();
//        mav.addObject("exception", e);
//        mav.addObject("url", reqest.getRequestURL());
//        mav.setViewName(IMOOC_ERROR_VIEW);
//        return mav;
//    }
	
	@ExceptionHandler(value = Exception.class)
    public Object errorHandler(HttpServletRequest reqest,
                               HttpServletResponse response, Exception e) throws Exception {
    	
    	e.printStackTrace();

    	//是否是自定义异常类抛出的异常
//		if(e instanceof UserException){
//
//		}
    	
    	if (isAjax(reqest)) {
			log.error("日志记录发生错误, errorAjaxMessage: {}", e.getMessage());
    		return JSONResultUtils.errorException(e.getMessage());
    	} else {
    		ModelAndView mav = new ModelAndView();
            mav.addObject("exception", e);
            mav.addObject("url", reqest.getRequestURL());
            mav.setViewName(IMOOC_ERROR_VIEW);
			log.error("日志记录发生错误, errorWebMessage: {}", e.getMessage());
			log.error("日志记录发生错误, errorWebUrl: {}", reqest.getRequestURL());
			return mav;
    	}
    }
	
	/**
	 *
	 * @Description: 判断是否是ajax请求
	 * Copyright: Copyright (c) 2017
	 * Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
	 * @version V1.0
	 */
	public static boolean isAjax(HttpServletRequest httpRequest){
		return  (httpRequest.getHeader("X-Requested-With") != null  
					&& "XMLHttpRequest"
						.equals( httpRequest.getHeader("X-Requested-With").toString()) );
	}
}
