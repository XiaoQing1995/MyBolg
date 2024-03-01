package com.xiaoqing.blog.model.account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IAccountService {

	// 新建帳戶
	public void createsAccounts(Account account);

	// 取得帳戶 By Pageable
	Page<Account> getAccounts(Pageable pageable);
	
	// 更新帳戶
	public void updatesAccounts(Account account) throws Exception;

	// 刪除帳戶
	void deletesAccounts(int id);

	

}