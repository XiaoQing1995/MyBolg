package com.xiaoqing.blog.model.account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAccountService {

	boolean createAccount(Account account);

	Page<Account> getAccounts(Pageable pageable);
	
	boolean updateAccount(Account account) throws Exception;

	void deleteAccount(int id);


}