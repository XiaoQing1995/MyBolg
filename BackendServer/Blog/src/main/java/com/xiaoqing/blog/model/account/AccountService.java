package com.xiaoqing.blog.model.account;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService implements IAccountService {

	private final AccountRepository accountRepository;

	private final PasswordEncoder passwordEncoder;

	// 新建帳戶
	@Override
	public void createsAccounts(Account account) {

		Account user = Account.builder().accountNumber(account.getAccountNumber())
				.accountPassword(passwordEncoder.encode(account.getAccountPassword())).role(account.getRole()).build();

		accountRepository.save(user);
	}

	// 取得所有文章 By Pageable，並且不帶密碼
	@Override
	public Page<Account> getAccounts(Pageable pageable) {
//		帶密碼
//		Page<Account> accounts = accountRepository.findAll(pageable);

//		不帶密碼
		Page<Account> accounts = accountRepository.findAll(pageable).map(this::convertNoPassword);
		return accounts;
	}

	// 更新帳戶
	@Override
	public void updatesAccounts(Account account) {
		Optional<Account> accountById = accountRepository.findById(account.getAccountId());
		if (accountById.isPresent()) {
			if (account.getAccountPassword() == null || account.getAccountPassword().isEmpty()) {
				Account user = Account.builder().accountId(account.getAccountId())
						.accountNumber(account.getAccountNumber())
						.accountPassword(accountById.get().getAccountPassword()).role(account.getRole()).build();

				accountRepository.save(user);
			} else {
				Account user = Account.builder().accountId(account.getAccountId())
						.accountNumber(account.getAccountNumber())
						.accountPassword(passwordEncoder.encode(account.getAccountPassword())).role(account.getRole())
						.build();

				accountRepository.save(user);
			}
		}

	}

	// 刪除帳戶
	@Override
	public void deletesAccounts(int id) {
		accountRepository.deleteById(id);
	}

	// 轉為沒有密碼的 Account
	private Account convertNoPassword(Account account) {
		Account user = Account.builder().accountId(account.getAccountId()).accountNumber(account.getAccountNumber())
				.role(account.getRole()).build();
		return user;
	}
}
