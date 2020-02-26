package com.example.tabtest2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String SELECTED_ITEM = "selected_item";

    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;
    private MenuItem menuItemSelected;
    private int mMenuItemSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                selectFragment(item);
//                return true;
//            }
//        });

        //Always load first fragment as default
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, new FragmentTap1());
        fragmentTransaction.commit();

//        if (savedInstanceState != null) {
//            mMenuItemSelected = savedInstanceState.getInt(SELECTED_ITEM, 0);
//            menuItemSelected = bottomNavigationView.getMenu().findItem(mMenuItemSelected);
//        } else {
//            menuItemSelected = bottomNavigationView.getMenu().getItem(0);
//        }
//
//        selectFragment(menuItemSelected);
    }

    private void selectFragment(MenuItem item) {
        Fragment fragment = null;
        Class fragmentClass;
        switch (item.getItemId()) {
            case R.id.navigation_tap1:
                fragmentClass = FragmentTap1.class;
                break;
            case R.id.navigation_tap2:
                fragmentClass = FragmentTap2.class;
                break;
            case R.id.navigation_tap3:
                fragmentClass = FragmentTap3.class;
                break;
            case R.id.navigation_tap4:
                fragmentClass = FragmentTap4.class;
                break;

            default:
                fragmentClass = FragmentTap1.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(SELECTED_ITEM, mMenuItemSelected);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        MenuItem homeItem = bottomNavigationView.getMenu().getItem(0);
        if (mMenuItemSelected != homeItem.getItemId()) {
            selectFragment(homeItem);
        } else {
            super.onBackPressed();
        }
    }
}
