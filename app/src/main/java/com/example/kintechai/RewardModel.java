package com.example.kintechai;

import com.google.firebase.Timestamp;

import java.util.Date;

public class RewardModel {

    private String type;
    private String upperLimit;
    private String lowerLimit;
    private String discORamt;
    private String couponBody;
    private Date timestamp;
    private Boolean alreadyUsed;
    private String couponId;


    public RewardModel(String couponId, String type, String upperLimit, String lowerLimit, String discORamt, String couponBody, Date timestamp, Boolean alreadyUsed) {
        this.couponId = couponId;
        this.type = type;
        this.upperLimit = upperLimit;
        this.lowerLimit = lowerLimit;
        this.discORamt = discORamt;
        this.couponBody = couponBody;
        this.timestamp = timestamp;
        this.alreadyUsed = alreadyUsed;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public Boolean getAlreadyUsed() {
        return alreadyUsed;
    }

    public void setAlreadyUsed(Boolean alreadyUsed) {
        this.alreadyUsed = alreadyUsed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(String upperLimit) {
        this.upperLimit = upperLimit;
    }

    public String getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(String lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public String getDiscORamt() {
        return discORamt;
    }

    public void setDiscORamt(String discORamt) {
        this.discORamt = discORamt;
    }

    public String getCouponBody() {
        return couponBody;
    }

    public void setCouponBody(String couponBody) {
        this.couponBody = couponBody;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
