package com.example.a707446.alloetudiant.inscription;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.connexion.Login;
import com.example.a707446.alloetudiant.general.view.NavigationActivity;
import com.example.a707446.alloetudiant.inscription.presenter.InscriptionContract;
import com.example.a707446.alloetudiant.inscription.presenter.InscriptionPresenter;
import com.example.a707446.alloetudiant.general.model.dto.RegisterProfileDto;
import com.example.a707446.alloetudiant.general.model.enumeration.Gender;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Inscription extends AppCompatActivity implements InscriptionContract.View {

    //Views
    @BindView(R.id.homme)
    public RadioButton radioHomme;
    @BindView(R.id.femme)
    public RadioButton radioFemme;
    @BindView(R.id.genderRadioGroup)
    public RadioGroup radioGroup;
    @BindView(R.id.email)
    public EditText edtEmail;
    @BindView(R.id.nom)
    public EditText edtNom;
    @BindView(R.id.prenom)
    public EditText edtPrenom;
    @BindView(R.id.phoneNumber)
    public EditText edtPhoneNumber;
    @BindView(R.id.password1)
    public EditText edtPassword;

    private String sexe;

    // Globals
    private InscriptionContract.Presenter mPresenter;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        mUnbinder = ButterKnife.bind(this);
        mPresenter = new InscriptionPresenter(this);
    }

    @OnClick(R.id.inscrire)
    public void onInscrireClick(){
        RegisterProfileDto profileDto = new RegisterProfileDto();
        profileDto.setEmail(edtEmail.getText().toString());
        profileDto.setFirstName(edtPrenom.getText().toString());
        profileDto.setLastName(edtNom.getText().toString());
        profileDto.setPassword(edtPassword.getText().toString());
        profileDto.setPhoneNumber(edtPhoneNumber.getText().toString());

        if(sexe.equals("homme"))
            profileDto.setGender(Gender.MALE);
        else
            profileDto.setGender(Gender.FEMALE);
        mPresenter.startInscrire(profileDto);
    }

    @OnClick(R.id.annuler)
    public void onAnnulerClick(){mPresenter.startAnnuler();}

    @Override
    public void annuler() {
        Intent i = new Intent(getApplicationContext(),Login.class);
        startActivity(i);
    }

    @Override
    public void toast(String message) {
        Toast.makeText(getApplication(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void endInscrire(String message, int code) {
        if(code == 200){
            Toast.makeText(getApplication(), "Un email vous a été envoyé pour confirmation", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(),NavigationActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(getApplication(), code+message, Toast.LENGTH_SHORT).show();
        }
    }

    public void onRadioButtonClicked(View view) {
        sexe=((RadioButton) view).getText().toString();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK :
                Intent i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void onPause() {
        super.onPause();
        finish();

    }
}
