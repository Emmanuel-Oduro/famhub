package com.example.nanayawzaza.farmiconn.Adopter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.nanayawzaza.farmiconn.Utils.Utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nana Yaw Zaza on 12/5/2017.
 */

public class ViewPageAdapter extends FragmentPagerAdapter {
    public String TAG = ViewPageAdapter.class.getSimpleName();

    private final List<Fragment> mFragmentList = new ArrayList<>();

    public ViewPageAdapter(FragmentManager manager) {
        super(manager);
    }
    @Override
    public Fragment getItem(int position) {

        Utilities.displayLog(TAG,String.valueOf(mFragmentList.get(position)));
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        Utilities.displayLog(TAG,String.valueOf(mFragmentList.size()));
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment) {
        Utilities.displayLog(TAG,String.valueOf(mFragmentList));
        mFragmentList.add(fragment);
    }
}


/*extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public ViewPageAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:

            case 1:
                ProfileView profileView = new ProfileView();
                return profileView;
            case 2:
                ProductList productList = new ProductList();
                return productList;
            case 3:
                PasswordChange passwordChange = new PasswordChange();
                return passwordChange;
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
        */