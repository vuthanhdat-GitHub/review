package com.server.tradedoc.logic.service;

import com.server.tradedoc.config.exception.CustomException;
import com.server.tradedoc.logic.dto.UserDTO;
import com.server.tradedoc.logic.dto.UserSignUpDTO;
import com.server.tradedoc.logic.dto.custom.UserDTOCustom;
import com.server.tradedoc.logic.dto.reponse.MessageSuccess;
import com.server.tradedoc.logic.dto.request.ChangePassRequest;
import com.server.tradedoc.logic.dto.request.ResetPasswordRequest;

import javax.mail.MessagingException;

/**
 * UserService : service of user
 *
 * @author DatDV
 */
public interface UserService {
    MessageSuccess signUpUser(UserSignUpDTO userSignUpDTO) throws CustomException, MessagingException;
    UserDTOCustom findOne();
    MessageSuccess updateProfile(UserDTOCustom userDTOCustom);
    MessageSuccess changePassword(ChangePassRequest changePassRequest);
    MessageSuccess resetPassWord(ResetPasswordRequest resetPasswordRequest);
    Long countUserCustomer();
}
