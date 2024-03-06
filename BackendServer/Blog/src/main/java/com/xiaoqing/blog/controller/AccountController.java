package com.xiaoqing.blog.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoqing.blog.model.account.Account;
import com.xiaoqing.blog.model.account.IAccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
@CrossOrigin("http://localhost:5173")
@PreAuthorize("hasAuthority('ADMIN')")
public class AccountController {
	private final IAccountService accountService;

	// 取得所有帳戶 By Pageable
	@GetMapping
	public ResponseEntity<?> getAccounts(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "accountId"));
		Page<Account> accounts = accountService.getAccounts(pageable);
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}

	// 更新帳戶
	@PutMapping
	public ResponseEntity<?> updatesAccounts(@RequestBody Account account) {
		try {
			System.out.println(account.toString());
			accountService.updatesAccounts(account);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletesAccounts(@PathVariable("id") int id) {
		try {
			accountService.deletesAccounts(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
