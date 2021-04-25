package com.daniilvdovin.wowraider.Token;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TokenByRegion {
    public String
            region;
    public Integer
            current_price,
            last_change,
            time_of_last_change_unix_epoch;

    public String getRegion() {
        return region;
    }

    public String getCurrent_price() {
        return String.format("%,8d g.",current_price) ;
    }

    public String getLast_change() {
        return String.format("%s%,4d g.",(last_change>0?"+":""),last_change);
    }

    public String getTime_of_last_change_unix_epoch() {
        Date date = new java.util.Date(time_of_last_change_unix_epoch*1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("MM-dd HH:mm z");
        sdf.setTimeZone(java.util.TimeZone.getDefault());
        return sdf.format(date);
    }
}
