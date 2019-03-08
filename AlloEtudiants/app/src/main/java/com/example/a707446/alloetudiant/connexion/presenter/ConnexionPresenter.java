package com.example.a707446.alloetudiant.connexion.presenter;

import com.example.a707446.alloetudiant.general.SharedPreferencesSingleton;
import com.example.a707446.alloetudiant.general.model.payload.LoginRequest;
import com.example.a707446.alloetudiant.general.repository.connexion.LoginRepository;
import com.example.a707446.alloetudiant.general.repository.connexion.LoginRepositoryImpl;

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
                        if(response.code() == 200) {
                            SharedPreferencesSingleton.setToken(mView.applicationContext(),response.headers().get("Authorization"));
                            getProfileIdByEmail(response.headers().get("Authorization"),email);

                        } else {
                            mView.showError("Adresse e-mail ou mot de passe incorrect");
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        mView.toast("Erreur inconnue. Veuillez réessayer plus tard");
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
                            SharedPreferencesSingleton.setProfileId(mView.applicationContext(),response.body());
//                            SharedPreferencesHelper.setProfileId(response.body());
//                            SharedPreferencesSingleton.getInstance(mView.applicationContext()).setProfileId(response.body());
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
