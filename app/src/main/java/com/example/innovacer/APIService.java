package com.example.innovacer;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {

    @FormUrlEncoded
    @POST("checkin/")
    Call<ResponseApi> createDatabase(
            @Field("visitor_name") String name,
            @Field("visitor_email") String email,
            @Field("visitor_phone") String phone,
            @Field("host_name") String h_name,
            @Field("host_email") String h_email,
            @Field("host_phone") String h_phone
    );

    @FormUrlEncoded
    @POST("checkout/")
    Call<ResponseApi> checkOut(@Field("visitor_email") String email
    );


}
