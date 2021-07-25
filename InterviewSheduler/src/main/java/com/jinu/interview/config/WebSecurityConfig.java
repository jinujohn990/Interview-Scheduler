package com.jinu.interview.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jinu.interview.serviceimpl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled  = true)
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{
	  @Bean
	    public UserDetailsService userDetailsService() {
	        return new UserDetailsServiceImpl();
	    }
	     
	   /*@Bean public BCryptPasswordEncoder passwordEncoder() { return new
	   BCryptPasswordEncoder(); }*/
	 
	    
	
	  @Bean public PasswordEncoder passwordEncoder() { return
	  NoOpPasswordEncoder.getInstance(); }
	 
	     
	    @Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(userDetailsService());
	        authProvider.setPasswordEncoder(passwordEncoder());
	        return authProvider;
	    }
	    
	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.authenticationProvider(authenticationProvider());
	    }
	    
	    
	    /*@Override
	    protected void configure(HttpSecurity http) throws Exception {
	    	   http.authorizeRequests()
	            .antMatchers("/").hasAnyAuthority("USER", "CREATOR", "EDITOR", "ADMIN")
	            .antMatchers("/new").hasAnyAuthority("ADMIN", "CREATOR")
	            .antMatchers("/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
	            .antMatchers("/admin/**").hasAuthority("ADMIN")
	            .anyRequest().authenticated()
	            .and()
	            .formLogin().permitAll()
	            .and()
	            .logout().permitAll()
	            .and()
	            .exceptionHandling().accessDeniedPage("/403");
	    }*/
	    
	    /*@Override
	    protected void configure(HttpSecurity http) throws Exception {
	    	   http.authorizeRequests()
	    	    .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
	            .antMatchers("/").hasAnyAuthority("ROLE_INTERVIEWER", "ROLE_ADMIN")
	            .anyRequest().authenticated()
	            .and()
	            .formLogin().permitAll()
	            .and()
	            .logout().permitAll()
	            .and()
	            .exceptionHandling()
	            .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.NEVER);
	    }*/
	    
	        protected void configure(HttpSecurity http) throws Exception{
	        	http.httpBasic().and().csrf().disable().authorizeRequests()
	        	 .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
		         .antMatchers("/").hasAnyAuthority("ROLE_INTERVIEWER", "ROLE_ADMIN")
	        	.anyRequest().authenticated().and()
	        	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	        	
	        }
}
