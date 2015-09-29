package us.coreis.core;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by aman on 29/9/15.
 */
public class MainActivity extends AppCompatActivity {
    @Bind(R.id.drawer_layout) DrawerLayout drawerLayout;
    @Bind(R.id.toolbar)    Toolbar toolbar;
    @Bind(R.id.drawer_recyclerView)  RecyclerView drawerRecyclerView ;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle drawerToggle= new ActionBarDrawerToggle(this ,drawerLayout ,toolbar ,R.string.app_name ,R.string.app_name );
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        List <String> rows =new ArrayList<>();
        rows.add("Home");
        rows.add("Events");
        rows.add("EnCOЯE");
        rows.add("Contact Us");
        rows.add("The Team");
        DrawerAdapter drawerAdapter =new DrawerAdapter(rows);
        drawerRecyclerView.setAdapter(drawerAdapter);
        drawerRecyclerView.hasFixedSize();
        drawerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
