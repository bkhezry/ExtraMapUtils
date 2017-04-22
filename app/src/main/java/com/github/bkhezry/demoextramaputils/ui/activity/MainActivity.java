package com.github.bkhezry.demoextramaputils.ui.activity;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.github.bkhezry.demoextramaputils.R;
import com.github.bkhezry.demoextramaputils.ui.fragment.AboutFragment;
import com.github.bkhezry.demoextramaputils.ui.fragment.BasicFragment;
import com.github.bkhezry.demoextramaputils.ui.fragment.DataLoaderFragment;
import com.github.bkhezry.demoextramaputils.ui.fragment.ListViewFragment;

public class MainActivity extends AppCompatActivity {
    private Fragment mFragment = new BasicFragment().newInstance();
    private FragmentManager mFragmentManager;
    private BottomNavigationView navigation;
    private String title = "Basic";

    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_basic:
                    mFragment = new BasicFragment().newInstance();
                    title = getResources().getString(R.string.title_basic);
                    break;
                case R.id.navigation_loader:
                    mFragment = new DataLoaderFragment().newInstance();
                    title = getResources().getString(R.string.title_loader);
                    break;
                case R.id.navigation_list:
                    mFragment = new ListViewFragment().newInstance();
                    title = getResources().getString(R.string.title_listview);
                    break;
                case R.id.navigation_about:
                    mFragment = new AboutFragment().newInstance();
                    title = getResources().getString(R.string.title_about);
                    break;
            }
            doTransaction();
            return true;
        }

    };

    private void doTransaction() {
        if (mFragment != null) {
            setTitle(title);
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
