package com.aarongutierrez.blizzardapi.controlador;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.aarongutierrez.blizzardapi.fragments.FragmentToken;

public class PagerAdapter extends FragmentPagerAdapter {
    private int N_PAGES =3;

    public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.N_PAGES =behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FragmentToken fragmentToken =new FragmentToken();
                return fragmentToken;
            case 1:

            case 2:

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return N_PAGES;
    }
}
