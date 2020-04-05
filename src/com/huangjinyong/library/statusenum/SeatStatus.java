package com.huangjinyong.library.statusenum;

/**
 * @author huangjinyong
 */

public enum SeatStatus{
    /**
     * ordered
     */
    ORDERED("已被预定",0),
    /**
     * unordered
     */
    UNORDERED("未被预定",1);

    private String status;
    SeatStatus(String status,int i){
        this.status=status;
    }
    @Override
    public String toString() {
        return status;
    }
}
