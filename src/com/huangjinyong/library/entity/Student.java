package com.huangjinyong.library.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student implements User {
    private IntegerProperty id=new SimpleIntegerProperty();
    private StringProperty username=new SimpleStringProperty();
    private StringProperty password=new SimpleStringProperty();
    private StringProperty trueName=new SimpleStringProperty();
    private IntegerProperty status=new SimpleIntegerProperty();

    public Student(String username, String password, String trueName) {
        setUsername(username);
        setPassword(password);
        setTrueName(trueName);
    }
    public Student(){}


    public int getId() {
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

    public String getTrueName() {
        return trueName.get();
    }

    public StringProperty trueNameProperty() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName.set(trueName);
    }

    public int getStatus() {
        return status.get();
    }

    public IntegerProperty statusProperty() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status.set(status);
    }

    @Override
    public <T> T getUser(Class<T> tClass) {
        if(Student.class==tClass){
            return (T)this;
        }
        return null;
    }

    @Override
    public String getType() {
        return "student";
    }
}
