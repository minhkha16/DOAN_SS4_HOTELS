package com.demo.configurations;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.cors(cor -> cor.disable()).csrf(csrf -> csrf.disable()).authorizeHttpRequests(request -> {
			request.requestMatchers("/",
					"layout/**",
					"/hotel/**",
					"/**",
					"/searchHotel/**",
					"/hotels/**",
					"https://code.jquery.com/**", 
					"/assets/**",
					"/api/**",			
					"/home/**",
					"/acount/**",
					"/home/**",
					"/payment/**",
					"/contact/**",
					"/listings/**",
					"/news/**",
					"/details/**",
					"/addHotel/**",
					"/invoice/**",
					"/static/**",
					"account/**",
					"/**",
					"https://fonts.googleapis.com/",
					"/admin/dist/**",
					"/admin/ckeditor.js",
					"/admin/plugins/**",
					"/admin/plugins/**",
					"/images/**","/account/logout",
					"/admin/account/login").permitAll()
			.requestMatchers("/admin/**").hasAnyRole("ADMIN")
			.requestMatchers("/owner/**").hasAnyRole("HOTEL")
			.requestMatchers("/account/**", "/hotel/**", "/hotels","/").hasAnyRole("USER");
		}).formLogin(form -> {
			form.loginPage("/").loginProcessingUrl("/account/process-login")
			.usernameParameter("username").passwordParameter("password")
			.successHandler(new AuthenticationSuccessHandler() {

						@Override
						public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
								Authentication authentication) throws IOException, ServletException {

							@SuppressWarnings("unchecked")
							Collection<GrantedAuthority> role = (Collection<GrantedAuthority>) authentication
									.getAuthorities();
							System.out.println("id: " + authentication.getName());
							Map<String, String> map = new HashMap<>();
							// custom to redirect
							map.put("ROLE_ADMIN", "/admin/chart/index");
							map.put("ROLE_HOTEL", "/owner/hotels/index");
							map.put("ROLE_USER", "/");

							String url = "";

							for (GrantedAuthority gr : role) {
								if (map.containsKey(gr.getAuthority())) {
									url = map.get(gr.getAuthority());
									break;
								}
							}

							response.sendRedirect(url);
						}
					}).failureUrl("/");
		}).logout(logout -> {
			logout.logoutUrl("/account/logout");
		}).build();
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}
