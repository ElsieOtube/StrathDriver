package com.example.strathdriver.model.RoutesAPI;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GoogleMapsAPIRequest {
    @SerializedName("routes")
    public ArrayList<Routes> routes;
}

