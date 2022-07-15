package model;

import java.sql.Date;
import java.sql.Time;

public class Check {
    private int checkId;
    private Date checkDate;
    private Time checkTime;
    private Double checkSummary;

    public int getCheckId() {
        return checkId;
    }

    public void setCheckId(int checkId) {
        this.checkId = checkId;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Time getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Time checkTime) {
        this.checkTime = checkTime;
    }

    public Double getCheckSummary() {
        return checkSummary;
    }

    public void setCheckSummary(Double checkSummary) {
        this.checkSummary = checkSummary;
    }
}
