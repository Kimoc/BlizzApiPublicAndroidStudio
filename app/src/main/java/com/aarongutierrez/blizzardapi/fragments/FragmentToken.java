package com.aarongutierrez.blizzardapi.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.aarongutierrez.blizzardapi.BuildConfig;
import com.aarongutierrez.blizzardapi.R;
import com.aarongutierrez.blizzardapi.constants.Constants;
import com.aarongutierrez.blizzardapi.interfaces.IOAuthTokenRequest;
import com.aarongutierrez.blizzardapi.response.TokenResponse;
import com.aarongutierrez.blizzardapi.service.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentToken extends Fragment {

    private Button btTokenresuqest;
    private Button btValidationRequest;
    private TextView tvTokenRequestView;
    private TextView tvTokenValidationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout= inflater.inflate(R.layout.fragment_token,container,false);
        btTokenresuqest=layout.findViewById(R.id.bt_token_request);
        btValidationRequest=layout.findViewById(R.id.bt_token_validation);
        tvTokenRequestView=layout.findViewById(R.id.tv_token_request_response);
        tvTokenValidationView=layout.findViewById(R.id.tv_token_validation_request);
        btTokenresuqest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IOAuthTokenRequest authTokenRequest= ServiceGenerator.createService(IOAuthTokenRequest.class);
                final Call<TokenResponse> tokenResponseCall=authTokenRequest.getToken(
                        Constants.OAUTH_CLIENT_ID,
                        Constants.OAUTH_CLIENT_SECRET,
                        "d3.profile",
                        "client_credentials");
                tokenResponseCall.enqueue(new Callback<TokenResponse>() {
                    @Override
                    public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                        TokenResponse tokenResponse= response.body();
                        tvTokenRequestView.setText(tokenResponse.getAccessToken());
                    }

                    @Override
                    public void onFailure(Call<TokenResponse> call, Throwable t) {

                    }
                });
            }
        });

        return layout;

    }
}
