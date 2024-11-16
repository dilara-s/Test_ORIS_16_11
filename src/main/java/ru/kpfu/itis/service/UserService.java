package ru.kpfu.itis.service;

import ru.kpfu.itis.dto.UserDto;
import ru.kpfu.itis.dto.UserRegistrationDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAll();

    UserDto get(Integer id);

    UserDto getByLogin(String login);

    void register(UserRegistrationDto user);
}