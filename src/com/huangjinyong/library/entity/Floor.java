package com.huangjinyong.library.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author huangjinyong
 */
public class Floor {
    private IntegerProperty id=new SimpleIntegerProperty();
    private StringProperty name=new SimpleStringProperty();
    private IntegerProperty libraryId=new SimpleIntegerProperty();

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

    public int getLibraryId() {
        return libraryId.get();
    }

    public IntegerProperty libraryIdProperty() {
        return libraryId;
    }

    public void setLibraryId(Integer libraryId) {
        this.libraryId.set(libraryId);
    }

    @Override
    public String toString() {
        return name.get();
    }
}
