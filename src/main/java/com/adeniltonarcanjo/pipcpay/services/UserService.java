package com.adeniltonarcanjo.pipcpay.services;


import com.adeniltonarcanjo.pipcpay.domain.user.User;
import com.adeniltonarcanjo.pipcpay.domain.user.UserType;
import com.adeniltonarcanjo.pipcpay.dtos.UserDTO;
import com.adeniltonarcanjo.pipcpay.repositories.UserRepository;
import com.adeniltonarcanjo.pipcpay.services.exceptions.DataIntegrityViolationException;
import com.adeniltonarcanjo.pipcpay.services.exceptions.ObjectNotFoundException;
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
            throw new Exception("unauthorized user");
        }

        if (sender.getBalance().compareTo(amount)<0){
            throw new Exception("insufficient balance");
        }
    }


    public User findUserById( Long id ) {
        return this.repository.findUserById(id).orElseThrow(()->
                new ObjectNotFoundException("User Not Found!  ID: "+id + "Object Type "+ User.class.getName()));
    }

    public User createUser(UserDTO userDTO){
        User newUser = new User(userDTO);
        this.saveUser(newUser);
        return newUser;
    }

    public User findUserByDocumment(String documment){
        return this.repository.findUserByDocument(documment).orElseThrow(()->
                new ObjectNotFoundException("User Not Found! Documment: "+ documment+ " Object Type "
                + User.class.getName()));
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public void saveUser(User user ){
        try {
            this.repository.save(user);
        } catch(org.springframework.dao.DataIntegrityViolationException e){
                throw new com.adeniltonarcanjo.pipcpay.services.exceptions.DataIntegrityViolationException("User document or email already exist");
        }

    }


    public void delete(Long id) {
        findUserById(id);

            repository.deleteById(id);

    }
}
