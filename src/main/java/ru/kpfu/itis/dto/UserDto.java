package ru.kpfu.itis.dto;

public class UserDto {

    private String name;
    private Integer score;
    private String lastname;

    public UserDto(String name, Integer score, String lastname) {
        this.name = name;
        this.score = score;
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public Integer getScore() {
        return score;
    }

    public String getLastname() {
        return lastname;
    }
}
