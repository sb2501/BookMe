package com.sboyd.bookme;



import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity{
    private FirebaseAuth mAuth;

    @Override
    public void onStart()
    {
        super.onStart();
        if(mAuth.getCurrentUser() != null)
        {

        }
        else {
            Tab1Fragment tab1 = new Tab1Fragment();
            getSupportFragmentManager().beginTransaction().add(R.id.container, tab1).commitNow();
        }
    }

    @Override
    public void onStop()
    {
        super.onStop();
        mAuth.signOut();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Profile"));
        tabLayout.addTab(tabLayout.newTab().setText("Appointments"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        mAuth = FirebaseAuth.getInstance();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0)
                {
                    if(mAuth.getCurrentUser() != null)
                    {

                    }
                    else {
                        Tab1Fragment tab1 = new Tab1Fragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, tab1).commitNow();
                    }
                }
                else if(tab.getPosition() == 1)
                {
                    Tab2Fragment tab2 = new Tab2Fragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,tab2).commitNow();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


}

