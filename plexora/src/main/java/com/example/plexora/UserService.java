package com.example.plexora;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean createUser(User user) {
        try {
            if (userRepository.existsByPhone(user.getPhone())
            // || userRepository.existsByEmail(user.getEmail())
            ) {
                return false;
            }
            userRepository.save(user);
            return true;
        } catch (DataIntegrityViolationException e) {
            // Handle specific constraint violation exceptions (e.g., unique constraint)
            return false;
        } catch (TransactionSystemException e) {
            // Handle transaction-related exceptions
            return false;
        } catch (Exception e) {
            // Handle other unexpected exceptions
            return false;
        }
    }

    public User isValidUser(String phone, String password) {
        User user = userRepository.findByPhone(phone);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public boolean isAvailable(String phone) {
        return !userRepository.existsByPhone(phone);
    }

}
