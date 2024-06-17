package com.matrix.matrix.service;

import com.matrix.matrix.model.Kab;
import com.matrix.matrix.model.User;
import com.matrix.matrix.repository.KabRepository;
import com.matrix.matrix.repository.UserRepository;
import exception.ResourceNotFound;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
@Transactional
public class Service {

    //add userRepository
    private final UserRepository userRepository;
    //call kab
    private final KabRepository kabRepository;

    @Autowired
    public Service(UserRepository userRepository, KabRepository kabRepository){
        this.userRepository = userRepository;
        this.kabRepository = kabRepository;
    }

    //look all user
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    //add neu User
    public User addNeuUser(User user){
        return userRepository.save(user);
    }

    // user delete
    public boolean getDeleteUser(Long id){
        if (this.chekUser(id)) {
            userRepository.deleteById(id);
            System.out.println("User with ID " + id + " has been deleted.");
            return true;
        } else {
            System.out.println("User with ID " + id + " does not exist.");
            return false;
        }
    }

    //find user by id
    public User findUserById(Long id){
        return userRepository.findById(id).orElseThrow( () -> new ResourceNotFound("User with id " + id + " not found, search!!!"));
    }

    //update user by id
    public User upadetUserById(Long id, User user){
        return userRepository.save(user);
    }
//update user version 2
    public User updateUser(Long id, User userDetails) {
        User existingUser = findUserById(id);

        existingUser.setName(userDetails.getName());
        existingUser.setEmail(userDetails.getEmail());
        existingUser.setNachname(userDetails.getNachname());
        existingUser.setCreated(userDetails.getCreated());

        return userRepository.save(existingUser);
    }

    //chek if user exist
    public boolean chekUser(Long id){
        return userRepository.existsById(id);
    }

    //add all kab
    public List<Kab> getAllKab(){
        return kabRepository.findAll();
    }


    // add version 2 kab
    public List<Kab> getAllKabVer2(){
        return kabRepository.selectAllKabManual();
    }

}
