package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repo.BankRepository;

@Service
public class AuditService {

	private BankRepository bankRepository;
	
	
	public AuditService(BankRepository bankRepo) {
		this.bankRepository=bankRepo;
		
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void log(String message) {
		bankRepository.insertAudit(message);
	}
}