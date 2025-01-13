package com.example.plexora;

// import java.io.File;
// import java.io.IOException;
// import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.multipart.MultipartFile;

// import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
// import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.plexora.responseHandler.responseHandler;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

 
    UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "createuser", method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        List<User> userDetails = new ArrayList<>();
        try {
            if (userService.createUser(user)) {
                return responseHandler.generateResponse(true, HttpStatus.CREATED, (Object) userDetails);
            } else {
                return responseHandler.generateResponse(false, HttpStatus.NOT_ACCEPTABLE, (Object) userDetails);
            }
        } catch (Exception e) {
            return responseHandler.generateResponse(false, HttpStatus.NOT_ACCEPTABLE, (Object) userDetails);
        }
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<Object> validateUser(@RequestBody User data) {
        String phone = data.getPhone();
        String password = data.getPassword();
        User user = userService.isValidUser(phone, password);
        if (user == null) {
            return responseHandler.generateResponse(false, HttpStatus.BAD_GATEWAY, null);
        } else {
            return responseHandler.generateResponse(true, HttpStatus.OK, new UserWrapper(user));
        }
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public ResponseEntity<Object> isValidMobile(@RequestBody String phone) {
        boolean isAvailable = userService.isAvailable(phone);
        return responseHandler.generateResponse(isAvailable, HttpStatus.BAD_REQUEST, (Object) null);
    }
}
