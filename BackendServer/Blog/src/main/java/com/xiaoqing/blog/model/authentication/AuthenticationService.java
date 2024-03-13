package com.xiaoqing.blog.model.authentication;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.xiaoqing.blog.model.account.Account;
import com.xiaoqing.blog.model.account.AccountRepository;
import com.xiaoqing.blog.model.role.Role;
import com.xiaoqing.blog.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final AccountRepository accountRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	public boolean register(Account account) {
		if (accountRepository.findByAccountNumber(account.getAccountNumber()).isEmpty()) {
			var user = Account.builder().accountNumber(account.getAccountNumber())
					.accountPassword(passwordEncoder.encode(account.getAccountPassword())).role(account.getRole())
					.build();
			accountRepository.save(user);
			var jwtToken = jwtService.generateToken(user);
			return true;
		}

		return false;

	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getAccountNumber(), request.getAccountPassword()));

		var user = accountRepository.findByAccountNumber(request.getAccountNumber()).orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(jwtToken).build();
	}
}