package com.github.bkhezry.demoextramaputils.adapter;


import android.support.v4.app.Fragment;

import com.aspsine.fragmentnavigator.FragmentNavigatorAdapter;
import com.github.bkhezry.demoextramaputils.ui.fragment.AboutFragment;
import com.github.bkhezry.demoextramaputils.ui.fragment.BasicFragment;
import com.github.bkhezry.demoextramaputils.ui.fragment.DataLoaderFragment;
import com.github.bkhezry.demoextramaputils.ui.fragment.ListViewFragment;

public class FragmentAdapter implements FragmentNavigatorAdapter {
    private static final String TABS[] = {"basic", "loader", "listview", "about"};

    @Override
    public Fragment onCreateFragment(int position) {
        Fragment mFragment = null;
        switch (position) {
            case 0:
                mFragment = new BasicFragment().newInstance();
                break;
            case 1:
                mFragment = new DataLoaderFragment().newInstance();
                break;
            case 2:
                mFragment = new ListViewFragment().newInstance();
                break;
            case 3:
                mFragment = new AboutFragment().newInstance();
                break;
        }
        return mFragment;

    }

    @Override
    public String getTag(int position) {
        return TABS[position];
    }

    @Override
    public int getCount() {
        return TABS.length;
    }
}
