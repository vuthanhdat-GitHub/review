package com.server.tradedoc.logic.converter;

import com.server.tradedoc.logic.dto.UserDTO;
import com.server.tradedoc.logic.dto.custom.UserDTOCustom;
import com.server.tradedoc.logic.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends MapperManager<UserDTO, UserEntity> {

    @Autowired
    private ModelMapper modelMapper;

    public UserDTOCustom convertUserDTOCustom(UserEntity userEntity) {
        return modelMapper.map(userEntity , UserDTOCustom.class);
    }
}
