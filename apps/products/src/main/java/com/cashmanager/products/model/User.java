package com.cashmanager.products.model;

import java.beans.JavaBean;
import javax.persistence.*;
import javax.persistence.Id;

@Entity
@Table(name = "users")
@JavaBean
public class User {
    @Id
    private String username;
    private String password;
    private Boolean enabled;

    public User() { }

    public User(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.enabled = user.getEnabled();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }
}