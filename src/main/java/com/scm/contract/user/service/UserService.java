package com.scm.contract.user.service;

import com.scm.contract.user.dto.UserDto;
import java.util.stream.Stream;

public interface UserService {

    Stream<UserDto> getUser();

    Stream<UserDto> getUserById(String loginId);

    Stream<UserDto> getUserByUserNm(String userNm);
}
