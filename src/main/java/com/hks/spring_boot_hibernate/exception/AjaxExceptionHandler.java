package com.hks.spring_boot_hibernate.exception;


import com.hks.spring_boot_hibernate.utils.JSONResultUtils;

import javax.servlet.http.HttpServletRequest;

//@RestControllerAdvice
public class AjaxExceptionHandler {

//	@ExceptionHandler(value = Exception.class)
	public JSONResultUtils defaultErrorHandler(HttpServletRequest req,
											   Exception e) throws Exception {

		e.printStackTrace();
		return JSONResultUtils.errorException(e.getMessage());
	}
}
