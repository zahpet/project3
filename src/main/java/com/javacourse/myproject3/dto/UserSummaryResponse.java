package com.javacourse.myproject3.dto;

public class UserSummaryResponse implements UserResponse {
    private Long id;
    private String name;
    private String surname;


    public UserSummaryResponse(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public String getSurname() { return surname; }
}
