package com.ringdingdong.ridingthewind.config;

import com.ringdingdong.ridingthewind.interceptor.ConfirmInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;


@Configuration
@MapperScan(basePackages = {"com.ringdingdong.**.mapper"})
public class WebMvcConfiguration implements WebMvcConfigurer {

	private final List<String> patterns = Arrays.asList("/article/*", "admin", "/user/list");
	
	private ConfirmInterceptor confirmInterceptor;
	
	public WebMvcConfiguration(ConfirmInterceptor confirmInterceptor) {
		this.confirmInterceptor = confirmInterceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(confirmInterceptor).addPathPatterns(patterns);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("*")
			//.allowedOrigins("http://ssafy.com", "http://localhost", "http://localhost:8080")
				.allowedMethods("GET", "POST", "UPDATE", "DELETE")
			//.allowedMethods(RequestMethod.GET.name(), RequestMethod.POST.name(), ...)
			//.allowedMethods("*")
			.maxAge(1800);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/").setViewName("index2");
//		registry.addViewController("/user/join").setViewName("user/join");
	}
	
//	@Override
//	public void configureViewResolvers(ViewResolverRegistry registry) {
//		registry.jsp("/WEB-INF/views/", "jsp");
//	}
	
}
