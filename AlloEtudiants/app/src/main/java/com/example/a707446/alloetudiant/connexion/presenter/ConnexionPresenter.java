package com.example.a707446.alloetudiant.connexion.presenter;

import com.example.a707446.alloetudiant.general.repository.connexion.LoginRepository;
import com.example.a707446.alloetudiant.general.repository.connexion.LoginRepositoryImpl;
import com.example.a707446.alloetudiant.general.SharedPreferencesHelper;
import com.example.a707446.alloetudiant.general.model.payload.LoginRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConnexionPresenter implements ConnexionContract.Presenter {
    // Constants
    private static final String TAG = ConnexionPresenter.class.getSimpleName();

    // Globals
    private ConnexionContract.View mView;

    private LoginRepository loginRepository = new LoginRepositoryImpl();

    public ConnexionPresenter(ConnexionContract.View view) {
        mView = view;
    }

    @Override
    public void startLogin(final String email, String password) {
        loginRepository.login(new LoginRequest(email, password))
        .enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
//                mView.toast(response.code()+response.headers().get("Authorization"));
                if(response.code() == 200) {
                    SharedPreferencesHelper.setToken(response.headers().get("Authorization"));
                    getProfileIdByEmail(response.headers().get("Authorization"),email);
                } else {
                    mView.showError("Adresse e-mail ou mot de passe incorrect");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                mView.toast("Failure : " + t.toString());
            }
        });
    }

    @Override
    public void startSignup() {
        mView.signup();
    }

    @Override
    public void startForgetPassword(){mView.forgetPassword();}

    private void getProfileIdByEmail(final String token, String email){
        loginRepository.getProfileIdByEmail(email)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful()){
                            mView.login(token,response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        mView.showError("Failure: "+t.toString());
                    }
                });
    }


}
