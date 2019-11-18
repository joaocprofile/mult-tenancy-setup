package br.com.sigdata.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.sigdata.tenancy.TenancyInterceptor;

@Configuration
public class WebAPIConfig implements WebMvcConfigurer {
	
	 @Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(new TenancyInterceptor());
		}

}
