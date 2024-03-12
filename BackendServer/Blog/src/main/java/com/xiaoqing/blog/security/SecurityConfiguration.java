package com.xiaoqing.blog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	private final AuthenticationProvider authenticationProvider;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
		.csrf((csrf) -> csrf
				.disable())
		.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers("/api/v1/accounts/**")
				.hasAuthority("ADMIN").requestMatchers("/api/v1/auth/register").hasAuthority("ADMIN")
				.requestMatchers("/api/v1/accounts/**").hasAuthority("ADMIN")
				.requestMatchers("/api/v1/auth/register").hasAuthority("ADMIN")
				.requestMatchers("/api/v1/auth/authenticate").permitAll()
				.requestMatchers(HttpMethod.GET, "/api/v1/articles/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/api/v1/articleclasses/**").permitAll().anyRequest()
				.authenticated())
		.sessionManagement((session) -> session
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authenticationProvider(authenticationProvider)
		.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}