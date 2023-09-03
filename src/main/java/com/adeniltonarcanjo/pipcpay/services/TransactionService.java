package com.adeniltonarcanjo.pipcpay.services;

import com.adeniltonarcanjo.pipcpay.domain.user.User;
import com.adeniltonarcanjo.pipcpay.dtos.TransactionDTO;
import com.adeniltonarcanjo.pipcpay.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private UserService userServicice;

    @Autowired
    private TransactionRepository repository;


    private void CreateTransection(TransactionDTO transaction) throws Exception {
        User sender = this.userServicice.findUserById(transaction.senderId());
    }

}
