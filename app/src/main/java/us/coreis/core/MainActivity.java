package us.coreis.core;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yalantis.guillotine.animation.GuillotineAnimation;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final long RIPPLE_DURATION = 250;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.root)
    FrameLayout root;
    @Bind(R.id.content_hamburger)
    View contentHamburger;
    boolean doubleBackToExitPressedOnce = false;
    Fragment curF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(null);
        }

        View guillotineMenu = LayoutInflater.from(this).inflate(R.layout.guillotine, null);
        root.addView(guillotineMenu);
        final LinearLayout aboutUs = (LinearLayout) guillotineMenu.findViewById(R.id.about_group);
        final LinearLayout mHome = (LinearLayout) guillotineMenu.findViewById(R.id.home_group);
        final LinearLayout events = (LinearLayout) guillotineMenu.findViewById(R.id.events_group);
        final LinearLayout contactUs = (LinearLayout) guillotineMenu.findViewById(R.id.contact_group);
        final LinearLayout encore = (LinearLayout) guillotineMenu.findViewById(R.id.encore_group);
        final GuillotineAnimation GA = new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), contentHamburger)
                .setStartDelay(RIPPLE_DURATION)
                .setActionBarViewForAnimation(toolbar)
                .setClosedOnStart(true)
                .build();
        getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment home = new HomeFragment();
        fragmentTransaction.replace(R.id.containerView, home);

        fragmentTransaction.commit();

        mHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                Fragment home = new HomeFragment();
                fragmentTransaction.replace(R.id.containerView, home);

                GA.close();
                fragmentTransaction.commit();
            }
        });

        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                Fragment eventsF = new EventsFragment();
                fragmentTransaction.replace(R.id.containerView, eventsF);

                GA.close();
                fragmentTransaction.commit();
            }
        });

        encore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                Fragment encoreF = new EncoreFragment();
                fragmentTransaction.replace(R.id.containerView, encoreF);

                GA.close();
                fragmentTransaction.commit();
            }
        });
        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                Fragment contact_us = new ContactFragment();
                fragmentTransaction.replace(R.id.containerView, contact_us);

                GA.close();
                fragmentTransaction.commit();
            }
        });

        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                Fragment about_us = new TeamFragment();
                fragmentTransaction.replace(R.id.containerView, about_us);

                GA.close();
                fragmentTransaction.commit();
            }
        });

    }
}
