package com.example.a707446.alloetudiant.inscription;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.connexion.Login;
import com.example.a707446.alloetudiant.inscription.presenter.InscriptionContract;
import com.example.a707446.alloetudiant.inscription.presenter.InscriptionPresenter;

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
    public void onInscrireClick(){mPresenter.startInscrire();}

    @OnClick(R.id.annuler)
    public void onAnnulerClick(){mPresenter.startAnnuler();}

    @Override
    public void annuler() {
        Intent i = new Intent(getApplicationContext(),Login.class);
        startActivity(i);
    }

    @Override
    public void inscrire() {
        Toast.makeText(getApplication(), "Merci pour votre inscription!", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(),Login.class);
        startActivity(i);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.homme:
                if (checked)
                    radioFemme.setChecked(false);
                    break;
            case R.id.femme:
                if (checked)
                    radioHomme.setChecked(false);
                    // Ninjas rule
                    break;
        }
    }
}
