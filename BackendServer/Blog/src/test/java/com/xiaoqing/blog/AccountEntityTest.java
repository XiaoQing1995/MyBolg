package com.xiaoqing.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.xiaoqing.blog.model.account.Account;

public class AccountEntityTest {
	
	@Test
	public void whenSetAccountId_ThenGetAccountId() {
		Account account = new Account();
		account.setAccountId(1);
		Integer expected = 1;
		Integer actual = account.getAccountId();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void whenSetAccountNumber_ThenGetAccountNumber() {
		Account account = new Account();
		account.setAccountNumber("test1");
		String expected = "test1";
		String actual = account.getAccountNumber();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void whenSetAccountPassword_ThenGetAccountPassword() {
		Account account = new Account();
		account.setAccountPassword("test2");
		String expected = "test2";
		String actual = account.getAccountPassword();
		
		assertEquals(expected, actual);
	}
}
