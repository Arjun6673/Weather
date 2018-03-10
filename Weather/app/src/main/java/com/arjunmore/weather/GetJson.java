package com.arjunmore.weather;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ARJUN on 3/10/2018.
 */

public class GetJson {

    public static Weather GetJsonData(String data){
        Weather weather = new Weather();
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONObject query = jsonObject.getJSONObject("query");
            JSONObject results = query.getJSONObject("results");
            JSONObject Channel = results.getJSONObject("channel");
            JSONObject location = Channel.getJSONObject("location");

            String city = location.getString("city");
            String country = location.getString("country");
            weather.setCity(city);
            weather.setCountry(country);

            JSONObject astronomy = Channel.getJSONObject("astronomy");

            String sunrise = astronomy.getString("sunrise");
            String sunset = astronomy.getString("sunset");
            weather.setSunrise(sunrise);
            weather.setSunset(sunset);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weather;
    }
}
