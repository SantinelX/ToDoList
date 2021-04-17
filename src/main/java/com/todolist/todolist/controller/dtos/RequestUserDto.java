package com.todolist.todolist.controller.dtos;

public class RequestUserDto {
    private String userName;
    private String password;
    private String roleName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRolName(String rolName) {
        this.roleName = rolName;
    }
}
