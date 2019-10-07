package com.ghc.tdi_main.Login;

public class UserDTO {
    public String userPassword;
    public String userEmail;

    public UserDTO(String userPassword, String userEmail) {
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }

    public UserDTO(){};

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}


