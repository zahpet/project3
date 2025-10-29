package com.javacourse.myproject3.dto;

public class UserDetailResponse implements UserResponse {
    private Long id;
    private String name;
    private String surname;
    private String personID;
    private String uuid;


    public UserDetailResponse(Long id, String name, String surname, String personID, String uuid) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.personID = personID;
        this.uuid = uuid;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPersonID() {
        return personID;
    }

    public String getUuid() {
        return uuid;
    }
}
