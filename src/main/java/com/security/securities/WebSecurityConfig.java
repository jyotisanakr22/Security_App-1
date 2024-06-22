package com.security.securities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authProvider() {
	//"DAO" stands for data access object. Usually, the DAO class is responsible for two concepts: encapsulating the details of the persistence layer and providing a CRUD interface for a single entity.
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		
		provider.setUserDetailsService(userDetailsService);
		//provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {	//Right click-->>source-->>configure HttpSecurity 
		http
		.csrf().disable()
		.authorizeRequests().antMatchers("/login","/showReg","/registerUser").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.and()
		.logout().invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/logout-success").permitAll();
	}	
}
//Created by us:
//@Override
//protected void configure(HttpSecurity http) throws Exception {  //Generate by going to source-->>Override/Implement methods...
//	http.authorizeRequests()
//	.antMatchers("/showReg","/","/registerUser", "/login", "/showLogin", "/login/*")
//	.permitAll().antMatchers("/addFlight").hasAnyAuthority("ADMIN").anyRequest().authenticated()
//	.and().csrf().disable().logout()
//	.logoutUrl("/perform_logout")
//	.deleteCookies("");	//JSESSIONID
//	
//}

//@Bean
//@Override
//protected UserDetailsService userDetailsService() {	//Right click-->>Source-->>Override/Implement Method
//	
//	List<UserDetails> users = new ArrayList<>();
//	users.add(User.withDefaultPasswordEncoder().username("navin").password("1234").roles("USER").build());
//	
//	return new InMemoryUserDetailsManager(users);
//}
