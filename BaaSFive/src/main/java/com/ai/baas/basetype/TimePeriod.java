package com.ai.baas.basetype;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A base / value business entity used to represent a period of time, between two time points
 */
public class TimePeriod {

    /**
     * An instant of time, starting at the TimePeriod
     * <p/>
     * Notes:
     * If null, then represents to the beginning of time
     */
    public Date startDateTime;
    /**
     * An instant of time, ending at the TimePeriod:
     * <p/>
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

    public TimePeriod(String startDateTime, String endDateTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            if (StringUtils.isNotEmpty(startDateTime)) {
                this.startDateTime = format.parse(startDateTime);
            }
            if (StringUtils.isNotEmpty(endDateTime)) {
                this.endDateTime = format.parse(endDateTime);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public TimePeriod() {
    }


    /**
     * 0  表示在区间
     * -1 小于开始时间
     * 1  大于结束时间
     *
     * @param time
     * @return
     */
    public int isInTimePeriod(Date time) {
        if (this.startDateTime != null && this.endDateTime != null) {
            if (time.compareTo(this.startDateTime) == 1 && time.compareTo(this.endDateTime) == -1) {
                return 0;
            } else if (time.compareTo(this.startDateTime) == -1) {
                return -1;
            } else if (time.compareTo(this.endDateTime) == 1) {
                return 1;
            }
        }
        return 0;
    }

}