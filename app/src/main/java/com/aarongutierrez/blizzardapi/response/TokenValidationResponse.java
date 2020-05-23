package com.aarongutierrez.blizzardapi.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokenValidationResponse {
    @SerializedName("exp")
    @Expose
    private String expiration;
    @SerializedName("client_id")
    @Expose
    private String client_id;



    public String getExpiration() {
        return expiration;
    }

    public String getClient_id() {
        return client_id;
    }

}
