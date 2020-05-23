package com.aarongutierrez.blizzardapi.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aarongutierrez.blizzardapi.R;
import com.aarongutierrez.blizzardapi.constants.Constants;
import com.aarongutierrez.blizzardapi.interfaces.IOAuthTokenRequest;
import com.aarongutierrez.blizzardapi.interfaces.IOauthTokenValidation;
import com.aarongutierrez.blizzardapi.response.TokenResponse;
import com.aarongutierrez.blizzardapi.response.TokenValidationResponse;
import com.aarongutierrez.blizzardapi.service.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentToken extends Fragment {

    private Button btTokenresuqest;
    private Button btValidationRequest;
    private TextView tvTokenRequestView;
    private TextView tvTokenValidationView;
    private String token;

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
                        token=tokenResponse.getAccessToken();
                    }

                    @Override
                    public void onFailure(Call<TokenResponse> call, Throwable t) {

                    }
                });
            }
        });
        btValidationRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(token==null) {
                    Toast.makeText(v.getContext(), "UPS NO HAS GENERADO PRIMERO UN TOKEN", Toast.LENGTH_SHORT).show();
                    return;
                }
                IOauthTokenValidation tokenValidationRequest= ServiceGenerator.createService(IOauthTokenValidation.class);
                final Call<TokenValidationResponse> tokenValidationResponseCall=tokenValidationRequest.getTokenValidation(
                        "eu",
                        token);
                tokenValidationResponseCall.enqueue(new Callback<TokenValidationResponse>() {
                    //Si obtenemos respuesta podremos avisarle al usuario que el token de sesion sigue siendo valido
                    @Override
                    public void onResponse(Call<TokenValidationResponse> call, Response<TokenValidationResponse> response) {
                        TokenValidationResponse tokenValidationResponse=response.body();
                        if(tokenValidationResponse.getClient_id()!=null && tokenValidationResponse.getExpiration()!=null){
                            tvTokenValidationView.setText("EL TOKEN ES VALIDO");
                        }else{
                            tvTokenValidationView.setText("HA HABIDO RESPUESTA PERO NO ES LA DESEADA");
                        }

                    }
                    @Override
                    public void onFailure(Call<TokenValidationResponse> call, Throwable t) {
                        tvTokenValidationView.setText("Error Validating Token");
                    }
                });


            }
        });

        return layout;

    }
}
