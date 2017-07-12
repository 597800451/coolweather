package com.example.nice.coolweather.util;

import android.text.TextUtils;

import com.example.nice.coolweather.gson.Weather;
import com.example.nice.coolweather.shiti_class.City;
import com.example.nice.coolweather.shiti_class.County;
import com.example.nice.coolweather.shiti_class.Province;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by NICE on 2017/7/12.
 */

public class Utility {
    public static boolean handleProvinceResponse(String responce){
        if(!TextUtils.isEmpty(responce)){
            try {
                JSONArray allProvince=new JSONArray(responce);
                for(int i=0;i<allProvince.length();i++) {
                    JSONObject provinceObject=allProvince.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCityResponse(String responce,int provinceId){
        if(!TextUtils.isEmpty(responce)){
            try {
                JSONArray allProvince=new JSONArray(responce);
                for(int i=0;i<allProvince.length();i++) {
                    JSONObject cityObject=allProvince.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCountyResponse(String responce,int cityId){
        if(!TextUtils.isEmpty(responce)){
            try {
                JSONArray allProvince=new JSONArray(responce);
                for(int i=0;i<allProvince.length();i++) {
                    JSONObject countyObject=allProvince.getJSONObject(i);
                    County county = new County();
                    county.setCountName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static Weather handleWeatherResponse(String response){
        try {

            JSONObject jsonObject=new JSONObject(response);
            JSONArray jsonArray=jsonObject.getJSONArray("HeWeather");
            String weatherContent=jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent,Weather.class);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
