package com.pagae.pagae.services;

import com.pagae.pagae.models.UserModel;
import com.pagae.pagae.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserModel> findAll () {
        return userRepository.findAll();
    }

    public UserModel createUser (UserModel userModel) {
        return userRepository.save(userModel);
    }

    public void deleteUser (Long id) {
        userRepository.deleteById(id);
    }
}
