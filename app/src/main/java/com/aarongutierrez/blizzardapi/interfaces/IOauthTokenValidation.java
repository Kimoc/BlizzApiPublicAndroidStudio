package com.aarongutierrez.blizzardapi.interfaces;

import com.aarongutierrez.blizzardapi.response.TokenValidationResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IOauthTokenValidation {
    @POST("/oauth/check_token")
    @FormUrlEncoded
    Call<TokenValidationResponse> getTokenValidation(@Field(":region") String region,
                                                     @Field("token") String token);
}
