package ru.bgpu.splk.models;

import jakarta.persistence.*;
import ru.bgpu.splk.dto.login.LoginUserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private String surname;
    private String login;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Group> groups = new ArrayList<>();

    public User() {
    }

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public LoginUserDto toLoginUserDto(){
        LoginUserDto userDto = new LoginUserDto(id, name, surname, login);
        userDto.setGroups(groups.stream().map(Group::getName).collect(Collectors.toList()));
        return userDto;
    }
}
