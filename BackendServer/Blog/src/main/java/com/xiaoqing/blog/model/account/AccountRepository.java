package com.xiaoqing.blog.model.account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	
	Optional<Account> findByAccountNumber(String accountNumber);
	
}
