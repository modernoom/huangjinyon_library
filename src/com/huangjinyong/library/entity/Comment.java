package com.huangjinyong.library.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class Comment {
    private IntegerProperty id=new SimpleIntegerProperty();
    private StringProperty studentName=new SimpleStringProperty();
    private IntegerProperty reservationId=new SimpleIntegerProperty();
    private IntegerProperty seatId=new SimpleIntegerProperty();
    private StringProperty context=new SimpleStringProperty();
    private Date time;


    public int getSeatId() {
        return seatId.get();
    }

    public IntegerProperty seatIdProperty() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId.set(seatId);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public String getStudentName() {
        return studentName.get();
    }

    public StringProperty studentNameProperty() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName.set(studentName);
    }

    public int getReservationId() {
        return reservationId.get();
    }

    public IntegerProperty reservationIdProperty() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId.set(reservationId);
    }

    public String getContext() {
        return context.get();
    }

    public StringProperty contextProperty() {
        return context;
    }

    public void setContext(String context) {
        this.context.set(context);
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", studentName=" + studentName +
                ", reservationId=" + reservationId +
                ", seatId=" + seatId +
                ", context=" + context +
                ", time=" + time +
                '}';
    }
}
