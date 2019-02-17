package com.example.a707446.alloetudiant.general.view;

import android.content.DialogInterface;
import android.content.SharedPreferences;
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
import android.view.View;
import android.widget.Toast;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.annonces.AnnonceFragment;
import com.example.a707446.alloetudiant.general.SharedPreferencesSingleton;
import com.example.a707446.alloetudiant.home.HomeFragment;
import com.example.a707446.alloetudiant.notifications.NotificationFragment;
import com.example.a707446.alloetudiant.publication.PublierFragment;
import com.example.a707446.alloetudiant.recherche.RechercheFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NavigationActivity extends AppCompatActivity implements BottomBar.DisableBottomBar , BottomBar.EnableBottomBar{

    // Views
    @BindView(R.id.navigationActivity_toolbar)
    public Toolbar mToolbar;
    @BindView(R.id.navigationActivity_bottomNavigationActivity)
    public  BottomNavigationView mBottomNavigationView;


    // Globals
    private Unbinder mUnbinder;
    public static boolean inHome;
    public static boolean firstChildFragment = false;
//    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        mUnbinder = ButterKnife.bind(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        inHome = true;
//        preferences = getSharedPreferences("token",MODE_PRIVATE);
//        preferences.edit().clear().apply();
//        SharedPreferencesSingleton.clear(getApplicationContext());

        setSupportActionBar(mToolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_account_circle_black_24dp);

        getSupportActionBar().setElevation(0);

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

                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        AbstractFragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_rechercher:
                                fragmentManager.popBackStack();
                                selectedFragment = RechercheFragment.newInstance();
                                inHome = false;
                                firstChildFragment = true;
                                break;
                            case R.id.action_annonce:
                                fragmentManager.popBackStack();
                                selectedFragment = AnnonceFragment.newInstance();
                                firstChildFragment = true;
                                inHome = false;
                                break;
                            case R.id.action_publier:
                                fragmentManager.popBackStack();
                                selectedFragment = PublierFragment.newInstance();
                                firstChildFragment = true;
                                inHome = false;
                                break;
                        }
                        fragmentTransaction.replace(R.id.navigationActivity_fragmentContainer, selectedFragment);
                        fragmentTransaction.addToBackStack(null).commit();
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
        getMenuInflater().inflate(R.menu.navigation_top, menu);
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


    private void mydialog() {
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
                        Toast.makeText(getApplicationContext(), "Au revoir!", Toast.LENGTH_LONG).show();
                        finish();
                    }
                })
                //set negative button
                .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what should happen when negative button is clicked
                        Toast.makeText(getApplicationContext(), " :) ", Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (inHome) {
                    mydialog();
                } else if(firstChildFragment){
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.navigationActivity_fragmentContainer, HomeFragment.newInstance());
                    transaction.commit();
                    inHome = true;
                    firstChildFragment = false;
                    return true;
                }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //finish();

    }

    @Override
    public void disableBottomBar() {
        mBottomNavigationView.setVisibility(View.GONE);
    }


    @Override
    public void enableBottomBar() {
        mBottomNavigationView.setVisibility(View.VISIBLE);
    }
}
