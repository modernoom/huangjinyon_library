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
    private Date orderTime;
    private IntegerProperty isComment=new SimpleIntegerProperty();
    private IntegerProperty isScore=new SimpleIntegerProperty();
    private StringProperty statusString=new SimpleStringProperty();

    public String getStatusString() {
        return statusString.get();
    }

    public StringProperty statusStringProperty() {
        return statusString;
    }

    public void setStatusString(String statusString) {
        this.statusString.set(statusString);
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
        if(status==1){
            setStatusString("已完成");
        }
        setStatusString("进行中");
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public int getIsComment() {
        return isComment.get();
    }

    public IntegerProperty isCommentProperty() {
        return isComment;
    }

    public void setIsComment(Integer isComment) {
        this.isComment.set(isComment);
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
