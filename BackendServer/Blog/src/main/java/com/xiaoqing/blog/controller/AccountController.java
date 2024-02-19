package com.xiaoqing.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoqing.blog.model.account.AccountService;

@RestController
public class AccountController {
	@Autowired
	AccountService accountService;

}
