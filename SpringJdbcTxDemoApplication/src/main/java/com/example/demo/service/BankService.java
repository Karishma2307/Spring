package com.example.demo.service;

import javax.management.RuntimeErrorException;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repo.BankRepository;

@Service
public class BankService {

	private final BankRepository bankRepository;
	private final AuditService auditService;
	
	public BankService(BankRepository bankRepository,AuditService auditService)
	{
		this.bankRepository=bankRepository;
		this.auditService=auditService;
	}
	
	@Transactional
	 public void transfer(int fromId, int toId, int amount, boolean failAfterDebit) {
		 
		 auditService.log
		 ("TRANSFER START : from " +fromId + ", to = "+toId + ", amount = "+amount);
		 
		 
		 int fromBalance=bankRepository.getBalance(fromId);
		 int toBalance=bankRepository.getBalance(toId);
				 
		if(fromBalance<amount)
		{
			auditService.log("TRANSFER FAILED : Due to insuffient Balance");
			throw new RuntimeException("InSufficient Balance");
		}
		
	
		bankRepository.updateBalance(fromId, fromBalance-amount);
		
		if(failAfterDebit)
		{
			auditService.log("TRANSFER FAILED  : simulated error after Debit");
			throw new RuntimeException("Simulate failue after debit");
		}
		bankRepository.updateBalance(toId, toBalance+amount);
		auditService.log("TRANSFER SUCCESS");
	 }
	
}