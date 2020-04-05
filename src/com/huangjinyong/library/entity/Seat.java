package com.huangjinyong.library.entity;


import javafx.beans.property.*;

/**
 * @author huangjinyong
 */
public class Seat {

    private IntegerProperty id=new SimpleIntegerProperty();
    private StringProperty icon=new SimpleStringProperty();
    private DoubleProperty comfortScore=new SimpleDoubleProperty();
    private DoubleProperty affectScore=new SimpleDoubleProperty();
    private DoubleProperty totalScore=new SimpleDoubleProperty();
    private IntegerProperty status=new SimpleIntegerProperty();
    private StringProperty statusString=new SimpleStringProperty();
    private IntegerProperty usableTime=new SimpleIntegerProperty();
    private IntegerProperty timeFrom=new SimpleIntegerProperty();
    private IntegerProperty timeTo=new SimpleIntegerProperty();
    private StringProperty type=new SimpleStringProperty();
    private IntegerProperty seatTypeId=new SimpleIntegerProperty();

    public Integer getSeatTypeId() {
        return seatTypeId.get();
    }

    public IntegerProperty seatTypeIdProperty() {
        return seatTypeId;
    }

    public void setSeatTypeId(Integer seatTypeId) {
        this.seatTypeId.set(seatTypeId);
    }

    private IntegerProperty floorId=new SimpleIntegerProperty();
    private IntegerProperty libraryId=new SimpleIntegerProperty();

    public int getId() {
        return id.get();
    }

    public String getStatusString() {
        return statusString.get();
    }

    public StringProperty statusStringProperty() {
        return statusString;
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public String getIcon() {
        return icon.get();
    }

    public StringProperty iconProperty() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon.set(icon);
    }

    public double getComfortScore() {
        return comfortScore.get();
    }

    public DoubleProperty comfortScoreProperty() {
        return comfortScore;
    }

    public void setComfortScore(Double comfortScore) {
        this.comfortScore.set(comfortScore);
    }

    public double getAffectScore() {
        return affectScore.get();
    }

    public DoubleProperty affectScoreProperty() {
        return affectScore;
    }

    public void setAffectScore(Double affectScore) {
        this.affectScore.set(affectScore);
    }

    public double getTotalScore() {
        return totalScore.get();
    }

    public DoubleProperty totalScoreProperty() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore.set(totalScore);
    }

    public Integer getStatus() {
        return status.get();
    }

    public IntegerProperty statusProperty() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status.set(status);
        if(status==1){
            statusString.set("未被预定");
        }
        if(status==0){
            statusString.set("已被预定");
        }
    }

    public int getUsableTime() {
        return usableTime.get();
    }

    public IntegerProperty usableTimeProperty() {
        return usableTime;
    }

    public void setUsableTime(Integer usableTime) {
        this.usableTime.set(usableTime);
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

    public int getTimeTo() {
        return timeTo.get();
    }

    public IntegerProperty timeToProperty() {
        return timeTo;
    }

    public void setTimeTo(Integer timeTo) {
        this.timeTo.set(timeTo);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public int getFloorId() {
        return floorId.get();
    }

    public IntegerProperty floorIdProperty() {
        return floorId;
    }

    public void setFloorId(Integer floorId) {
        this.floorId.set(floorId);
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


}
