package com.xiaoqing.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.xiaoqing.blog.model.account.AccountRepository;

import lombok.RequiredArgsConstructor;


// Spring 配置類，主要設置安全和認證的相關組件
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
	private final AccountRepository accountRepository;
	
	// UserDetailsService 是 Spring Security 中用於加載用戶特定數據的接口，透過 AccountRepository 查找用戶信息
	@Bean
	UserDetailsService userDetailsService() {
		return accountNumber -> accountRepository.findByAccountNumber(accountNumber)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}
	
	// AuthenticationProvider 是 Spring Security 的認證提供者，用於基於 DAO (數據訪問對象) 的認證，使用 userDetailsService 獲取用戶信息，並使用 passwordEncoder 對密碼進行編碼的比較
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	// AuthenticationManager 是 Spring Security 中用於處理認證的主要接口，允許進行認證操作
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	// 用於密碼的編碼
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}