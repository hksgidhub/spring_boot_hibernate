package com.hks.spring_boot_hibernate.controller.interceptor;

import com.hks.spring_boot_hibernate.utils.JSONResultUtils;
import com.hks.spring_boot_hibernate.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

//HandlerInterceptor 拦截器
public class TwoInterceptor implements HandlerInterceptor {

	final static Logger log = LoggerFactory.getLogger(TwoInterceptor.class);
	
	/**
	 * 在请求处理之前进行调用（Controller方法调用之前）
	 */
	@Override
	//HttpServletRequest 对象代表客户端的请求
	//HttpServletResponse 重定向（响应）
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		
		if (true) {
			//返回json给客户端
			returnErrorResponse(response, JSONResultUtils.errorMsg("被two拦截..."));
		}
		
		System.out.println("被two拦截...");
		
		return false;
	}
	
	/**
	 * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView mv)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void returnErrorResponse(HttpServletResponse response, JSONResultUtils result) throws IOException, UnsupportedEncodingException {
		OutputStream out=null;
		try{
		    response.setCharacterEncoding("utf-8");
		    response.setContentType("text/json");
		    out = response.getOutputStream();
		    //向response缓冲区中写入字节
		    out.write(JsonUtils.objectToJson(result).getBytes("utf-8"));
		    out.flush();
		} finally{
		    if(out!=null){
		        out.close();
		    }
		}
	}
}
