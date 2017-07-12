package com.example.nice.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by NICE on 2017/7/12.
 */

public class Now {
    @SerializedName("tmp")
    public String temperature;
    @SerializedName("cond")
    public More more;
    public class More{
        @SerializedName("txt")
        public String info;
    }
}
