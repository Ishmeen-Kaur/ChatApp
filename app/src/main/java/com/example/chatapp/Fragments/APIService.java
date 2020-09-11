package com.example.chatapp.Fragments;

import com.example.chatapp.Notifications.MyResponse;
import com.example.chatapp.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAALDhnRtc:APA91bFcpu4VCeYNNY1ix_L1xEzcCRgp1JDUNgnIPf__KNVkzsxFmxK-eKTDfMSb5V6-Zr_g2AgpeLbqLE0OvP3JiJRoToCJNWLA2NwylvcIBm6SV3FnD78dAYWJGlPlYm8fE_YfnlTf"

            }
    )

    @POST("fcm/send")
    Call<MyResponse>sendNotification(@Body Sender body);
}
