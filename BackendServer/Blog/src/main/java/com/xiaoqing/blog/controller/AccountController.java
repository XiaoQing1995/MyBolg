package com.xiaoqing.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoqing.blog.model.account.IAccountService;

@RestController
public class AccountController {
	@Autowired
	IAccountService accountService;

}
