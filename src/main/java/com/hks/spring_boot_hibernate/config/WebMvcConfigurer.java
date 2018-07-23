package com.hks.spring_boot_hibernate.config;

import com.hks.spring_boot_hibernate.controller.interceptor.OneInterceptor;
import com.hks.spring_boot_hibernate.controller.interceptor.TwoInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//WebMvcConfigurerAdapter spring5.0已废弃
//1.解决 extends WebMvcConfigurationSupport
//2. implements WebMvcConfigurer
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurationSupport {

	@Override
    //重写WebMvcConfigurationSupport里的方法
	public void addInterceptors(InterceptorRegistry registry) {
		/**
		 * 拦截器按照顺序执行
		 */
        registry.addInterceptor(new OneInterceptor()).addPathPatterns("/one/**");
		registry.addInterceptor(new TwoInterceptor()).addPathPatterns("/two/**");//.addPathPatterns("/one/**");

		//registry.addResourceHandler("/sh/static/**").addResourceLocations("classpath:/static/");
		super.addInterceptors(registry);
	}

}
