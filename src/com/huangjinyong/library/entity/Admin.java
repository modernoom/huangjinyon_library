package com.huangjinyong.library.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author huangjinyong
 */
public class Admin implements User{
    private IntegerProperty id=new SimpleIntegerProperty();
    private StringProperty username=new SimpleStringProperty();
    private StringProperty password=new SimpleStringProperty();

    public Integer getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username=" + username +
                ", password=" + password +
                '}';
    }

    @Override
    public <T> T getUser(Class<T> tClass) {
        if(Admin.class==tClass){
            return (T)this;
        }
        return null;
    }

    @Override
    public String getType() {
        return "admin";
    }
}
