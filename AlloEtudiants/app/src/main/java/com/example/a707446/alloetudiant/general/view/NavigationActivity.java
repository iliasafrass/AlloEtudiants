package com.example.a707446.alloetudiant.general.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.annonces.AnnonceFragment;
import com.example.a707446.alloetudiant.home.HomeFragment;
import com.example.a707446.alloetudiant.notifications.NotificationFragment;
import com.example.a707446.alloetudiant.publication.PublierFragment;
import com.example.a707446.alloetudiant.recherche.RechercheFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NavigationActivity extends AppCompatActivity {

    // Views
    @BindView(R.id.navigationActivity_toolbar)
    public Toolbar mToolbar;
    @BindView(R.id.navigationActivity_bottomNavigationActivity)
    public BottomNavigationView mBottomNavigationView;

    // Globals
    private Unbinder mUnbinder;
    private boolean inHome = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        mUnbinder = ButterKnife.bind(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        setSupportActionBar(mToolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_account_circle_black_24dp);


        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AbstractFragment selectedFragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.notification:
                        Toast.makeText(getApplication(), "notification", Toast.LENGTH_SHORT).show();
                        selectedFragment = NotificationFragment.newInstance();
                        inHome = false;
                        break;
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.navigationActivity_fragmentContainer, selectedFragment);
                fragmentTransaction.commit();
                return true;
            }
        });

        mBottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        AbstractFragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_rechercher:
                                selectedFragment = RechercheFragment.newInstance();
                                inHome = false;
                                break;
                            case R.id.action_annonce:
                                selectedFragment = AnnonceFragment.newInstance();
                                inHome = false;
                                break;
                            case R.id.action_publier:
                                selectedFragment = PublierFragment.newInstance();
                                inHome = false;
                                break;
                        }
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.navigationActivity_fragmentContainer, selectedFragment);
                        fragmentTransaction.commit();
                        return true;
                    }
                });

        // Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.navigationActivity_fragmentContainer, HomeFragment.newInstance());
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_top,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                Toast.makeText(this, "mon compte", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }


    public void mydialog1(){
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                //set icon
                .setIcon(android.R.drawable.ic_dialog_alert)
                //set title
                .setTitle("")
                //set message
                .setMessage("Souhaitez-vous revenir à l'accueil ou quitter l'application ?")
                //set positive button
                .setPositiveButton("Accueil", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what would happen when positive button is clicked
                        // displaying the first fragment
                        inHome = true;
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.navigationActivity_fragmentContainer, HomeFragment.newInstance());
                        transaction.commit();
                    }
                })
                //set negative button
                .setNegativeButton("Quitter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what should happen when negative button is clicked
                        Toast.makeText(getApplicationContext(),"Au revoir!",Toast.LENGTH_LONG).show();
                        finish();
                    }
                })
                .show();
    }

    private void mydialog2() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                //set icon
                .setIcon(android.R.drawable.ic_dialog_alert)
                //set title
                .setTitle("Attention!")
                //set message
                .setMessage("Etes-vous sûr de vouloir quitter l'application ?")
                //set positive button
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what would happen when positive button is clicked
                        Toast.makeText(getApplicationContext(),"Au revoir!",Toast.LENGTH_LONG).show();
                        finish();
                    }
                })
                //set negative button
                .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what should happen when negative button is clicked
                        Toast.makeText(getApplicationContext()," :) ",Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK :
                if(inHome) {
                    mydialog2();
                }else{
                    mydialog1();
                }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();

    }

}
