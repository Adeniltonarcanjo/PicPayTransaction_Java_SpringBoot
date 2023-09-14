package com.adeniltonarcanjo.pipcpay.controllers;

import com.adeniltonarcanjo.pipcpay.domain.transaction.Transaction;
import com.adeniltonarcanjo.pipcpay.domain.user.User;
import com.adeniltonarcanjo.pipcpay.dtos.TransactionDTO;
import com.adeniltonarcanjo.pipcpay.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;




  @PostMapping
    public ResponseEntity<Transaction> createTransection(@RequestBody TransactionDTO transection) throws Exception {
      Transaction newTransection= this.transactionService.createTransection(transection);
      return new ResponseEntity<>(newTransection,HttpStatus.OK);

  }







}
