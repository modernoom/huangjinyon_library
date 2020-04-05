package com.huangjinyong.library.entity;

import javafx.beans.property.*;

/**
 * @author huangjinyong
 */
public class Library {
    private IntegerProperty id=new SimpleIntegerProperty();
    private StringProperty name=new SimpleStringProperty();
    private DoubleProperty score=new SimpleDoubleProperty();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public double getScore() {
        return score.get();
    }

    public DoubleProperty scoreProperty() {
        return score;
    }

    public void setScore(Double score) {
        this.score.set(score);
    }

    @Override
    public String toString() {
        return name.get();
    }
}
