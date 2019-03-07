package com.example.a707446.alloetudiant.inscription;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.connexion.Login;
import com.example.a707446.alloetudiant.general.model.enumeration.Grade;
import com.example.a707446.alloetudiant.general.view.NavigationActivity;
import com.example.a707446.alloetudiant.inscription.presenter.InscriptionContract;
import com.example.a707446.alloetudiant.inscription.presenter.InscriptionPresenter;
import com.example.a707446.alloetudiant.general.model.dto.RegisterProfileDto;
import com.example.a707446.alloetudiant.general.model.enumeration.Gender;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import fr.ganfra.materialspinner.MaterialSpinner;

public class Inscription extends AppCompatActivity implements InscriptionContract.View {

    //Views
    @BindView(R.id.homme)
    public RadioButton radioHomme;
    @BindView(R.id.femme)
    public RadioButton radioFemme;
    @BindView(R.id.genderRadioGroup)
    public RadioGroup radioGroup;
    @BindView(R.id.email)
    public TextInputLayout edtEmail;
    @BindView(R.id.nom)
    public TextInputLayout edtNom;
    @BindView(R.id.prenom)
    public TextInputLayout edtPrenom;
    @BindView(R.id.phoneNumber)
    public TextInputLayout edtPhoneNumber;
    @BindView(R.id.password1)
    public TextInputLayout edtPassword;

    private String sexe = "sexe";

    private ArrayAdapter<String> spinnerNiveauAdapter;
    private List<String> listItemsNiveau = new ArrayList<>();
    private String selectedSpinnerNiveau;
    private Grade mNiveau;
    MaterialSpinner spinnerNiveau;
    // Globals
    private InscriptionContract.Presenter mPresenter;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        mUnbinder = ButterKnife.bind(this);
        mPresenter = new InscriptionPresenter(this);

        //region sppiner niveau
        selectedSpinnerNiveau = "";
        spinnerNiveau  = (MaterialSpinner)findViewById(R.id.niveau);

        clearSpinnerNiveauItems();
        initSpinnerNiveauItems();
        spinnerNiveauAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listItemsNiveau);
        spinnerNiveauAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNiveau.setAdapter(spinnerNiveauAdapter);
        spinnerNiveau.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != -1)// -1 hint choose..
                {
                    selectedSpinnerNiveau = spinnerNiveau.getItemAtPosition(position).toString();
                } else {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //endregion
    }


    private void initSpinnerNiveauItems() {
        listItemsNiveau.add(Grade.L1.toString());
        listItemsNiveau.add(Grade.L2.toString());
        listItemsNiveau.add(Grade.L3.toString());
        listItemsNiveau.add(Grade.M1.toString());
        listItemsNiveau.add(Grade.M2.toString());
    }

    private void clearSpinnerNiveauItems() {
        listItemsNiveau.clear();
    }

    @OnClick(R.id.inscrire)
    public void onInscrireClick(){

        RegisterProfileDto profileDto = new RegisterProfileDto();

        String emailInput = edtEmail.getEditText().getText().toString();
        String nomInput = edtNom.getEditText().getText().toString();
        String prenomInput = edtPrenom.getEditText().getText().toString();
        String telInput = edtPhoneNumber.getEditText().getText().toString();
        String passwordInput = edtPassword.getEditText().getText().toString();
        Gender sexeInput;

        if(sexe.equals("homme"))
            sexeInput =Gender.MALE;
        else
            sexeInput =Gender.FEMALE;

        if (emailInput.isEmpty() || emailInput == null) {
            edtEmail.setError("email est obligatoire! ");
        } else
            edtEmail.setError(null);

        if (nomInput.isEmpty() || nomInput == null) {
            edtNom.setError("nom est obligatoire! ");
        } else
            edtNom.setError(null);

        if (prenomInput.isEmpty() || prenomInput == null) {
            edtPrenom.setError("prenom est obligatoire! ");
        } else
            edtPrenom.setError(null);

        if (selectedSpinnerNiveau.isEmpty() || selectedSpinnerNiveau == null) {
            spinnerNiveau.setError("niveau est obligatoire! ");
        } else
            spinnerNiveau.setError(null);

        if (telInput.isEmpty() || telInput == null) {
            edtPhoneNumber.setError("telephone est obligatoire! ");
        } else
            edtPhoneNumber.setError(null);

        if (passwordInput.isEmpty() || passwordInput == null) {
            edtPassword.setError("mot de passe est obligatoire! ");
        } else
            edtPassword.setError(null);

        if (sexeInput.name().isEmpty() || sexeInput.name() == null) {
            Toast.makeText(this,"sexe est obligatoire! ",Toast.LENGTH_LONG);
        }

        if (!passwordInput.isEmpty() && passwordInput != null && !sexeInput.name().isEmpty() && sexeInput.name() != null && !telInput.isEmpty() && telInput != null && !selectedSpinnerNiveau.isEmpty() && selectedSpinnerNiveau != null && !prenomInput.isEmpty() && prenomInput != null && !nomInput.isEmpty() && nomInput != null && !emailInput.isEmpty() && emailInput != null) {
            Grade niveauInput = Grade.valueOf(selectedSpinnerNiveau);
            profileDto.setEmail(emailInput);
            profileDto.setFirstName(prenomInput);
            profileDto.setLastName(nomInput);
            profileDto.setGrade(niveauInput);
            profileDto.setPassword(passwordInput);
            profileDto.setPhoneNumber(telInput);
            profileDto.setGender(sexeInput);
            mPresenter.startInscrire(profileDto);
        }

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

        } else {
            Toast.makeText(getApplication(), "code !=200 :"+code+message, Toast.LENGTH_SHORT).show();
        }
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

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.homme:
                if (checked) {
                    sexe = "homme";
                    radioFemme.setChecked(false);
                }
                break;
            case R.id.femme:
                if (checked) {
                    sexe = "femme";
                    radioHomme.setChecked(false);
                }
                // Ninjas rule
                break;
        }
    }
}
