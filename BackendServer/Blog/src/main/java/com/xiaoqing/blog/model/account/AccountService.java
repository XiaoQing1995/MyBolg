package com.xiaoqing.blog.model.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AccountService {
	@Autowired
	AccountDao accountDao;

	// 新建帳戶
	public boolean createsAccount(Account account) {
		try {
			accountDao.save(account);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	
}
