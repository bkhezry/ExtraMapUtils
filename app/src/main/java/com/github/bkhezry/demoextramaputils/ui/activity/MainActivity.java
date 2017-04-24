package com.github.bkhezry.demoextramaputils.ui.activity;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.aspsine.fragmentnavigator.FragmentNavigator;
import com.github.bkhezry.demoextramaputils.R;
import com.github.bkhezry.demoextramaputils.adapter.FragmentAdapter;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigation;
    private String title = "Basic";
    private FragmentNavigator fragmentNavigator;
    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_basic:
                    fragmentNavigator.showFragment(0);
                    title = getResources().getString(R.string.title_basic);
                    break;
                case R.id.navigation_loader:
                    fragmentNavigator.showFragment(1);
                    title = getResources().getString(R.string.title_loader);
                    break;
                case R.id.navigation_list:
                    fragmentNavigator.showFragment(2);
                    title = getResources().getString(R.string.title_listview);
                    break;
                case R.id.navigation_about:
                    fragmentNavigator.showFragment(3);
                    title = getResources().getString(R.string.title_about);
                    break;
            }
            setTitle(title);
            return true;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        fragmentNavigator = new FragmentNavigator(getSupportFragmentManager(), new FragmentAdapter(), R.id.contentFrameLayout);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        fragmentNavigator.showFragment(0);
    }

}
