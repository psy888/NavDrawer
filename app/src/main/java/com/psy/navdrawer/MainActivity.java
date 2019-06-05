package com.psy.navdrawer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    int[] mSlides = new int[]{R.id.tvSlideOne, R.id.tvSlideTwo, R.id.tvSlideThree};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find Views
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this,mDrawerLayout, toolbar,R.string.openMenu, R.string.closeMenu);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = findViewById(R.id.navView);
        //Set default slide for first start
        if(savedInstanceState==null){
            changeSlide(mSlides[0]);
            mNavigationView.setCheckedItem(0);
        }


        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.mi_slideOne:
                        changeSlide(R.id.tvSlideOne);
                        break;
                    case R.id.mi_slideTwo:
                        changeSlide(R.id.tvSlideTwo);
                        break;
                    case R.id.mi_slideThree:
                        changeSlide(R.id.tvSlideThree);
                        break;

                }
//                mDrawerLayout.closeDrawer(Gravity.START);
                mDrawerLayout.closeDrawer(mNavigationView);
                return true;
            }
        });

    }

    void changeSlide(int slideId){
        TextView slide = null;
        //Find current visible slide
        for (int i = 0; i < mSlides.length ; i++) {
            slide = findViewById(mSlides[i]);
            if (slide.getAlpha()>0){
                //hide current visible slide
                slide.animate().alpha(0)
                        .scaleX(0)
                        .scaleY(0)
                        .setDuration(500)
                        .withEndAction(resetProp(slide))
                        .setInterpolator(new AccelerateInterpolator());
//                        .start();


                break;}

        }


        //show new slide
        TextView slide2 = findViewById(slideId);
        /*if(slide2.getScaleX()!=1)
        {
            slide2.setScaleX(1);
            slide2.setScaleY(1);
        }*/
        slide2.animate().alpha(1f).setDuration(500).setInterpolator(new AccelerateInterpolator());//.start();

    }
    /**Reset Properties to starting state*/
    Runnable resetProp(final View v)
    {
        Runnable resetProp = new Runnable() {
            @Override
            public void run() {
                v.setScaleX(1);
                v.setScaleY(1);
            }
        };
        return resetProp;
    }
}
