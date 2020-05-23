package com.aarongutierrez.blizzardapi.interfaces;

import com.aarongutierrez.blizzardapi.response.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IOAuthTokenRequest {
    @POST("/oauth/token")
    @FormUrlEncoded
    Call<TokenResponse> getToken(@Field("client_id") String client_id,
                                 @Field("client_secret") String clients_secret,
                                 @Field("scope") String scope,
                                 @Field("grant_type") String grant_type);
}
