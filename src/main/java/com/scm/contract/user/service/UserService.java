package com.scm.contract.user.service;

import com.scm.contract.manager.entity.ManagerEntity;
import com.scm.contract.user.dto.UserDto;
import com.scm.contract.user.entity.UserEntity;

import java.util.List;
import java.util.stream.Stream;

public interface UserService {

    Stream<UserDto> getUser();

    Stream<UserDto> getUserById(String loginId);

    Stream<UserDto> getUserByUserNm(String userNm);
    Stream<UserDto> getUserList(String loginId, String userNm);
}
