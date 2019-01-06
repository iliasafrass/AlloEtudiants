package com.example.a707446.alloetudiant.general.view;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.a707446.alloetudiant.R;
import com.example.a707446.alloetudiant.annonces.AnnonceFragment;
import com.example.a707446.alloetudiant.home.HomeFragment;
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
                switch (menuItem.getItemId()) {
                    case R.id.notification:

                        Toast.makeText(getApplication(), "notification", Toast.LENGTH_SHORT).show();
                }
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
                                break;
                            case R.id.action_annonce:
                                selectedFragment = AnnonceFragment.newInstance();
                                break;
                            case R.id.action_publier:
                                selectedFragment = PublierFragment.newInstance();
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK :
                // displaying the first fragment
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.navigationActivity_fragmentContainer, HomeFragment.newInstance());
                transaction.commit();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
