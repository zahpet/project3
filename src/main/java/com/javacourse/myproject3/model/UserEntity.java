package com.javacourse.myproject3.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "personID"),
        @UniqueConstraint(columnNames = "uuid")
})
public class UserEntity {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name", nullable = false)
    private String name;


    @Column(name = "surname")
    private String surname;


    @Column(name = "person_ID", nullable = false, unique = true)
    private String personID;


    @Column(name = "uuid", nullable = false, unique = true)
    private String uuid;

// getters / setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }


    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }


    public String getPersonID() { return personID; }
    public void setPersonID(String personID) { this.personID = personID; }


    public String getUuid() { return uuid; }
    public void setUuid(String uuid) { this.uuid = uuid; }

}
