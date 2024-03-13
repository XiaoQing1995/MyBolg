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

//	@Override
//	public boolean createAccount(Account account) {
//		if (accountRepository.findByAccountNumber(account.getAccountNumber()).isEmpty()) {
//			Account user = Account.builder().accountNumber(account.getAccountNumber())
//					.accountPassword(passwordEncoder.encode(account.getAccountPassword())).role(account.getRole())
//					.build();
//			accountRepository.save(user);
//			return true;
//		}
//		return false;
//	}

	@Override
	public Page<Account> getAccounts(Pageable pageable) {
//		不帶密碼
		Page<Account> accounts = accountRepository.findAll(pageable).map(this::convertAccountNoPassword);
		return accounts;
	}

	@Override
	public boolean updateAccount(Account account) {
		Optional<Account> accountById = accountRepository.findById(account.getAccountId());

		if (accountById.isEmpty()) {
			return false;
		}

		try {
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
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public void deleteAccount(int id) {
		accountRepository.deleteById(id);
	}

	// 轉為沒有密碼的 Account
	private Account convertAccountNoPassword(Account account) {
		Account user = Account.builder().accountId(account.getAccountId()).accountNumber(account.getAccountNumber())
				.role(account.getRole()).build();
		return user;
	}
}
