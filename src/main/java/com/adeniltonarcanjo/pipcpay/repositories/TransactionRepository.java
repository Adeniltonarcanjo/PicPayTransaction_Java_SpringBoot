package com.adeniltonarcanjo.pipcpay.repositories;

import com.adeniltonarcanjo.pipcpay.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository  extends JpaRepository<Transaction, Long> {
}
