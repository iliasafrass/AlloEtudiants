package com.example.a707446.alloetudiant.recherche.tabFragments.demande;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.general.model.pojo.Request;
import com.example.a707446.alloetudiant.general.view.NavigationActivity;

import butterknife.BindView;

public class DetailsRequests extends AppCompatActivity {

    //Views
    @BindView(R.id.title_request_detail)
    public TextView title;

    @BindView(R.id.request_image_detail)
    public ImageView icon;

    @BindView(R.id.date_request_detail)
    public TextView date;

    @BindView(R.id.description_request_detail)
    public TextView description;

    @BindView(R.id.address_request_detail)
    public TextView address;

    private Request request;
    private String idRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_requests);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idRequest = extras.getString("id");
            // and get whatever type user account id is
        }

        Toast.makeText(getApplicationContext(), idRequest, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:

                Intent i = new Intent(this, NavigationActivity.class);
                startActivity(i);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();

    }
}
