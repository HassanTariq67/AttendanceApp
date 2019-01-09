package com.naqibhatti.mod.madfinal;

public class Admin {
    private String adminId;
    private String username;
    private String password;

    public Admin(){
        //Empty Constructor
    }

    public Admin(String adminId, String username,String password){
        this.adminId = adminId;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAdminId(String adminId) {
        this.username = adminId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdminId() {
        return adminId;
    }
}
