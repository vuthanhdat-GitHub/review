package com.server.tradedoc.logic.api;

import com.server.tradedoc.config.exception.CustomException;
import com.server.tradedoc.logic.dto.UserSignUpDTO;
import com.server.tradedoc.logic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

/**
 * SignUpAPI : API sign up user
 *
 * @author DatDV
 */
@RestController
@RequestMapping("/sign-up")
public class SignUpAPI {

    @Autowired
    private UserService userService;

    /**
     * signUpUser: function sign up user
     *
     * @param userSignUpDTO
     * @return
     * @throws CustomException
     * @throws MessagingException
     */
    @RequestMapping(value = "/user" , method = RequestMethod.POST)
    public ResponseEntity<?> signUpUser(@RequestBody UserSignUpDTO userSignUpDTO) throws CustomException, MessagingException {
        return ResponseEntity.ok(userService.signUpUser(userSignUpDTO));
    }
}
