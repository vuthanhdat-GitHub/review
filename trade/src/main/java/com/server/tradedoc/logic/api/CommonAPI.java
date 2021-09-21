package com.server.tradedoc.logic.api;

import com.server.tradedoc.logic.dto.custom.UserDTOCustom;
import com.server.tradedoc.logic.dto.request.ChangePassRequest;
import com.server.tradedoc.logic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * CommonAPI : API for all user
 *
 * @author DatDV
 */
@RestController
@RequestMapping("/api/client")
public class CommonAPI {

    @Autowired
    private UserService userService;

    /**
     * getMyProFile : get profile user using login
     *
     * @return UserDTOCustom : response of API {com.server.tradedoc.logic.dto.custom}
     */
    @RequestMapping(value = "/get-my-profile", method = RequestMethod.GET)
    public ResponseEntity<?> getMyProFile() {
        return ResponseEntity.ok(userService.findOne());
    }

    /**
     * updateMyProFile : API update profile of user using login
     *
     * @param userDTOCustom : param for update {com.server.tradedoc.logic.dto.custom}
     * @return MessageSuccess DTO response of API
     */
    @RequestMapping(value = "/update-my-profile", method = RequestMethod.PUT)
    public ResponseEntity<?> updateMyProFile(@RequestBody UserDTOCustom userDTOCustom) {
        return ResponseEntity.ok(userService.updateProfile(userDTOCustom));
    }

    /**
     *changePassword : API change password
     *
     * @param changePassRequest : DTO request for change pass {com.server.tradedoc.logic.dto.request}
     * @return MessageSuccess : DTO response of API {com.server.tradedoc.logic.dto.reponse}
     */
    @RequestMapping(value = "/change-password" , method = RequestMethod.PUT)
    public ResponseEntity<?> changePassword(@RequestBody ChangePassRequest changePassRequest) {
        return ResponseEntity.ok(userService.changePassword(changePassRequest));
    }
}
