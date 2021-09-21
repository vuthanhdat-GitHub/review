package com.server.tradedoc.logic.api;

import com.server.tradedoc.logic.dto.request.ResetPasswordRequest;
import com.server.tradedoc.logic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ResetPasswordAPI
 *
 * @author DatDV
 */
@RestController
@RequestMapping("/reset")
public class ResetPasswordAPI {

    @Autowired
    private UserService userService;

    /**
     * resetPassword
     *
     * @param resetPasswordRequest
     * @return
     */
    @RequestMapping(value = "/password" , method = RequestMethod.POST)
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest){
        return ResponseEntity.ok(userService.resetPassWord(resetPasswordRequest));
    }
}
