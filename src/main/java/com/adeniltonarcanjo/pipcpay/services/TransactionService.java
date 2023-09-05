package com.adeniltonarcanjo.pipcpay.services;

import com.adeniltonarcanjo.pipcpay.domain.transaction.Transaction;
import com.adeniltonarcanjo.pipcpay.domain.user.User;
import com.adeniltonarcanjo.pipcpay.dtos.TransactionDTO;
import com.adeniltonarcanjo.pipcpay.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private UserService userServicice;

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private RestTemplate restTemplate; // diponibilizado pelo spring para fazer requisições

    String urlAuthorize= "https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6";


    public void CreateTransection(TransactionDTO transactionDTO) throws Exception {
        User sender = this.userServicice.findUserById(transactionDTO.senderId());
        User receiver = this.userServicice.findUserById(transactionDTO.receiverId());

        userServicice.validateTransaction(sender, transactionDTO.value());

        boolean isAuthoried= this.authorizeTransaction(sender, transactionDTO.value());
        if(!isAuthoried){
            throw new Exception("transação não autorizada");
        }else{

          Transaction transaction= new Transaction();
          transaction.setAmount(transactionDTO.value());
          transaction.setReceiver(receiver);
          transaction.setSender(sender);
          transaction.setTimestamp(LocalDateTime.now());

          sender.setBalance(sender.getBalance().subtract(transactionDTO.value()));
          receiver.setBalance(receiver.getBalance().add(transactionDTO.value()));

          this.repository.save(transaction);
          this.userServicice.saveUser(sender);
          this.userServicice.saveUser(receiver);

        }
    }



    public boolean authorizeTransaction(User sender, BigDecimal value){

        ResponseEntity<Map> authorizationResponse= restTemplate.getForEntity(urlAuthorize, Map.class);

        if(authorizationResponse.getStatusCode()== HttpStatus.OK ){
           String message =(String)authorizationResponse.getBody().get("message");
          return message.equalsIgnoreCase("Autorizado");
        } else return false;


    }
}
