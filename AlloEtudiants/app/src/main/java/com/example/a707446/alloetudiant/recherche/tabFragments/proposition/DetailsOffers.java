package com.example.a707446.alloetudiant.recherche.tabFragments.proposition;

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

public class DetailsOffers extends AppCompatActivity {
    //Views
    @BindView(R.id.title_event_detail)
    public TextView title;

    @BindView(R.id.event_image_detail)
    public ImageView icon;

    @BindView(R.id.date_event_detail)
    public TextView date;

    @BindView(R.id.description_event_detail)
    public TextView description;

    @BindView(R.id.address_event_detail)
    public TextView address;

    private Request offer;
    private String idOffer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_offers);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idOffer = extras.getString("id");
            // and get whatever type user account id is
        }

        Toast.makeText(getApplicationContext(), idOffer, Toast.LENGTH_LONG).show();
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
