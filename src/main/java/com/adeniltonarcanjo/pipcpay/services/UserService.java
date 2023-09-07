package com.adeniltonarcanjo.pipcpay.services;


import com.adeniltonarcanjo.pipcpay.domain.user.User;
import com.adeniltonarcanjo.pipcpay.domain.user.UserType;
import com.adeniltonarcanjo.pipcpay.dtos.UserDTO;
import com.adeniltonarcanjo.pipcpay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception{
        if (sender.getUserType()== UserType.MERCHANT){
            throw new Exception(" usuario do tipo logista não esta autorizado a realizar tranzação");
        }

        if (sender.getBalance().compareTo(amount)<0){
            throw new Exception("saldo insuficiente");
        }
    }


    public User findUserById( Long id ) throws Exception{
        return this.repository.findUserById(id).orElseThrow(()-> new Exception("Usuario não encontrado"));
    }

    public User createUser(UserDTO userDTO){
        User newUser = new User(userDTO);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public void saveUser(User user ){
        this.repository.save(user);
    }

}
