package com.huangjinyong.library.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

/**
 * @author huangjinyong
 */
public class Reservation {
    private IntegerProperty id=new SimpleIntegerProperty();
    private IntegerProperty seatId=new SimpleIntegerProperty();
    private IntegerProperty studentId=new SimpleIntegerProperty();
    private IntegerProperty status=new SimpleIntegerProperty();
    private IntegerProperty isScore=new SimpleIntegerProperty();
    private IntegerProperty isFinish=new SimpleIntegerProperty();

    public int getIsFinish() {
        return isFinish.get();
    }

    public IntegerProperty isFinishProperty() {
        return isFinish;
    }

    public void setIsFinish(Integer isFinish) {
        this.isFinish.set(isFinish);
    }

    private Date orderDate;
    private IntegerProperty timeFrom=new SimpleIntegerProperty();

    public int getTimeTo() {
        return timeTo.get();
    }

    public IntegerProperty timeToProperty() {
        return timeTo;
    }

    public void setTimeTo(Integer timeTo) {
        this.timeTo.set(timeTo);
    }

    public int getTimeFrom() {
        return timeFrom.get();
    }

    public IntegerProperty timeFromProperty() {
        return timeFrom;
    }

    public void setTimeFrom(Integer timeFrom) {
        this.timeFrom.set(timeFrom);
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    private IntegerProperty timeTo=new SimpleIntegerProperty();
    private Date orderTime;

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public int getSeatId() {
        return seatId.get();
    }

    public IntegerProperty seatIdProperty() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId.set(seatId);
    }

    public int getStudentId() {
        return studentId.get();
    }

    public IntegerProperty studentIdProperty() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId.set(studentId);

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

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public int getIsScore() {
        return isScore.get();
    }

    public IntegerProperty isScoreProperty() {
        return isScore;
    }

    public void setIsScore(Integer isScore) {
        this.isScore.set(isScore);
    }
}
