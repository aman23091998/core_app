package us.coreis.core;

import android.support.v4.app.Fragment;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.StackView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.drawer_layout) DrawerLayout drawerLayout;
    @Bind(R.id.toolbar)    Toolbar toolbar;
    @Bind(R.id.drawer_recyclerView)  RecyclerView drawerRecyclerView ;
    ActionBarDrawerToggle drawerToggle ;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        drawerToggle= new ActionBarDrawerToggle(this ,drawerLayout ,toolbar ,R.string.app_name ,R.string.app_name );
        setSupportActionBar(toolbar);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        String rows[];
        TypedArray navIcons;


        rows =getResources().getStringArray(R.array.navDrawerItems) ;

        navIcons = getResources().obtainTypedArray(R.array.navDrawerIcons);

        DrawerAdapter drawerAdapter =new DrawerAdapter(rows ,navIcons , this );
        drawerRecyclerView.setAdapter(drawerAdapter);
        drawerRecyclerView.hasFixedSize();
        drawerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        drawerSwitch();                        //DrawerToggle

        //Add the Very First i.e Home Fragment to the Container
        Fragment homeFragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.containerView, homeFragment, null);
        fragmentTransaction.commit();
    }
    void drawerSwitch(){
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        drawerToggle.syncState();
    }
}