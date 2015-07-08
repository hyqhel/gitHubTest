package com.ai.baas.basetype;

import java.text.ParseException;
import java.util.Date;

import com.ai.baas.common.util.DateUtils;

/**
 * A base / value business entity used to represent a period of time, between two time points
 */
public class TimePeriod {

    /**
     * An instant of time, starting at the TimePeriod
     * 
     * Notes:
     * If null, then represents to the beginning of time
     */
    public Date startDateTime;
    /**
     * An instant of time, ending at the TimePeriod:
     * 
     * Notes:
     * If null, then represents to the end of time
     */
    public Date endDateTime;

    public Date getStartDateTime() {
        return this.startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return this.endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public int compait(Date time) throws ParseException {
    	DateUtils du = new DateUtils();
    	if(du.dateDiff('s', du.parseCalendar(time), du.parseCalendar(startDateTime))<0){
    		return -1;
    	}
    	if(du.dateDiff('s', du.parseCalendar(time), du.parseCalendar(endDateTime))>0){
    		return 1;
    	}
    	if(du.dateDiff('s', du.parseCalendar(time), du.parseCalendar(startDateTime))>=0 &&
    			du.dateDiff('s', du.parseCalendar(time), du.parseCalendar(endDateTime))<=0){
    		return 0;
    	}
    	return 0;
    }
    
    /**
     * 0  表示在区间
     * -1 小于开始时间
     * 1  大于结束时间
     * @param time
     * @return
     */
    public int isInTimePeriod(Date time){
        if (this.startDateTime != null && this.endDateTime != null) {
            if(time.compareTo(this.startDateTime) == 1 && time.compareTo(this.endDateTime) == -1){
            	return 0;
            }else if(time.compareTo(this.startDateTime) == -1){
            	return -1;
            }else if(time.compareTo(this.endDateTime) == 1){
            	return 1;
            }
        }
        return 0;
    }

}