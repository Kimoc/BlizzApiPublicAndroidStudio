package com.aarongutierrez.blizzardapi;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.aarongutierrez.blizzardapi.controlador.PagerAdapter;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private PagerAdapter miFragmentPageAdapter;
    private ViewPager mviewPager;

    PagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miFragmentPageAdapter = new PagerAdapter(getSupportFragmentManager(),FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mviewPager = findViewById(R.id.container);
        mviewPager.setAdapter(miFragmentPageAdapter);
        TabLayout tabLayout= findViewById(R.id.tabs);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        mviewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mviewPager));








    }


}
