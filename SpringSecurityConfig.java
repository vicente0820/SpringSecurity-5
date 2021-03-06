package com.vicentecabrera.springboot.app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() { // crear metodo regsistrar nuedtro password encoder como por defecto en nuestra configuaraci/ 
		return new BCryptPasswordEncoder();
		
	}
	
	    @Autowired
		public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception  {
	    	
	    	PasswordEncoder encoder = passwordEncoder();
	    	UserBuilder users = User.builder().passwordEncoder(password ->{
	    		return encoder.encode(password);
	    	} );
	    	
	    	builder.inMemoryAuthentication()                                           // creamos los usuarios en memoria
	    	.withUser(users.username("admin").password("12345").roles("ADMIN","USER")) // colocamos el password literal sin encriptar el UserBuilder see encarga de codificar la clave/
	    	.withUser(users.username("vicente").password("12345").roles("USER"));                                                                          
			
		}

}
