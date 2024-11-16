package ru.kpfu.itis.dto;

public class UserRegistrationDto {
    private String name;
    private String lastname;
    private String login;
    private String password;

    public UserRegistrationDto(String name, String lastname, String login, String password) {
        this.name = name;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}