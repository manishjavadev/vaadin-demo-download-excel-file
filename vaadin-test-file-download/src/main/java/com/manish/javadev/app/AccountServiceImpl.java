package com.manish.javadev.app;

import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

	@Override
	public String getName() {
		return "Hello Manish";
	}

}
