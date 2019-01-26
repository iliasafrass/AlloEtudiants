package com.example.a707446.alloetudiant.connexion.presenter;

import com.example.a707446.alloetudiant.connexion.repository.LoginRepository;
import com.example.a707446.alloetudiant.connexion.repository.LoginRepositoryImpl;
import com.example.a707446.alloetudiant.connexion.webservice.LoginWebService;
import com.example.a707446.alloetudiant.general.webservice.RetrofitClientInstance;
import com.example.a707446.alloetudiant.model.payload.LoginRequest;
import com.example.a707446.alloetudiant.model.pojo.Event;

import java.util.List;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConnexionPresenter implements ConnexionContract.Presenter {
    // Constants
    private static final String TAG = ConnexionPresenter.class.getSimpleName();

    // Globals
    private ConnexionContract.View mView;

    private LoginRepositoryImpl repository = new LoginRepositoryImpl();

    public ConnexionPresenter(ConnexionContract.View view) {
        mView = view;

    }

    @Override
    public void startLogin(String email, String password) {
        RetrofitClientInstance
                .getRetrofitInstance().create(LoginWebService.class)
                .getEvents()
                .enqueue(new Callback<List<Event>>() {
                    @Override
                    public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                        List<Event> eventList = response.body();
                        if(eventList != null){
                            mView.login("SIZE : "+eventList.size());
                        } else {
                            mView.login("Walo");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Event>> call, Throwable t) {
                        mView.login("Walo Failure");
                    }
                });
    }


    @Override
    public void startSignup() {
        mView.signup();
    }

    @Override
    public void startForgetPassword(){mView.forgetPassword();}

}
