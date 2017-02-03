package com.cviac.s4iApp.datamodel;

/**
 * Created by john on 12/23/2016.
 */

public class MembershipInfo {
    private String MemState;
    private String MemDis;
    private String MemType;
    private String MemPlan;
    private String reg_type;
    private String MemID;

    public String getMemID() {
        return MemID;
    }

    public void setMemID(String memID) {
        MemID = memID;
    }


    public String getMemState() {
        return MemState;
    }

    public void setMemState(String memState) {
        MemState = memState;
    }

    public String getMemDis() {
        return MemDis;
    }

    public void setMemDis(String memDis) {
        MemDis = memDis;
    }

    public String getMemType() {
        return MemType;
    }

    public void setMemType(String memType) {
        MemType = memType;
    }

    public String getMemPlan() {
        return MemPlan;
    }

    public void setMemPlan(String memPlan) {
        MemPlan = memPlan;
    }

    public String getReg_type() {
        return reg_type;
    }

    public void setReg_type(String reg_type) {
        this.reg_type = reg_type;
    }


}
