package com.github.bkhezry.demoextramapview.ui;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.github.bkhezry.demoextramapview.R;
import com.github.bkhezry.demoextramapview.ui.fragment.BasicFragment;
import com.github.bkhezry.demoextramapview.ui.fragment.RecycleViewFragment;

public class MainActivity extends AppCompatActivity {
    private Fragment mFragment = new BasicFragment().newInstance();
    private FragmentManager mFragmentManager;
    private BottomNavigationView navigation;
    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_basic:
                    mFragment = new BasicFragment().newInstance();
                    break;
                case R.id.navigation_list:
                    mFragment = new RecycleViewFragment().newInstance();
                    break;
                case R.id.navigation_about:

                    break;
            }
            doTransaction();
            return true;
        }

    };

    private void doTransaction() {
        if (mFragment != null) {
            mFragmentManager.beginTransaction()
                    .replace(R.id.contentFrameLayout, mFragment).commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        mFragmentManager = getSupportFragmentManager();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        doTransaction();
    }

}
