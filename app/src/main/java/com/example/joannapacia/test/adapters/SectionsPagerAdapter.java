package com.example.joannapacia.test.adapters;

/**
 * Created by joannapacia on 12/03/17.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.joannapacia.test.fragments.PlaceholderFragment;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page;
        // return a PlaceholderFragment (defined as a static inner class below);
        // return PlaceholderFragment.newInstance(position + 1);
        // returning the current tabs
        switch (position) {
            case 0:
                return PlaceholderFragment.newInstance("http://www.independent.co.uk/api/v1/11831/json");
            case 1:
                return PlaceholderFragment.newInstance("http://www.independent.co.uk/api/v1/11916/json");
            case 2:
                return PlaceholderFragment.newInstance("http://www.independent.co.uk/api/v1/11981/json");
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        // show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Front";
            case 1:
                return "World";
            case 2:
                return "Business";
        }
        return null;
    }
}

