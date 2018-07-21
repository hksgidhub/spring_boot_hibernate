package com.hks.spring_boot_hibernate.service;

import com.hks.spring_boot_hibernate.entity.User;
import com.hks.spring_boot_hibernate.repository.UserRepository;
import com.hks.spring_boot_hibernate.utils.JSONResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Object findOne(String id){
        return userRepository.findById(id);
    }
}
